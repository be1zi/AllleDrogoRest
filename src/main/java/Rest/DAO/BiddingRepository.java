package Rest.DAO;

import Rest.Model.BiddingModel;
import Rest.Model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BiddingRepository extends CrudRepository<BiddingModel, Long> {

    BiddingModel findByUserIdAndAuctionId(UserModel userModel, Long id);
    List<BiddingModel> findAllByUserLogin(String userLogin);
}
