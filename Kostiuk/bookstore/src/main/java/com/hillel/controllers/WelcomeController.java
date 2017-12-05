package com.hillel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import static com.hillel.util.Utils.redirectTo;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public String getWelcomePage(Principal principal) {
        if (principal == null) {
            return redirectTo("start");
        }
        return redirectTo("welcome");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @GetMapping("/start")
    public String start() {
        return "startPage";
    }
}
