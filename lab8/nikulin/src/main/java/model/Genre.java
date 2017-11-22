package model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreid;

    private String genrename;

    @ManyToMany
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinTable(name = "BookGenre",
            joinColumns = {
                    @JoinColumn(name = "genre_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id")
            })
    private List<Book> books;

    @Override
    public String toString() {
        return "Genre{" + " id=" + + genreid +
                "genrename='" + genrename + '\'' +
                '}';
    }
}
