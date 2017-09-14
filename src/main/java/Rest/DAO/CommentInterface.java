package Rest.DAO;

import Rest.Model.CommentModel;
import org.springframework.data.repository.CrudRepository;

public interface CommentInterface extends CrudRepository<CommentModel, Long> {
}
