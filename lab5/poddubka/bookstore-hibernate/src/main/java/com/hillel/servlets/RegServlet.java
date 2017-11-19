package com.hillel.servlets;

import com.hillel.dao.UserDao;
import com.hillel.dao.impl.UserDaoImpl;
import com.hillel.model.User;
import com.hillel.UserDatasource;

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

            //      User user = UserDatasource.setUserData(newName, newPassword);

            User user = new User(newName, newPassword);

            UserDao userDao = new UserDaoImpl(user);

            User newUser = userDao.findByName(newName);


            if (newUser == null) {

                // регистрация...
                // ...

                Integer id = userDao.insertUser(newUser);
                // ...
                session.setAttribute("PRINCIPAL", newUser);
                resp.sendRedirect("/changingListBook.jsp");
                return;

            }

            if (newUser != null && newName.equals(newUser.getUserName())) {
                session.setAttribute("DUBLICATE", user);
                resp.sendRedirect("/dublicateName.jsp");
                return;
            }

        }

        resp.sendRedirect("/registration.html");
    }
}
