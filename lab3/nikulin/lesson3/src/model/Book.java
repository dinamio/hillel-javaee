package model;

import lombok.Data;

@Data
public class Book {
    private long id;
    private String name;
    private String author;
    private String publisher;
}
