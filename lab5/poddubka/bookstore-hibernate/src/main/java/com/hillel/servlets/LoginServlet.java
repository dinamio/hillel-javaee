package com.hillel.servlets;

import com.hillel.dao.impl.UserDaoImpl;
import com.hillel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && !username.equals("") && !password.equals("")) {

            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.findByName(username);

            if(user != null && user.getUserPassword().equals(password)) {
                session.setAttribute("PRINCIPAL", user);
                resp.sendRedirect("/userListBook.jsp");
                return;
            }

        }
        resp.sendRedirect("/registration.html");
    }
}
