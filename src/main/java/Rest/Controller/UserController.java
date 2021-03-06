package Rest.Controller;

import Rest.DAO.AccountRepository;
import Rest.DAO.UserRepository;
import Rest.Model.AccountModel;
import Rest.Model.AuctionModel;
import Rest.Model.TransactionModel;
import Rest.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserController(UserRepository userRepository, AccountRepository accountRepository){

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    public ResponseEntity<UserModel> getUser(@RequestBody UserModel userModel) {

        UserModel uM = userRepository.findByLoginAndPassword(userModel.getLogin(), userModel.getPassword());
        if (uM == null) {
            uM = userRepository.findByLogin(userModel.getLogin());

            if(uM == null) {
                return new ResponseEntity<>(uM, new HttpHeaders(), HttpStatus.valueOf(301));
            }else{
                return new ResponseEntity<>(uM, new HttpHeaders(), HttpStatus.FOUND);
            }
        } else {
            List<AuctionModel> tmp = new ArrayList<>();
            uM.getAccountModel().setUserAuctionList(tmp);
            List<TransactionModel> tmpT = new ArrayList<>();
            uM.getAccountModel().setTransactionList(tmpT);
            return new ResponseEntity<>(uM, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel){

        UserModel uM = userRepository.findByLogin(userModel.getLogin());

        if (uM != null){
            UserModel userModel1 = new UserModel();
            return new ResponseEntity<>(userModel1, new HttpHeaders(), HttpStatus.FOUND);
        }

        accountRepository.save(userModel.getAccountModel());
        userRepository.save(userModel);

        uM = userRepository.findByLogin(userModel.getLogin());

        return new ResponseEntity<>(uM, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByLogin")
    public ResponseEntity<UserModel> getByLogin(@RequestBody String login){

        UserModel user = userRepository.findByLogin(login);

        if(user == null)
            return new ResponseEntity<>(new UserModel(), new HttpHeaders() , HttpStatus.OK);

        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }
}
