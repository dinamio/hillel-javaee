package com.hillel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hillel.model.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleBookDto {

    @JsonProperty("id")
    private long id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("pages")
    private int pages;
    @JsonProperty("title")
    private String title;
    @JsonProperty("year")
    private int year;


    public static SimpleBookDto extractFromBook(Book book) {
        return SimpleBookDto.builder()
                .id(book.getId())
                .author(book.getAuthor().getFullName())
                .pages(book.getPages())
                .title(book.getTitle())
                .year(book.getYear())
                .build();
    }
}
