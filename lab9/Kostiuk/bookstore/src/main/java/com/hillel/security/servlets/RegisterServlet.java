package com.hillel.security.servlets;

import com.hillel.model.User;
import com.hillel.service.UserService;
import com.hillel.util.Message;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.hillel.util.Utils.isValidInput;

@WebServlet("/register")
@Component
public class RegisterServlet extends HttpServlet {


    private final UserService userService;
    private       Message     message;

    @Autowired
    public RegisterServlet(UserService userService) {
        this.userService = userService;
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
                userService.insert(user);
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
        return !userService.getByLogin(login).isPresent();
    }

    private String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
