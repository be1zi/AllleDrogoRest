package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.DAO.TransactionRepository;
import Rest.DAO.UserRepository;
import Rest.Model.AuctionModel;
import Rest.Model.TransactionModel;
import Rest.Model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final AuctionRepository auctionRepository;
    private final TransactionRepository transactionRepository;
    private  final UserRepository userRepository;

    public ShoppingController(AuctionRepository auctionRepository, TransactionRepository transactionRepository, UserRepository userRepository){
        this.auctionRepository = auctionRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
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

        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setAuctionModel(auctionModel);
        transactionModel.setBuy(true);
        transactionModel.setPay(false);
        transactionModel.setCommentSet(false);
        transactionModel.setUserModel(userModel);
        transactionModel.setItemNumber(itemNumber);
        transactionModel.setPrice(auctionModel.getBuyNowPrice());

        ownerModel.getAccountModel().getTransactionList().add(transactionModel);

        int items = auctionModel.getItemNumber() - itemNumber;

        if(itemNumber<0)
            return new ResponseEntity<>(0, new HttpHeaders(), HttpStatus.OK);

        auctionModel.setItemNumber(items);

        if(items == 0) {
            auctionModel.setEnded(true);
            auctionModel.setEndPrice(auctionModel.getBuyNowPrice());
            auctionModel.setEndDate(Calendar.getInstance());
        }

        transactionRepository.save(transactionModel);
        auctionRepository.save(auctionModel);
        userRepository.save(ownerModel);

        return new ResponseEntity<>(1, new HttpHeaders(), HttpStatus.OK);
    }

}
