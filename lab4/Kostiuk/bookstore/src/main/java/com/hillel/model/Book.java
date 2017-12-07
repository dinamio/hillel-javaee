package com.hillel.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hillel.util.BookDeserializer;
import com.hillel.util.BookSerializer;
import lombok.Data;

@Data
@JsonDeserialize(using = BookDeserializer.class)
@JsonSerialize(using = BookSerializer.class)
public class Book {

    private long   id;
    private String author;
    private String country;
    private String imageLink;
    private String language;
    private String link;
    private int    pages;
    private String title;
    private int    year;

}
