package edu.karazin.shop.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    private int id;

    private String name;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "book_author",
            joinColumns = {
                    @JoinColumn(name = "author_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id")
            })
    private List<BookList> bookLists;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookList> getBookLists() {
        return bookLists;
    }

    public void setBookLists(List<BookList> bookLists) {
        this.bookLists = bookLists;
    }

    @Override
    public String toString() {
        return name;
    }
}
