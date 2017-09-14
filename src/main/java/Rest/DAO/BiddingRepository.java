package Rest.DAO;

import Rest.Model.BiddingModel;
import org.springframework.data.repository.CrudRepository;

public interface BiddingRepository extends CrudRepository<BiddingModel, Long> {
}
