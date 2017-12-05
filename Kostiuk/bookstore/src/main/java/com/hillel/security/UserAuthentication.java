package com.hillel.security;

import com.hillel.model.User;
import lombok.Builder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class UserAuthentication implements Authentication {

    private final User user;
    private boolean isAuthenticated;
    private AuthDetails authDetails;

    @Builder
    UserAuthentication(User user, AuthDetails authDetails) {
        this.user = user;
        this.authDetails = authDetails;
        setAuthenticated(true);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getCredentials() {
        return user.getPassword();
    }

    @Override
    public AuthDetails getDetails() {
        return authDetails;
    }

    @Override
    public User getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getFirstName() + " " + user.getLastName();
    }
}
