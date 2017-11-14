package controller;

import model.User;
import model.hibernate.HibernateUserDataAccessObject;
import model.jdbc.JDBCUserDataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("signIn") != null){
            resp.sendRedirect("menu.jsp");
        } if (req.getParameter("signUp") != null){
            User user = new User();
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            JDBCUserDataAccessObject.getJdbcUserDataAccessObject().createUser(user);
//            HibernateUserDataAccessObject.getHibernateUserDataAccessObject().createUser(user);
            resp.sendRedirect("login.jsp");
        }
    }
}
