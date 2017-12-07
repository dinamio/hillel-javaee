package model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @Override
    public String toString() {
        return "Author{" + " id= " + id+
                ", name='" + name + '\'' +
                '}';
    }
}
