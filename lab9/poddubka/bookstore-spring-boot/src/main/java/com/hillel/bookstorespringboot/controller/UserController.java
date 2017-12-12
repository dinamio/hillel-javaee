package com.hillel.bookstorespringboot.controller;

import com.hillel.bookstorespringboot.dao.UserDao;
import com.hillel.bookstorespringboot.dto.UserDTO;
import com.hillel.bookstorespringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("security")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        model.addAttribute("user", new UserDTO());

        return "security/registration";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String registrationUser(@ModelAttribute(name = "user") UserDTO userDTO) {
        String userName = userDTO.getUserName();
        if(userName.trim().isEmpty()) {

            return "redirect:/security/registration";
        }
        if(userDao.findUserByUserName(userName) != null) {

            return "security/errorRegistration";
        }
        String userPassword = userDTO.getUserPassword();
        String userRole = "USER";
        User user = new User(userName, true, userRole);
        user.setUserPassword(passwordEncoder.encode(userPassword));
        userDao.save(user);

        return "redirect:/book";
    }
}
