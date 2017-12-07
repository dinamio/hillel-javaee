package controller;

import model.User;
import model.hibernate.HibernateUserDataAccessObject;
import model.jdbc.JDBCUserDataAccessObject;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/books/login")
public class LoginController extends HttpServlet {

    private UserService userService = UserService.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("signIn") != null){
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(9999999);
            resp.sendRedirect("menu.jsp");
        }

        if (req.getParameter("signUp") != null){
            User user = new User();
            user.setUlogin(req.getParameter("login"));
            user.setUpassword(req.getParameter("password"));
            userService.createUser(user);
            resp.sendRedirect("login.jsp");
        }
    }
}
