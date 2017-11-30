package edu.karazin.shop.web;

import edu.karazin.shop.model.User;
import edu.karazin.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("session")
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loadLogin(Model model) {
        model.addAttribute("userData", new User());
        return "bookstore-login";
    }
}
