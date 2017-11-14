package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "book")
@AllArgsConstructor
public class Book {

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "country")
    private String country;

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
    private byte[] image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}