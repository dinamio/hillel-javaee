package com.hillel.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hillel.filters.BasicHttpFilter;
import com.hillel.security.AuthHelper;
import com.hillel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@WebFilter({"/books/*", "/welcome", "/users/*"})
@Component
public class SecurityFilter implements BasicHttpFilter {

    @Autowired
    private UserService userService;

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        DecodedJWT jwt = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token"))
                .findFirst()
                .map(Cookie::getValue)
                .flatMap(AuthHelper::verifyToken)
                .orElse(null);
        if (jwt != null) {
            String login = jwt.getClaim("login").asString();
            Date expDate = jwt.getExpiresAt();
            String expDateForm = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(expDate);
            log.info("Login: {}", login);
            log.info("Date exp: {}", expDateForm);
            userService.getByLogin(login).ifPresent(user -> {
                request.setAttribute("expDate", expDateForm);
                request.setAttribute("user", user);
            });
            doFilter(chain, request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private void doFilter(FilterChain chain, HttpServletRequest request, HttpServletResponse response) {
        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
