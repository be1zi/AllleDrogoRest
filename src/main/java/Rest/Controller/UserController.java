package Rest.Controller;

import Rest.DAO.AccountRepository;
import Rest.DAO.UserRepository;
import Rest.Model.AccountModel;
import Rest.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserController(UserRepository userRepository, AccountRepository accountRepository){

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping("/getuser")
    public List<UserModel> getUser(){

        List<UserModel> list = (List<UserModel>)userRepository.findAll();

        return list;
    }

    @RequestMapping("/adduser")
    public UserModel addUser(@RequestParam(value = "login", defaultValue = "") String login, @RequestParam(value = "password", defaultValue = "") String password){

        UserModel userModel = new UserModel();

        userModel.setLogin(login);
        userModel.setPassword(password);
        userModel.setAccountType("Admin");

        userRepository.save(userModel);

        return userModel;
    }
}
