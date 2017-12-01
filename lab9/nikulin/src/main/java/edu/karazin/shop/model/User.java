package edu.karazin.shop.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Component
//@Scope("session")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String ulogin) {
        this.username = ulogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String upassword) {
        this.password = upassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

