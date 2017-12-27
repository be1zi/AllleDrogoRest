package Rest.DAO;

import Rest.Model.AuctionModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuctionRepository extends CrudRepository<AuctionModel, Long> {
    List<AuctionModel> findAllByUserId(Long id);
    List<AuctionModel> findAllByUserIdAndIsSoldAndIsEnded(Long id, boolean isSold, boolean isEnded);
    AuctionModel findByIdAndUserId(Long id, Long userId);
}
