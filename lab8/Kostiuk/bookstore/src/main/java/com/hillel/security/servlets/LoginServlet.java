package com.hillel.security.servlets;

import com.hillel.dao.UserDao;
import com.hillel.dao.impl.hibernate.UserDaoImpl;
import com.hillel.model.User;
import com.hillel.security.AuthHelper;
import com.hillel.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.hillel.util.Utils.isValidInput;
import static java.time.Duration.ofMinutes;

@Slf4j
@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    private UserDao userDao;
    private Message message;

    @Override
    public void init() throws ServletException {
        userDao = UserDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional.ofNullable(message)
                .ifPresent(message -> req.setAttribute("message", message));
        req.getRequestDispatcher("/views/formLogin.jsp").forward(req, resp);
        message = null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        if (isValidInput(login) && isValidInput(password) && checkPassword(password, login)) {
            AuthHelper.generateToken(login, ofMinutes(20)).ifPresent(token -> {
                log.info("Token: {}", token);
                try {
                    Cookie cookie = new Cookie("token", token);
                    resp.addCookie(cookie);
                    resp.sendRedirect(req.getContextPath() + "/welcome");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            message = new Message("Login or/and encodedPassword are incorrect");
            resp.sendRedirect(req.getRequestURI());
        }
    }


    private Boolean checkPassword(String password, String login) {
        return userDao.getByLogin(login)
                .map(User::getEncodedPassword)
                .map(psw -> BCrypt.checkpw(password, psw))
                .orElse(false);
    }

}
