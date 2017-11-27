package database.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "username")
    private String username;
    private String passHex;

    public String getPassHex() {
        return passHex;
    }

    public String getId() {
        return username;
    }

    public void setId(String username) {
        this.username = username;
    }

    public void setPassHex(String passHex) {
        this.passHex = passHex;
    }
}
