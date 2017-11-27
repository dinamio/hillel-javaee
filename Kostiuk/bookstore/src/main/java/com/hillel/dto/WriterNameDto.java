package com.hillel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WriterNameDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("fullName")
    private String fullName;

}
