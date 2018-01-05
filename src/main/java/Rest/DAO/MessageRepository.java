package Rest.DAO;

import Rest.Model.MessageModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageModel, Long> {

    MessageModel findByTopicAndOwnerLoginAndSenderLogin(String topic, String ownerLogin, String senderLogin);
    List<MessageModel> findAllByOwnerLogin(String ownerLogin);
    List<MessageModel> findAllBySenderLogin(String senderLogin);
}
