package com.hillel.servlets;

import com.hillel.service.BookService;
import com.hillel.service.WriterService;
import com.hillel.service.impl.BookServiceImpl;
import com.hillel.service.impl.WriterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/form")
public class BookFormServlet extends HttpServlet {

    @SuppressWarnings("unused")
    private BookService   bookService;
    @SuppressWarnings("unused")
    private WriterService writerService;


    @Override
    public void init() throws ServletException {
        bookService = BookServiceImpl.getInstance();
        writerService = WriterServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/formBook.jsp").forward(req, resp);
    }
}
