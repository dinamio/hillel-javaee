package edu.karazin.shop.web;

import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loadLogin() {
        return "bookstore-login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest){

        String page = "bookstore-login";

        if (httpServletRequest.getParameter("signIn") != null){
            String login = httpServletRequest.getParameter("login");
            String password = httpServletRequest.getParameter("password");

            boolean isCorrectData = userService.getUser(login, password);

            if (isCorrectData) page = "redirect:/books/menu";
        }
        else {

            String login = httpServletRequest.getParameter("login");
            String password = httpServletRequest.getParameter("password");

            User user = new User();
            user.setUlogin(login);
            user.setUpassword(password);

            userService.createUser(user);
        }

        return page;
    }
}
