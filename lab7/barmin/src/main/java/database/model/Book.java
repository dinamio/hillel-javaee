package database.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Book_Author",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            })
    private Set<Author> authors;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public Set<Author> getAuthors() {
        return authors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        StringBuilder authors = new StringBuilder();
        authors.append("[");
        for(Author el : this.authors){
            authors.append(el.getName())
                    .append(" ")
                    .append(el.getSurname())
                    .append(", ");
        }
        if(authors.length() > 2) {
            authors.delete(authors.length() - 2, authors.length());
        }
        authors.append("]");
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors.toString() +
                ", user_id=" + user_id.getId() +
                '}';
    }
}
