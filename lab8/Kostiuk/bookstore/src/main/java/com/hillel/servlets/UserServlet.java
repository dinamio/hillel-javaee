package com.hillel.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.hillel.model.Book;
import com.hillel.model.User;
import com.hillel.model.presentation.SimpleBookPresentation;
import com.hillel.service.UserService;
import com.hillel.service.impl.UserServiceImpl;
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

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Utils.requestID(req).orElse(-1L);

        if (id != -1) {
            List<Book> books = userService.getById(id)
                    .map(User::getBooks)
                    .filter(Utils::isNotEpmty)
                    .map(ImmutableList::copyOf)
                    .orElse(ImmutableList.of());

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writeValue(resp.getWriter(), books.stream()
                    .map(book -> SimpleBookPresentation.builder()
                            .author(book.getAuthor().getFullName())
                            .id(book.getId())
                            .pages(book.getPages())
                            .title(book.getTitle())
                            .year(book.getYear())
                            .build())
                    .collect(Collectors.toList()));
            return;
        }

        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/views/users.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.requestID(req).flatMap(userService::getById).ifPresent(userService::delete);
        resp.sendRedirect(req.getRequestURI());
    }

}
