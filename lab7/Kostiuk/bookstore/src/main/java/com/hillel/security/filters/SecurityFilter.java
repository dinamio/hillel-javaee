package com.hillel.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hillel.dao.UserDao;
import com.hillel.dao.impl.hibernate.UserDaoImpl;
import com.hillel.filters.BasicHttpFilter;
import com.hillel.security.AuthHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
public class SecurityFilter implements BasicHttpFilter {

    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = UserDaoImpl.getInstance();
    }

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
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
            userDao.getUserByLogin(login).ifPresent(user -> {
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
