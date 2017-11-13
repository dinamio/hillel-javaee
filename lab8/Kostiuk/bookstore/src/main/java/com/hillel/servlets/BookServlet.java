package com.hillel.servlets;

import com.hillel.model.Book;
import com.hillel.model.User;
import com.hillel.service.BookService;
import com.hillel.service.UserService;
import com.hillel.service.impl.BookServiceImpl;
import com.hillel.service.impl.UserServiceImpl;
import com.hillel.util.Utils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.hillel.util.Utils.base64Encoded;
import static com.hillel.util.Utils.resourceAsBytes;

@Slf4j
@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookService bookService;
    private final UserService userService;

    private byte[] noImage = resourceAsBytes("noimg.jpg");

    public BookServlet() {
        bookService = BookServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Utils.requestID(req).orElse(-1L);
        if (id != -1) {
            bookService.getEagerStateById(id).ifPresent(book -> {
                req.setAttribute("book", book);
                req.setAttribute("image", base64Encoded(Optional.ofNullable(book.getImage()).orElse(noImage)));
            });
            req.getRequestDispatcher("/views/book.jsp").forward(req, resp);
            return;
        }

        List<Book> books = bookService.getAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/views/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String language = req.getParameter("language");
        String country = req.getParameter("country");
        String pages = req.getParameter("pages");
        String year = req.getParameter("year");
        User user = (User) req.getAttribute("user");

        Integer pagesValue = getNumberOrZero(pages);
        Integer yearValue = getNumberOrZero(year);
        log.info("receive {}", req);

        Optional<Book> book = bookService.insert(language, "", pagesValue, title, yearValue, author, country);
        Optional<User> userAttr = userService.getEagerStateByLogin(user.getLoginName());
        book.ifPresent(b -> {
            bookService.update(b, bk -> bk.setUser(userAttr.get()));
        });
        userAttr.ifPresent(us -> {
            List<Book> books = us.getBooks();
            userService.update(us, u -> books.add(book.get()));
        });

        resp.sendRedirect(req.getRequestURI());
    }

    private Integer getNumberOrZero(String pages) {
        return Optional.ofNullable(pages)
                .map(Integer::valueOf)
                .orElse(0);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.requestID(req).flatMap(bookService::getById).ifPresent(bookService::delete);
        resp.sendRedirect(req.getRequestURI());
    }

}
