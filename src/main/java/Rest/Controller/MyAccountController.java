package Rest.Controller;

import Rest.DAO.AccountRepository;
import Rest.DAO.UserRepository;
import Rest.Model.AccountModel;
import Rest.Model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import static java.lang.System.out;

@RestController
@RequestMapping("/account")
public class MyAccountController {

    private final UserRepository userRepository;
    private  final AccountRepository accountRepository;

    public MyAccountController(UserRepository userRepository, AccountRepository accountRepository){
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel userModel) {

        UserModel userPassword = userRepository.findByIdAndLogin(userModel.getId(), userModel.getLogin());
        if(userPassword != null){
            userPassword.setPassword(userModel.getPassword());
            userRepository.save(userPassword);

            return new ResponseEntity<>(userPassword, new HttpHeaders(), HttpStatus.OK);
        }

        UserModel userModel1 = userRepository.findByLogin(userModel.getLogin());
        Optional<UserModel> uM = userRepository.findById(userModel.getId());

        if(userModel1 == null){
            userRepository.save(userModel);
            return new ResponseEntity<>(userModel, new HttpHeaders(), HttpStatus.OK);
        }else if(uM.get().getId() != userModel1.getId()){
            return new ResponseEntity<>(uM.get(), new HttpHeaders(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(uM.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/editemail",method = RequestMethod.POST)
    public ResponseEntity<UserModel> saveEmail(@RequestBody UserModel userModel){

        UserModel uM = userRepository.findByLoginAndPassword(userModel.getLogin(), userModel.getPassword());

        AccountModel accountModel = accountRepository.findByEmail(userModel.getAccountModel().getEmail());

        if(uM != null && accountModel == null){
            accountRepository.save(userModel.getAccountModel());
            return new ResponseEntity<>(userModel, new HttpHeaders(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(userModel, new HttpHeaders(), HttpStatus.valueOf(301));
        }
    }

    @RequestMapping(value = "/editaccount", method = RequestMethod.POST)
    public ResponseEntity<UserModel> saveAccount(@RequestBody UserModel userModel){

        UserModel uM = userRepository.findByLoginAndPassword(userModel.getLogin(), userModel.getPassword());

        if(uM != null){
            accountRepository.save(userModel.getAccountModel());
            return new ResponseEntity<>(userModel, new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(userModel, new HttpHeaders(), HttpStatus.valueOf(301));
        }
    }
}
