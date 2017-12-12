package com.hillel.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "books")
@EqualsAndHashCode
@NoArgsConstructor
public class Book {

    @Builder
    public Book(String language, String link, int pages, String title, int year, byte[] image, Writer author, List<Writer> reviewers) {
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
        this.image = image;
        this.author = author;
        this.reviewers = reviewers;
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
    @Column(name = "image")
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Writer author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_rev", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "rev_id"))
    private List<Writer> reviewers;

}