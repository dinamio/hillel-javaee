package com.hillel.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = {"firstName", "lastName"})
public class User {

    public final static User UNDEFINED_USER = User.builder().loginName("undefined").build();

    private Long   id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String encodedPassword;

}
