package edu.karazin.shop.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class BookList {

    @Id
    @Column(name = "bookid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookname")
    private String name;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "price")
    private Double price;

    @ManyToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "book_author",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            })
    private List<Author> authors;

    @ManyToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "book_genre",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            })
    private List<Genre> genres;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "bookLists")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<OrderList> orderLists;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "books")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<OrderStory> ordersStory;

    public BookList(){}

    public BookList(String name, Integer pages, List<Author> authors, List<Genre> genres, String publisher, Double price) {
        this.name = name;
        this.pages = pages;
        this.publisher = publisher;
        this.price = price;
        this.authors = authors;
        this.genres = genres;
    }

    public BookList(Long id, String name, Integer pages, List<Author> authors, List<Genre> genres, String publisher, Double price) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.publisher = publisher;
        this.price = price;
        this.authors = authors;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    @Override
    public String toString() {
        return "BookList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }

    public List<OrderStory> getOrdersStory() {
        return ordersStory;
    }

    public void setOrdersStory(List<OrderStory> ordersStory) {
        this.ordersStory = ordersStory;
    }
}
