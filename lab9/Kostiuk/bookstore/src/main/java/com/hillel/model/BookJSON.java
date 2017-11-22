package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookJSON {

    private String author;
    private String country;
    private byte[] image;
    private String language;
    private String link;
    private int    pages;
    private String title;
    private int    year;


}