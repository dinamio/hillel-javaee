package com.hillel.security.controller;

import com.hillel.model.User;
import com.hillel.service.UserService;
import com.hillel.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hillel.security.AuthHelper.encodePassword;
import static com.hillel.util.Utils.isValidInput;
import static com.hillel.util.Utils.redirectTo;

@Controller
@RequestMapping("user/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "formNewUser";
    }

    @PostMapping
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model) {


        String login = user.getLoginName();
        boolean isNew = isValidInput(login) && isNonExist(login);
        if (isNew) {
            user.setEncodedPassword(encodePassword(user.getEncodedPassword()));
            userService.insert(user);
            redirectAttributes.addFlashAttribute("registerMessage", new Message("User with [ " + login + " ] was successfully registered"));
            return redirectTo("/start");
        } else {
            model.addAttribute("message", new Message("Login: [ " + login + " ] already exists, please try another one"));
            return "formNewUser";
        }
    }

    private boolean isNonExist(String login) {
        return !userService.getByLogin(login).isPresent();
    }

}