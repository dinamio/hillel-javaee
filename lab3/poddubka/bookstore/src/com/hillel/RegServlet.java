package com.hillel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String newName = req.getParameter("newName");
        String newPassword = req.getParameter("newPassword");



        if (newName != null && newPassword != null && !newName.equals("") && !newPassword.equals("")) {

            User user = UserDatasource.setUserData(newName, newPassword);
            if (user != null) {
                session.setAttribute("PRINCIPAL", user);
                resp.sendRedirect("/changingListBook.jsp");
                return;
            }
        }
        resp.sendRedirect("/registration.html");
    }
}
