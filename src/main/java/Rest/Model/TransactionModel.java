package Rest.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TransactionModel {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserModel userModel;

    @OneToOne
    private AuctionModel auctionModel;

    private boolean isBuy;
    private boolean isPay;
    private boolean isCommentSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuctionModel getAuctionModel() {
        return auctionModel;
    }

    public void setAuctionModel(AuctionModel auctionModel) {
        this.auctionModel = auctionModel;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public boolean isCommentSet() {
        return isCommentSet;
    }

    public void setCommentSet(boolean commentSet) {
        isCommentSet = commentSet;
    }
}
