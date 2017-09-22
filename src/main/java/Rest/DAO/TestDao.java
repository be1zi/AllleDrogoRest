package Rest.DAO;

import Rest.Model.TestModel;
import org.springframework.data.repository.CrudRepository;

public interface TestDao extends CrudRepository<TestModel, Long> {
}
