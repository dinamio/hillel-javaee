package com.hillel.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

    private String firstName;
    private String lastName;
    private String loginName;
    private String encodedPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return loginName != null ? loginName.equals(user.loginName) : user.loginName == null;
    }

    @Override
    public int hashCode() {
        return loginName != null ? loginName.hashCode() : 0;
    }
}
