package edu.karazin.shop.web;

import edu.karazin.shop.model.User;
import edu.karazin.shop.util.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "profile")
public class UserController {

    @Autowired
    private UserData userData;

    @GetMapping
    public String getUserProfile(Model model){
        User user = userData.getUser();
        model.addAttribute("user", user);
        return "bookstore-profile";
    }
}
