package com.hillel.security.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hillel.security.AuthHelper;
import com.hillel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
            log.info("Handler: {}", handler);
            userService.getByLogin(login).ifPresent(user -> {
                request.setAttribute("expDate", expDateForm);
                request.setAttribute("user", user);
            });
            return true;
        } else {
            response.sendRedirect("/start");
            return false;
        }
    }

}
