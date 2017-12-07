package com.hillel.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Access(AccessType.FIELD)
@Table(name = "countries")
@EqualsAndHashCode(exclude = "writers")
public class Country {

    public Country() {
        //NOP
    }

    public Country(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "countries", fetch = FetchType.LAZY)
    private List<Writer> writers;

}
