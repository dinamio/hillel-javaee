package controller;


import database.dao.UserDao;
import database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;

import static utils.Encryption.cryptWithMD5;


@Controller
@RequestMapping(path ="/unregistered/registerController")
public class RegisterController extends HttpServlet {
    @Autowired
    UserDao userDao;

    @RequestMapping(method = RequestMethod.POST)
    protected String registerUser(@RequestParam(name = "uname") String username,
                                  @RequestParam(name = "psw") String password) {
        if(userDao.findOne(username) == null){
            User user = new User();
            user.setUsername(username);
            user.setPassHex(cryptWithMD5(password));
            userDao.save(user);
            return "redirect:/";
        }else {
            return "UserExist";
        }
    }
}
