package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.DAO.PhotoRepository;
import Rest.DAO.UserRepository;
import Rest.Helpers.TypeFormatter;
import Rest.Model.AuctionModel;
import Rest.Model.PhotoModel;
import Rest.Model.TransactionModel;
import Rest.Model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final AuctionRepository auctionRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public SaleController(AuctionRepository auctionRepository, PhotoRepository photoRepository, UserRepository userRepository){
        this.auctionRepository = auctionRepository;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<AuctionModel> addAuction(@RequestBody AuctionModel auctionModel){

        for( int i = 0; i<auctionModel.getFiles().size(); i++) {
            PhotoModel pM = auctionModel.getFiles().get(i);
            photoRepository.save(pM);
        }
        auctionRepository.save(auctionModel);
        return new ResponseEntity<>(auctionModel, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getMyAuction", method = RequestMethod.POST)
    public ResponseEntity<AuctionModel[]> getMyAuction(@RequestBody Map<Object,Object> map){

        Integer userIdInteger = (Integer) map.get("userId");
        Long userId = userIdInteger.longValue();
        boolean isSold = (boolean)map.get("isSold");
        boolean isEnded = (boolean)map.get("isEnded");

        List<AuctionModel> aMList = auctionRepository.findAllByUserIdAndIsSoldAndIsEnded(userId, isSold,isEnded);

        AuctionModel[] result = TypeFormatter.listToArray(aMList);

        if(aMList == null)
            return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.valueOf(301));
        else
            return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getSold", method = RequestMethod.POST)
    public ResponseEntity<TransactionModel[]> getTransactions(@RequestBody String login){

        UserModel userModel = userRepository.findByLogin(login);

        if(userModel == null)
            return new ResponseEntity<>(new TransactionModel[0], new HttpHeaders(), HttpStatus.OK);

        List<TransactionModel> list =  userModel.getAccountModel().getTransactionList();

        if(list == null)
            return new ResponseEntity<>(new TransactionModel[0], new HttpHeaders(), HttpStatus.OK);

        TransactionModel[] result = new TransactionModel[list.size()];

        for(int i=0; i<list.size();i++)
            result[i] = list.get(i);

        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

}
