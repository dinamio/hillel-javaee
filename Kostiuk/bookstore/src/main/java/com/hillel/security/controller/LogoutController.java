package com.hillel.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import static com.hillel.util.Utils.redirectTo;

@Slf4j
@Controller
@RequestMapping("logout")
public class LogoutController extends HttpServlet {


    @RequestMapping
    public String logout(HttpServletResponse resp){
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.addHeader("message", "You are successfully logged out");
        return redirectTo("/");
    }


}
