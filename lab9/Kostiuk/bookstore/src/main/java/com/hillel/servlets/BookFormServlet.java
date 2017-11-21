package com.hillel.servlets;

import com.hillel.service.BookService;
import com.hillel.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebServlet("/books/form")
public class BookFormServlet extends BasicHttpServlet {

    @Autowired
    private BookService bookService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Utils.requestID(req).orElse(-1L);
        if (id == -1) {
            req.getRequestDispatcher("/views/formBook.jsp").forward(req, resp);
        } else {
            bookService.getEagerStateById(id).ifPresent(book -> {
                req.setAttribute("book", id);
                try {
                    req.getRequestDispatcher("/views/updateBookForm.jsp").forward(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
