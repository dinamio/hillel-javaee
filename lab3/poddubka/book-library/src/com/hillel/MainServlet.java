package com.hillel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {

    public static List<Book> listBook = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/listBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

   //     int bookId = Integer.parseInt(req.getParameter("bookId"));
        String book = req.getParameter("book");
        String author = req.getParameter("author");

        Book bk = new Book(book, author);
        listBook.add(bk);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("listBook", listBook);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/listBook.jsp");
        rd.forward(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}



