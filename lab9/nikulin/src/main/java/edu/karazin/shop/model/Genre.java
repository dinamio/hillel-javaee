package edu.karazin.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreid;

    private String genrename;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BookGenre",
            joinColumns = {
                    @JoinColumn(name = "genre_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id")
            })
    private List<Book> books;

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{" + " id=" + + genreid +
                "genrename='" + genrename + '\'' +
                '}';
    }
}
