package model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BookList")
@Data
public class Book {

    @Id
    @Column(name = "bookid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BookName")
    private String name;

    @Column(name = "pages")
    int pages;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "price")
    private int price;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "BookAuthor",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            })
    private List<Author> authors;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "BookGenre",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            })
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BookOrder",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "order_id")
            })
    public List<Order> orders;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
