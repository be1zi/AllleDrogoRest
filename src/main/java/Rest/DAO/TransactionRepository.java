package Rest.DAO;

import Rest.Model.AuctionModel;
import Rest.Model.TransactionModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionModel, Long> {

    List<TransactionModel> findByAuctionModel(AuctionModel auctionModel);
}
