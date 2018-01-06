package Rest.Controller;

import Rest.DAO.AuctionRepository;
import Rest.Helpers.Comment;
import Rest.Model.AuctionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/account", method = RequestMethod.POST)
public class AccountController {

    private final AuctionRepository auctionRepository;


    public AccountController(AuctionRepository auctionRepository){
        this.auctionRepository = auctionRepository;
    }

    @RequestMapping("/auctionNumber")
    public ResponseEntity<Integer> numberOfAuction(@RequestBody  Long id){

        List<AuctionModel> list = auctionRepository.findAllByUserIdAndIsSoldAndIsEnded(id, false, false);

        if(list == null )
            return new ResponseEntity<>(0, new HttpHeaders(), HttpStatus.OK);

        return new ResponseEntity<>(list.size(), new HttpHeaders(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/commentsNumber", method = RequestMethod.POST)
//    public ResponseEntity<Integer> numberOfComments(@RequestBody Comment type){
//
//
//    }
}
