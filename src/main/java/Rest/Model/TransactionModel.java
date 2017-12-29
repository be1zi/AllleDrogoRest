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
    private int itemNumber;
    private double price;

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

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
