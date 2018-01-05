package Rest.DAO;

import Rest.Model.SingleMessageModel;
import org.springframework.data.repository.CrudRepository;

public interface SingleMessageRepository extends CrudRepository<SingleMessageModel, Long> {
}
