package com.hillel.model.presentation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hillel.util.SimpleBookPresentationSerializer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonSerialize(using = SimpleBookPresentationSerializer.class)
public class SimpleBookPresentation {

    private long   id;
    private String author;
    private int    pages;
    private String title;
    private int    year;

}
