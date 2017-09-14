package Rest.DAO;

import Rest.Model.AuctionModel;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<AuctionModel, Long> {
}
