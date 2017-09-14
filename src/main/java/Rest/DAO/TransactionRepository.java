package Rest.DAO;

import Rest.Model.TransactionModel;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionModel, Long> {
}
