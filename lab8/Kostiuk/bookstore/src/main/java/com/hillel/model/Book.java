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
@Table(name = "books")
@Access(AccessType.FIELD)
@FetchProfile(name = "book_full_info", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = Book.class, association = "reviewers", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = Book.class, association = "user", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = Book.class, association = "author", mode = FetchMode.JOIN)
})
public class Book {

    public Book() {
        //NOP
    }

    public Book(String language, String link, int pages, String title, int year, byte[] image, Writer author) {
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
        this.image = image;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "language")
    private String language;

    @Column(name = "link")
    private String link;

    @Column(name = "pages")
    private int pages;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @Lob
    @Column(name = "image", nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Writer author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_rev", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "rev_id"))
    private List<Writer> reviewers;

}