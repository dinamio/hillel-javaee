package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;
}

