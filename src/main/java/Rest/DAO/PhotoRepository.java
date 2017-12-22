package Rest.DAO;

import Rest.Model.PhotoModel;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<PhotoModel, Long> {
}
