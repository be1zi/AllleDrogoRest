package Rest.Controller;

import Rest.DAO.UserRepository;
import Rest.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){

        this.userRepository = userRepository;
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

        userRepository.save(userModel);

        return userModel.toString();
    }
}
