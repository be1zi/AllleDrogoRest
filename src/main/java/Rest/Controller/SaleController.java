package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.DAO.PhotoRepository;
import Rest.Model.AuctionModel;
import Rest.Model.PhotoModel;
import Rest.Model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.System.out;


@RestController
@RequestMapping("/sale")
public class SaleController {

    private final AuctionRepository auctionRepository;
    private final PhotoRepository photoRepository;

    public SaleController(AuctionRepository auctionRepository, PhotoRepository photoRepository){
        this.auctionRepository = auctionRepository;
        this.photoRepository = photoRepository;
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

        AuctionModel[] result = new AuctionModel[aMList.size()];

        for(int i = 0; i<aMList.size(); i++){
            result[i] = aMList.get(i);
        }

        if(aMList == null)
            return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.valueOf(301));
        else
            return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

}
