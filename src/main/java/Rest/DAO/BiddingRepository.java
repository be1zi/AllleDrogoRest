package Rest.DAO;

import Rest.Model.BiddingModel;
import Rest.Model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface BiddingRepository extends CrudRepository<BiddingModel, Long> {

    BiddingModel findByUserIdAndAuctionId(UserModel userModel, Long id);
}
