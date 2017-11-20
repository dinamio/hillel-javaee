package com.hillel.servlets;

import com.hillel.dao.UserDao;
import com.hillel.dao.impl.UserDaoImpl;
import com.hillel.model.User;
import com.hillel.UserDatasource;
import com.hillel.service.BookStoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RegServlet")
public class RegServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String newName = req.getParameter("newName");
        String newPassword = req.getParameter("newPassword");

        if (newName != null && newPassword != null && !newName.equals("") && !newPassword.equals("")) {

            BookStoreService bookStoreService = new BookStoreService();
            boolean checkName = bookStoreService.findByName(newName);

            if (!checkName) {
                // регистрация...
                User user = bookStoreService.insertUser(newName, newPassword);
                session.setAttribute("PRINCIPAL", user);
                resp.sendRedirect("/userListBook.jsp");
                return;

            } else {
                resp.sendRedirect("/dublicateName.jsp");
                return;
            }
        }
        resp.sendRedirect("/registration.html");
    }
}
