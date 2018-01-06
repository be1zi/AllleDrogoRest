package Rest.Model;

import Rest.Helpers.Comment;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class CommentModel {

    @Id
    @GeneratedValue
    private Long id;
    private int rate;
    private String description;
    private String senderLogin;
    private Calendar date;
    private Comment type;
    private String auctionTitle;

    @Transient
    private String tmpDate;

    private Long transactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Comment getType() {
        return type;
    }

    public void setType(Comment type) {
        this.type = type;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public String getTmpDate() {
        return tmpDate;
    }

    public void setTmpDate(String tmpDate) {
        this.tmpDate = tmpDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
