package com.hillel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddServlet extends HttpServlet {

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/changingListBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String addedName = (String) session.getId();

        String book = req.getParameter("book");
        String author = req.getParameter("author");

        if (book != null && author != null && !book.equals("") && !author.equals("")) {

            Book bk = new Book(book, author, addedName);
            BOOK_LIST.add(bk);

            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");

            req.setAttribute("listBook", BOOK_LIST);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/changingListBook.jsp");
        rd.forward(req, resp);
    }
}



