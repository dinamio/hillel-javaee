package edu.karazin.shop.web.controller;

import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "profile")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUserProfile(Model model, Principal principal){
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "bookstore-profile";
    }
}
