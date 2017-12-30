package Rest.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
public class BiddingModel {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserModel userId;

    private Long auctionId;
    private String auctionTitle;
    private String userLogin;
    private String ownerLogin;

    private Double price;
    private int itemNumber;
    private Calendar date;

    @Transient
    private String tmpDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getTmpDate() {
        return tmpDate;
    }

    public void setTmpDate(String tmpDate) {
        this.tmpDate = tmpDate;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }
}
