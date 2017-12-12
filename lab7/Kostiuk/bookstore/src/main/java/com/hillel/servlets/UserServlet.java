package com.hillel.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.dao.BookDao;
import com.hillel.dao.UserDao;
import com.hillel.dao.impl.hibernate.BookDaoImpl;
import com.hillel.dao.impl.hibernate.UserDaoImpl;
import com.hillel.model.Book;
import com.hillel.model.presentation.SimpleBookPresentation;
import com.hillel.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserDao userDao;
    private BookDao bookDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDaoImpl.getInstance();
        bookDao = BookDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Utils.requestID(req).orElse(-1L);

        if (id != -1) {
            List<Book> books = bookDao.getBooksByUser(id);
            ObjectMapper objectMapper = new ObjectMapper();


            objectMapper.writeValue(resp.getWriter(),
                    books.stream().map(book -> SimpleBookPresentation.builder()
                            .author(book.getAuthor())
                            .id(book.getId())
                            .pages(book.getPages())
                            .title(book.getTitle())
                            .build())
                            .collect(Collectors.toList()));
            return;
        }

        req.setAttribute("users", userDao.getAllUsers());
        req.getRequestDispatcher("/views/users.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.requestID(req).ifPresent(userDao::deleteUserById);
        resp.sendRedirect(req.getRequestURI());
//        req.getRequestDispatcher(req.getRequestURI()).forward(req, resp);
    }

}
