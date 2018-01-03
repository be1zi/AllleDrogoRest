package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.DAO.PhotoRepository;
import Rest.Helpers.TypeFormatter;
import Rest.Model.AuctionModel;
import Rest.Model.BiddingModel;
import Rest.Model.PhotoModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final PhotoRepository photoRepository;

    public AuctionController(AuctionRepository auctionRepository, PhotoRepository photoRepository){

        this.auctionRepository = auctionRepository;
        this.photoRepository = photoRepository;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ResponseEntity<AuctionModel> getAuction(@RequestBody Long id){

        Optional<AuctionModel> result = auctionRepository.findById(id);
        AuctionModel auctionModel = new AuctionModel();
        auctionModel.setId(result.map(AuctionModel::getId).orElse(Long.valueOf(0)));
        auctionModel.setUserId(result.map(AuctionModel::getUserId).orElse(Long.valueOf(0)));
        auctionModel.setBiddingList(result.map(AuctionModel::getBiddingList).orElse(null));
        auctionModel.setBuyNowPrice(result.map(AuctionModel::getBuyNowPrice).orElse(null));
        auctionModel.setBiddingPrice(result.map(AuctionModel::getBiddingPrice).orElse(null));
        auctionModel.setItemNumber(result.map(AuctionModel::getItemNumber).orElse(0));
        auctionModel.setStartDate(result.map(AuctionModel::getStartDate).orElse(null));
        auctionModel.setEndDate(result.map(AuctionModel::getEndDate).orElse(null));
        auctionModel.setViewNumber(result.map(AuctionModel::getViewNumber).orElse(0));
        auctionModel.setAuctionType(result.map(AuctionModel::isAuctionType).orElse(false));
        auctionModel.setBuyNowType(result.map(AuctionModel::isBuyNowType).orElse(false));
        auctionModel.setCategory(result.map(AuctionModel::getCategory).orElse(""));
        auctionModel.setTitle(result.map(AuctionModel::getTitle).orElse(""));
        auctionModel.setDescription(result.map(AuctionModel::getDescription).orElse(""));
        auctionModel.setState(result.map(AuctionModel::getState).orElse(""));
        auctionModel.setColor(result.map(AuctionModel::getColor).orElse(""));
        auctionModel.setDamaged(result.map(AuctionModel::getDamaged).orElse(""));
        auctionModel.setProductionDate(result.map(AuctionModel::getProductionDate).orElse(""));
        auctionModel.setWarranty(result.map(AuctionModel::getWarranty).orElse(""));
        auctionModel.setFiles(result.map(AuctionModel::getFiles).orElse(null));
        auctionModel.setUsersList(result.map(AuctionModel::getUsersList).orElse(null));
        auctionModel.setSold(result.map(AuctionModel::isSold).orElse(false));
        auctionModel.setEnded(result.map(AuctionModel::isEnded).orElse(false));
        auctionModel.setEndPrice(result.map(AuctionModel::getEndPrice).orElse(0.0));
        auctionModel.setUserLogin(result.map(AuctionModel::getUserLogin).orElse(null));

        if(auctionModel.getId() == 0)
            return new ResponseEntity<>(auctionModel, new HttpHeaders(), HttpStatus.valueOf(301));
        else
            return new ResponseEntity<>(auctionModel, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ResponseEntity<AuctionModel> editAuction(@RequestBody Map<Object,Object> map){

        Integer intId = (Integer)map.get("id");
        Long id = intId.longValue();
        Integer intUserId = (Integer)map.get("userId");
        Long userId = intUserId.longValue();
        Integer viewNumber = (Integer)map.get("viewNumber");

        AuctionModel aM = auctionRepository.findByIdAndUserId(id, userId);

        if(aM == null){
            return  new ResponseEntity<>(new AuctionModel(), new HttpHeaders(),HttpStatus.FOUND);
        }

        aM.setViewNumber(viewNumber);
        auctionRepository.save(aM);

        return new ResponseEntity<>(aM, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/searchByCategory", method = RequestMethod.POST)
    public ResponseEntity<AuctionModel[]> searchByCategory(@RequestBody String category){

        List<AuctionModel> auctionModels = auctionRepository.findAllByCategory(category);

        if(auctionModels == null || auctionModels.size() == 0)
            return new ResponseEntity<>(new AuctionModel[0], new HttpHeaders(), HttpStatus.OK);

//        AuctionModel[] auctionArray = new AuctionModel[auctionModels.size()];
//        for (int i=0;i<auctionModels.size();i++){
//            if(auctionModels.get(i).getFiles() != null && auctionModels.get(i).getFiles().size() != 0) {
//                PhotoModel tmp = auctionModels.get(i).getFiles().get(0);
//                List<PhotoModel> tmpArray = new ArrayList<>();
//                tmpArray.add(tmp);
//                auctionModels.get(i).setFiles(tmpArray);
//            }else {
//                auctionModels.get(i).setFiles(new ArrayList<>());
//            }
//            auctionArray[i] = auctionModels.get(i);
//        }

        AuctionModel[] auctionArray = TypeFormatter.listToArray(auctionModels);

        return new ResponseEntity<>(auctionArray, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteAuction(@RequestBody Map<Object,Object> map){

        Integer intId = (Integer)map.get("id");
        Long id = intId.longValue();
        Integer intUserId = (Integer)map.get("userId");
        Long userId = intUserId.longValue();

        AuctionModel auctionModel = auctionRepository.findByIdAndUserId(id, userId);

        if(auctionModel == null)
            return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.OK);

        auctionModel.getBiddingList().clear();   //???????
        auctionModel.setBiddingList(null);
        auctionModel.setEnded(true);
        auctionModel.setEndDate(Calendar.getInstance());

        auctionRepository.save(auctionModel);

        return new ResponseEntity<>(true, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/addagain", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addAgain(@RequestBody Map<Object,Object> map){

        Integer intId = (Integer)map.get("id");
        Long id = intId.longValue();
        Integer intUserId = (Integer)map.get("userId");
        Long userId = intUserId.longValue();

        AuctionModel auctionModel = auctionRepository.findByIdAndUserId(id, userId);

        if(auctionModel == null)
            return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.OK);

        List<BiddingModel> biddingModels = new ArrayList<>();
        auctionModel.setBiddingList(biddingModels);
        auctionModel.setStartDate(Calendar.getInstance());
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.add(Calendar.DATE, 7);
        auctionModel.setEndDate(tmpCal);
        auctionModel.setEnded(false);
        auctionModel.setViewNumber(0);

        auctionRepository.save(auctionModel);

        return new ResponseEntity<>(true, new HttpHeaders(), HttpStatus.OK);
    }
}
