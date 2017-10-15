package Rest.Controller;

import Rest.DAO.AccountRepository;
import Rest.DAO.UserRepository;
import Rest.Model.AccountModel;
import Rest.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserModel> getUser(@RequestBody UserModel userModel){

        UserModel uM = userRepository.findByLoginAndPassword(userModel.getLogin(), userModel.getPassword());
        return new ResponseEntity<>(uM, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel){

        UserModel uM = userRepository.findByLogin(userModel.getLogin());

        if (uM != null){
            UserModel userModel1 = new UserModel();
            return new ResponseEntity<>(userModel1, new HttpHeaders(), HttpStatus.OK);
        }

        accountRepository.save(userModel.getAccountModel());
        userRepository.save(userModel);
        uM = userRepository.findByLogin(userModel.getLogin());
        
        return new ResponseEntity<>(uM, new HttpHeaders(), HttpStatus.OK);
    }

//
//    @RequestMapping("/getalluser")
//    public List<UserModel> getUserList(){
//
//        List<UserModel> list = (List<UserModel>)userRepository.findAll();
//        return list;
//    }
//
//    @RequestMapping("/getuser")
//    public UserModel getUser(@RequestParam(value = "login", defaultValue = "") String login, @RequestParam(value = "password", defaultValue = "") String password){
//
//        UserModel uM = userRepository.findByLogin(login);
//        return uM;
//    }
//


}
