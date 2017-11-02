package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BookList")
@Data
public class Book {

    @Id
    @Column(name = "Id")
    private long id;

    @Column(name = "BookName")
    private String name;

    @Column(name = "Author")
    private String author;

    @Column(name = "Publisher")
    private String publisher;

    public String toString(){
        return id + " " + name + " " + author + " " + publisher + "\n";
    }
}
