package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.DAO.BiddingRepository;
import Rest.DAO.TransactionRepository;
import Rest.DAO.UserRepository;
import Rest.Helpers.TypeFormatter;
import Rest.Model.AuctionModel;
import Rest.Model.BiddingModel;
import Rest.Model.TransactionModel;
import Rest.Model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final AuctionRepository auctionRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BiddingRepository biddingRepository;

    public ShoppingController(AuctionRepository auctionRepository, TransactionRepository transactionRepository, UserRepository userRepository, BiddingRepository biddingRepository){
        this.auctionRepository = auctionRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.biddingRepository = biddingRepository;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Integer> buy(@RequestBody Map<Object, Object> map){

        Integer aId = (Integer)map.get("auctionId");
        Long auctionId = aId.longValue();
        Integer oId = (Integer)map.get("ownerId");
        Long ownerId = oId.longValue();
        Integer uId = (Integer)map.get("userId");
        Long userId = uId.longValue();
        Integer iN = (Integer)map.get("itemNumber");
        int itemNumber = iN.intValue();
        Double pr = (Double)map.get("price");
        double price = pr.doubleValue();

        AuctionModel auctionModel = auctionRepository.findByIdAndUserId(auctionId,ownerId);

        if(auctionModel == null || auctionModel.isEnded() == true){
            return new ResponseEntity<>(0, new HttpHeaders(), HttpStatus.OK);
        }

        Optional<UserModel> result = userRepository.findById(ownerId);
        UserModel ownerModel = new UserModel();
        ownerModel.setId(result.map(UserModel::getId).orElse(Long.valueOf(0)));
        ownerModel.setLogin(result.map(UserModel::getLogin).orElse(null));
        ownerModel.setPassword(result.map(UserModel::getPassword).orElse(null));
        ownerModel.setAccountType(result.map(UserModel::getAccountType).orElse(null));
        ownerModel.setAccountModel(result.map(UserModel::getAccountModel).orElse(null));

        if(ownerModel == null || ownerModel.getId() == 0){
            return new ResponseEntity<>(0, new HttpHeaders(), HttpStatus.OK);
        }

        Optional<UserModel> userResult = userRepository.findById(userId);
        UserModel userModel = new UserModel();
        userModel.setId(userResult.map(UserModel::getId).orElse(Long.valueOf(0)));
        userModel.setLogin(userResult.map(UserModel::getLogin).orElse(null));
        userModel.setPassword(userResult.map(UserModel::getPassword).orElse(null));
        userModel.setAccountType(userResult.map(UserModel::getAccountType).orElse(null));
        userModel.setAccountModel(userResult.map(UserModel::getAccountModel).orElse(null));

        if(userModel == null || userModel.getId() == 0){
            return new ResponseEntity<>(0, new HttpHeaders(), HttpStatus.OK);
        }

        if(ownerModel.getId() == userModel.getId())
            return new ResponseEntity<>(2, new HttpHeaders(), HttpStatus.OK);

        //kup teraz
        if(price < 1) {
            TransactionModel transactionModel = new TransactionModel();
            transactionModel.setAuctionModel(auctionModel);
            transactionModel.setBuy(true);
            transactionModel.setPay(false);
            transactionModel.setCommentSet(false);
            transactionModel.setUserModel(userModel);
            transactionModel.setItemNumber(itemNumber);
            transactionModel.setPrice(auctionModel.getBuyNowPrice());
            transactionModel.setDate(Calendar.getInstance());
            transactionModel.setUserLogin(userModel.getLogin());
            transactionModel.setOwnerLogin(ownerModel.getLogin());

            ownerModel.getAccountModel().getTransactionList().add(transactionModel);

            int items = auctionModel.getItemNumber() - itemNumber;

            if (itemNumber < 0)
                return new ResponseEntity<>(0, new HttpHeaders(), HttpStatus.OK);

            auctionModel.setItemNumber(items);

            if (items == 0) {
                auctionModel.setEnded(true);
                auctionModel.setSold(true);
                auctionModel.setEndPrice(auctionModel.getBuyNowPrice());
                auctionModel.setEndDate(Calendar.getInstance());
            }

            transactionRepository.save(transactionModel);
            auctionRepository.save(auctionModel);
            userRepository.save(ownerModel);

        }else{ //licytacja

            BiddingModel biddingModel = biddingRepository.findByUserIdAndAuctionId(userModel, auctionId);

            if(biddingModel == null) {
                biddingModel = new BiddingModel();
                biddingModel.setDate(Calendar.getInstance());
                biddingModel.setItemNumber(itemNumber);
                biddingModel.setPrice(price);
                biddingModel.setUserId(userModel);
                biddingModel.setUserLogin(userModel.getLogin());
                auctionModel.getBiddingList().add(biddingModel);
                biddingModel.setAuctionId(auctionId);
                biddingModel.setAuctionTitle(auctionModel.getTitle());
            }else{
                biddingModel.setItemNumber(itemNumber);
                biddingModel.setPrice(price);
            }

            auctionModel.setBiddingPrice(price + 1);

            biddingRepository.save(biddingModel);
            auctionRepository.save(auctionModel);
            userRepository.save(ownerModel);
        }

        return new ResponseEntity<>(1, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<TransactionModel[]> transactions(@RequestBody AuctionModel auctionModel){

        List<TransactionModel> transactionModelArrayList = transactionRepository.findByAuctionModel(auctionModel);

        if(transactionModelArrayList == null)
            return new ResponseEntity<>(new TransactionModel[0], new HttpHeaders(), HttpStatus.OK);

        TransactionModel[] transactionArray = new TransactionModel[transactionModelArrayList.size()];

        for (int i=0;i<transactionModelArrayList.size(); i++) {
            transactionArray[i] = transactionModelArrayList.get(i);
        }

        return new ResponseEntity<>(transactionArray, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/bought", method = RequestMethod.POST)
    public ResponseEntity<TransactionModel[]> getBought(@RequestBody String login){

        List<TransactionModel> transactionModelList = transactionRepository.findByUserLogin(login);

        if(transactionModelList == null && transactionModelList.size() == 0)
            return new ResponseEntity<>(new TransactionModel[0], new HttpHeaders(), HttpStatus.OK);

        TransactionModel[] transactionModels = new TransactionModel[transactionModelList.size()];

        for(int i=0; i<transactionModelList.size(); i++)
            transactionModels[i] = transactionModelList.get(i);

        return new ResponseEntity<>(transactionModels, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/auctioned", method = RequestMethod.POST)
    public ResponseEntity<BiddingModel[]> getAuctionedAuctions(@RequestBody String login){

        List<BiddingModel> biddingModels = biddingRepository.findAllByUserLogin(login);

        if(biddingModels == null && biddingModels.size() == 0)
            return new ResponseEntity<>(new BiddingModel[0], new HttpHeaders(), HttpStatus.OK);

        BiddingModel[] biddingArrays = new BiddingModel[biddingModels.size()];

        for( int i=0; i<biddingModels.size();i++)
            biddingArrays[i] = biddingModels.get(i);

        return new ResponseEntity<>(biddingArrays, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/observed", method = RequestMethod.POST)
    public ResponseEntity<AuctionModel[]> getObserved(@RequestBody String login){

        List<AuctionModel> auctionModels = userRepository.findByLogin(login).getAccountModel().getWatchUserList();

        if(auctionModels == null)
            return new ResponseEntity<>(new AuctionModel[0], new HttpHeaders(), HttpStatus.OK);

        AuctionModel[] array = TypeFormatter.listToArray(auctionModels);

        return new ResponseEntity<>(array, new HttpHeaders(), HttpStatus.OK);

    }
}
