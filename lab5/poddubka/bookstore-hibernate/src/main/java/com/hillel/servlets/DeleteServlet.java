package com.hillel.servlets;

import com.hillel.service.BookStoreService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    BookStoreService bookStoreService;


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
        int indexBook = Integer.parseInt(button.substring(6));

        // удаление книги по id
        bookStoreService.deleteBook(indexBook);

        req.setAttribute("listBook", AddServlet.book); // book = null ???

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/userListBook.jsp");
        rd.forward(req, resp);
    }

}

