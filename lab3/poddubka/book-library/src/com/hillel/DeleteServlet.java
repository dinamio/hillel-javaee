package com.hillel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/listBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String button = req.getParameter("button");

        int indexBook = Integer.parseInt(button);

        MainServlet.listBook.remove(indexBook);

        req.setAttribute("listBook", MainServlet.listBook);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/listBook.jsp");
        rd.forward(req, resp);
    }

}

