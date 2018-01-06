package Rest.DAO;

import Rest.Helpers.Comment;
import Rest.Model.CommentModel;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentModel, Long> {

}
