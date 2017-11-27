package database.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue
    private Integer author_id;
    private String name;
    private String surname;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<Book> books;

    public Integer getAuthor_id() {
        return author_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
