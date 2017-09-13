package Rest.Controller;

import Rest.DAO.AccountRepository;
import Rest.DAO.UserRepository;
import Rest.Model.AccountModel;
import Rest.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/getUser")
    public List<UserModel> getUser(){

        List<UserModel> list = (List<UserModel>)userRepository.findAll();

        return list;
    }

    @RequestMapping("/addUser")
    public String addUser(){

        UserModel userModel = new UserModel();
        userModel.setLogin("admin");
        userModel.setPassword("admin123");
        userModel.setAccountType("Administrator");

        AccountModel accountModel = new AccountModel();
        accountModel.setCity("Krakow");
        accountModel.setCountry("Poland");
        accountModel.setEmail("test12@mail.com");
        accountModel.setFirstName("Konrad");
        accountModel.setHouseNumber(123);
        accountModel.setLastName("Testowy");
        accountModel.setStreet("Lesna");
        accountModel.setZipCode("32-091");
        userModel.setAccountModel(accountModel);
        
        accountRepository.save(accountModel);
        userRepository.save(userModel);

        return userModel.toString();
    }
}
