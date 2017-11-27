package com.hillel.controllers;

import com.hillel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.hillel.util.Utils.redirectTo;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }


    @DeleteMapping
    public String deleteUser(@RequestParam("id") Long id) throws ServletException, IOException {
        userService.getById(id).ifPresent(userService::delete);
        return redirectTo("users");
    }

}
