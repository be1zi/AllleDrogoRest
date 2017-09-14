package Rest.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CommentModel {

    @Id
    @GeneratedValue
    private Long id;
    private int rate;
    private String description;

    @OneToOne
    private UserModel settingUser;

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

    public UserModel getSettingUser() {
        return settingUser;
    }

    public void setSettingUser(UserModel settingUser) {
        this.settingUser = settingUser;
    }
}
