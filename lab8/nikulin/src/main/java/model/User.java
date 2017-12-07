package model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "ulogin")
    String ulogin;

    @Column(name = "upassword")
    String upassword;



    @Override
    public String toString() {
        return "User{" +
                "login='" + ulogin + '\'' +
                ", password='" + upassword + '\'' +
                '}';
    }
}

