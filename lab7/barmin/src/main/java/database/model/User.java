package database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static utils.Encryption.cryptWithMD5;

@Entity
@Table(name = "Users")
public class User {
    @Id
    private String username;
    @Column(name = "passhex")
    private String passHex;

    public String getPassHex() {
        return passHex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassHex(String passHex) {
        this.passHex = cryptWithMD5(passHex);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passHex='" + passHex + '\'' +
                '}';
    }
}
