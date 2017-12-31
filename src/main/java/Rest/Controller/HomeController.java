package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.DAO.PhotoRepository;
import Rest.Model.AuctionModel;
import Rest.Model.PhotoModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final AuctionRepository auctionRepository;
    private final PhotoRepository photoRepository;

    public HomeController(AuctionRepository auctionRepository, PhotoRepository photoRepository){
        this.auctionRepository = auctionRepository;
        this.photoRepository = photoRepository;
    }

    @RequestMapping("/get")
    public ResponseEntity<AuctionModel[]> getHomeAuction(){

        List<AuctionModel> auctionModels = auctionRepository.findFirst9ByIsEndedOrderByViewNumberDesc(false);

        AuctionModel[] auctionArray = new AuctionModel[auctionModels.size()];

        for(int i =0; i<auctionModels.size(); i++){

            if(auctionModels.get(i).getFiles() != null || auctionModels.get(i).getFiles().size() != 0){
                try {
                    PhotoModel tmp = auctionModels.get(i).getFiles().get(0);
                    List<PhotoModel> tmpArray = new ArrayList<>();
                    tmpArray.add(tmp);
                    auctionModels.get(i).setFiles(tmpArray);
                }catch (Exception e){
                    List<PhotoModel> tmpArray = new ArrayList<>();
                    tmpArray.add(new PhotoModel());
                    auctionModels.get(i).setFiles(tmpArray);
                }
            }else{
                List<PhotoModel> tmpArray = new ArrayList<>();
                tmpArray.add(new PhotoModel());
                auctionModels.get(i).setFiles(tmpArray);
            }
            auctionArray[i] = auctionModels.get(i);
        }

        if(auctionModels == null)
            return new ResponseEntity<>(auctionArray, new HttpHeaders(), HttpStatus.valueOf(301));
        else
            return new ResponseEntity<>(auctionArray, new HttpHeaders(), HttpStatus.OK);
    }
}
