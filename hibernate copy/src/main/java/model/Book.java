package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by eugen on 10/29/17.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer pages;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BookAuthor",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            })
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToOne
    private ISBN isbn;

    public Book() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                ", authors=" + authors +
                ", genre=" + genre +
                ", isbn=" + isbn +
                '}';
    }

    public Book(String name, Integer pages, List<Author> authors, Genre genre, ISBN isbn) {

        this.name = name;
        this.pages = pages;
        this.authors = authors;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }
}
