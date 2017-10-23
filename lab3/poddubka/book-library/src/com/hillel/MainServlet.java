package com.hillel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {

    private final List<Book> lb = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//
//        PrintWriter writer = resp.getWriter();
//        writer.println("<h3>List of books</h3>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String book = req.getParameter("book");
        String author = req.getParameter("author");

        Book bk = new Book(bookId, book, author);
        lb.add(bk);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        for (Book b : lb) {
            sb.append(b.toString() + "</br>");
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<h3>List of books</h3>");
        writer.println(sb);
    }
}



