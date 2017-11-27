package com.hillel.security.controller;

import com.hillel.dto.UserSecurityDto;
import com.hillel.model.User;
import com.hillel.security.AuthHelper;
import com.hillel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.hillel.util.Utils.isValidInput;
import static com.hillel.util.Utils.redirectTo;
import static java.time.Duration.ofMinutes;

@Slf4j
@Controller
@RequestMapping("login")
public class LoginController {

    private final static long DEFAULT_TOKEN_LIFETIME_MINUTES = 20;

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    @GetMapping
    public String loginPage() {
        return "formLogin";
    }

    @PostMapping
    public String userLogin(UserSecurityDto userSecurityDto, HttpServletResponse resp, RedirectAttributes redirectAttributes) {
        String login = userSecurityDto.getLogin();
        String password = userSecurityDto.getPassword();

        if (isValidInput(login) && isValidInput(password) && checkPassword(password, login)) {
            AuthHelper.generateToken(login, ofMinutes(getLifetime())).ifPresent(token -> {
                log.info("Token: {}", token);
                Cookie cookie = new Cookie("token", token);
                resp.addCookie(cookie);
            });
            return redirectTo("/");
        } else {
            redirectAttributes.addFlashAttribute("loginMessage", "Login or/and encodedPassword are incorrect");
            return redirectTo("login");
        }
    }

    private long getLifetime() {
        String lifetime = environment.getProperty("security-token-lifetime-minutes");
        try {
            return Long.parseLong(lifetime);
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
        return DEFAULT_TOKEN_LIFETIME_MINUTES;
    }


    private Boolean checkPassword(String password, String login) {
        return userService.getByLogin(login)
                .map(User::getEncodedPassword)
                .map(psw -> BCrypt.checkpw(password, psw))
                .orElse(false);
    }

}
