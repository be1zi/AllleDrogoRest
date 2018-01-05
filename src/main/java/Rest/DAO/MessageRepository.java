package Rest.DAO;

import Rest.Model.MessageModel;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageModel, Long> {

    MessageModel findByTopicAndOwnerLoginAndSenderLogin(String topic, String ownerLogin, String senderLogin);
}
