package edu.karazin.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BookAuthor",
            joinColumns = {
                    @JoinColumn(name = "author_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id")
            })
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" + " id= " + id+
                ", name='" + name + '\'' +
                '}';
    }
}
