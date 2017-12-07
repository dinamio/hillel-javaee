package com.hillel.model.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleBookPresentation {

    @JsonProperty("id")
    private long   id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("pages")
    private int    pages;
    @JsonProperty("title")
    private String title;
    @JsonProperty("year")
    private int    year;

}
