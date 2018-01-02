package Rest.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Entity
public class AuctionModel {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String userLogin;

    @OneToMany
    private List<BiddingModel> biddingList;

    private Double buyNowPrice;
    private Double biddingPrice;
    private int itemNumber;
    private Calendar startDate;
    private Calendar endDate;
    private int viewNumber;
    private boolean isAuctionType;
    private boolean isBuyNowType;

    private String category;
    private String title;
    private String description;
    private String state;
    private String color;
    private String damaged;
    private String productionDate;
    private String warranty;

    @OneToMany
    private List<PhotoModel> files;

    @OneToMany
    private List<UserModel> usersList;

    @Transient
    private String tmpDate;
    @Transient
    private String mainImage;

    private boolean isSold;
    private boolean isEnded;
    private Double endPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BiddingModel> getBiddingList() {
        return biddingList;
    }

    public void setBiddingList(List<BiddingModel> biddingList) {
        this.biddingList = biddingList;
    }

    public Double getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(Double buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public Double getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(Double biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public boolean isAuctionType() {
        return isAuctionType;
    }

    public void setAuctionType(boolean auctionType) {
        isAuctionType = auctionType;
    }

    public boolean isBuyNowType() {
        return isBuyNowType;
    }

    public void setBuyNowType(boolean buyNowType) {
        isBuyNowType = buyNowType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDamaged() {
        return damaged;
    }

    public void setDamaged(String damaged) {
        this.damaged = damaged;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public List<PhotoModel> getFiles() {
        return files;
    }

    public void setFiles(List<PhotoModel> files) {
        this.files = files;
    }

    public List<UserModel> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserModel> usersList) {
        this.usersList = usersList;
    }

    public String getTmpDate() {
        return tmpDate;
    }

    public void setTmpDate(String tmpDate) {
        this.tmpDate = tmpDate;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "AuctionModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", biddingList=" + biddingList +
                ", buyNowPrice=" + buyNowPrice +
                ", biddingPrice=" + biddingPrice +
                ", itemNumber=" + itemNumber +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", viewNumber=" + viewNumber +
                ", isAuctionType=" + isAuctionType +
                ", isBuyNowType=" + isBuyNowType +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", color='" + color + '\'' +
                ", damaged='" + damaged + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", warranty='" + warranty + '\'' +
                ", files=" + files +
                ", usersList=" + usersList +
                ", tmpDate='" + tmpDate + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", isSold=" + isSold +
                ", isEnded=" + isEnded +
                ", endPrice=" + endPrice +
                '}';
    }
}

