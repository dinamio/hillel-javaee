package com.hillel.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
@Access(AccessType.FIELD)
@Table(name = "writers")
public class Writer {

    public Writer() {
        //NOP
    }

    public Writer(String fullName, List<Country> countries) {
        setFullName(fullName);
        setCountries(countries);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "country_writer", joinColumns = @JoinColumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> countries;


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;


    @ManyToMany(mappedBy = "reviewers", fetch = FetchType.LAZY)
    private List<Book> reviewedBooks;


}
