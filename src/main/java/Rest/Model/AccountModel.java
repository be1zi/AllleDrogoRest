package Rest.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class AccountModel {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String city;
    private String zipCode;
    private String street;
    private int houseNumber;
    private int placeNumber;
    private String voivodeship;
    private Double mark;
    private String phone;
    private String description;

    @OneToMany
    private List<AuctionModel> watchUserList;

    @OneToMany
    private List<CommentModel> commentList;

    @OneToMany
    private List<AuctionModel> activeAuctionList;

    @OneToMany
    private List<AuctionModel> userAuctionList;

    @OneToMany
    private List<TransactionModel> transactionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AuctionModel> getWatchUserList() {
        return watchUserList;
    }

    public void setWatchUserList(List<AuctionModel> watchUserList) {
        this.watchUserList = watchUserList;
    }

    public List<CommentModel> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentModel> commentList) {
        this.commentList = commentList;
    }

    public List<AuctionModel> getActiveAuctionList() {
        return activeAuctionList;
    }

    public void setActiveAuctionList(List<AuctionModel> activeAuctionList) {
        this.activeAuctionList = activeAuctionList;
    }

    public List<AuctionModel> getUserAuctionList() {
        return userAuctionList;
    }

    public void setUserAuctionList(List<AuctionModel> userAuctionList) {
        this.userAuctionList = userAuctionList;
    }

    public List<TransactionModel> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionModel> transactionList) {
        this.transactionList = transactionList;
    }
}
