package com.hillel.security.servlets;

import com.hillel.dao.UserDao;
import com.hillel.dao.impl.hibernate.UserDaoImpl;
import com.hillel.model.User;
import com.hillel.util.Message;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.hillel.util.Utils.isValidInput;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    private UserDao userDao;
    private Message message;

    @Override
    public void init() throws ServletException {
        userDao = UserDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional.ofNullable(message).ifPresent(message -> req.setAttribute("message", message));
        req.getRequestDispatcher("/views/formNewUser.jsp").forward(req, resp);
        message = null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        boolean isNew = isValidInput(login) && isNonExist(login);
        if (isNew) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String password = req.getParameter("password");

            if (isValidInput(firstName) && isValidInput(lastName) && isValidInput(password)) {
                User user = User.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .loginName(login)
                        .encodedPassword(encodePassword(password))
                        .build();
                userDao.addNewUser(user);
                resp.sendRedirect(req.getContextPath() + "/welcome");
            } else {
                message = new Message("Please fill all input fields");
                resp.sendRedirect(req.getRequestURI());
            }
        } else {
            message = new Message("Login: [ " + login + " ] already exists, please try another one");
            resp.sendRedirect(req.getRequestURI());
        }
    }

    private boolean isNonExist(String login) {
        return !userDao.getUserByLogin(login).isPresent();
    }

    private String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
