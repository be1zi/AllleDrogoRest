package Rest.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class UserModel {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "UserID")
    private Long id;

    @NotNull
    @Column(name = "Login")
    private String login;

    @NotNull
    @Column(name = "Password")
    private String password;

    @NotNull
    @Column(name = "AccountType")
    private String accountType;

    @OneToOne
    @JoinColumn(name = "accountId")
    private AccountModel accountModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountModel=" + accountModel +
                '}';
    }
}
