package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by eugen on 11/3/17.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)

    private List<Book> books;


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



    public Integer getId() {
        return id;
    }

    public Author() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
