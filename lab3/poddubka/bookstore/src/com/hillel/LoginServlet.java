package com.hillel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && !username.equals("") && !password.equals("")) {

            User user = UserDatasource.getByUsernameAndPassword(username, password);
            if (user != null) {
                session.setAttribute("PRINCIPAL", user);
                resp.sendRedirect("/changingListBook.jsp");
                return;
            }
        }
        resp.sendRedirect("/registration.html");
    }
}
