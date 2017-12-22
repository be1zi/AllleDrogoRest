package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.DAO.PhotoRepository;
import Rest.Model.AuctionModel;
import Rest.Model.PhotoModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            out.println(pM.toString());
            photoRepository.save(pM);
        }
        auctionRepository.save(auctionModel);
        return new ResponseEntity<>(auctionModel, new HttpHeaders(), HttpStatus.OK);
    }

}
