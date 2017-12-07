package edu.karazin.shop.util;

import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserData {

    @Autowired
    private UserService userService;

    public edu.karazin.shop.model.User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() : null;

        if (myUser instanceof UserDetails) {
            UserDetails sessionUser = (UserDetails) myUser;
            return userService.getUserByName(sessionUser.getUsername());
        } else return null;
    }
}
