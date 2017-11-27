package com.hillel.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Access(AccessType.FIELD)
@Table(name = "users")
@FetchProfile(name = "eager_profile", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = User.class, association = "books", mode = FetchMode.JOIN),
})
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    private String loginName;

    @Column(name = "encoded_password")
    private String encodedPassword;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Book> books;

}
