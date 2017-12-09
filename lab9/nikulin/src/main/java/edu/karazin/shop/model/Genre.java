package edu.karazin.shop.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genreid")
    private int genreid;

    @Column(name = "genrename")
    private String genrename;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "book_genre",
            joinColumns = {
                    @JoinColumn(name = "genre_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id")
            })
    private List<BookList> bookLists;

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

    public List<BookList> getBookLists() {
        return bookLists;
    }

    public void setBookLists(List<BookList> bookLists) {
        this.bookLists = bookLists;
    }

    @Override
    public String toString() {
        return genrename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genreid == genre.genreid &&
                Objects.equals(genrename, genre.genrename) &&
                Objects.equals(bookLists, genre.bookLists);
    }

    @Override
    public int hashCode() {

        return Objects.hash(genreid, genrename, bookLists);
    }
}
