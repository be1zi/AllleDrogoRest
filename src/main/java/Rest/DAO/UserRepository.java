package Rest.DAO;

import Rest.Model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByLogin(String login);
    UserModel findByLoginAndPassword(String login, String password);
    UserModel findByIdAndLogin(Long id, String login);
}
