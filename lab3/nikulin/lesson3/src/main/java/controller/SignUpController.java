package controller;

import model.User;
import model.jdbc.JDBCUserDataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/signup")
public class SignUpController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("create") != null){
            User user = new User();
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            if (JDBCUserDataAccessObject.getJdbcUserDataAccessObject().createUser(user)) resp.getWriter().write("DONE");
            else resp.getWriter().write("FAIL");
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }
}
