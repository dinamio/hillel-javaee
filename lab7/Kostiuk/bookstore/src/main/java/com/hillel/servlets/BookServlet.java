package com.hillel.servlets;

import com.hillel.dao.BookDao;
import com.hillel.dao.impl.hibernate.BookDaoImpl;
import com.hillel.model.Book;
import com.hillel.model.User;
import com.hillel.util.Utils;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static com.hillel.util.Utils.resourceAsBytes;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookDao bookDao;
    private byte[] noImage = resourceAsBytes("noimg.jpg");

    public BookServlet() {
        bookDao = BookDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Utils.requestID(req).orElse(-1L);
        if (id != -1) {
            bookDao.getBookById(id).ifPresent(book -> {
                req.setAttribute("book", book);
                byte[] img = Optional.ofNullable(book.getImage()).orElse(noImage);
                req.setAttribute("image", base64Encoded(img));
            });
            req.getRequestDispatcher("/views/book.jsp").forward(req, resp);
            return;
        }

        List<Book> books = bookDao.getAllBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/views/books.jsp").forward(req, resp);
    }

    private String base64Encoded(byte[] bytes) {
        try {
            byte[] encodeBase64 = Base64.encodeBase64(bytes);
            return new String(encodeBase64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
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

        Book book = Book.builder()
                .year(yearValue)
                .title(title)
                .language(language)
                .author(author)
                .pages(pagesValue)
                .country(country)
                .user(user)
//                .image(image)
                .build();

        bookDao.addNewBook(book);
        resp.sendRedirect(req.getRequestURI());
    }

    private Integer getNumberOrZero(String pages) {
        Integer pagesValue = 0;
        if (!pages.isEmpty()) {
            pagesValue = Integer.valueOf(pages);
        }
        return pagesValue;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.requestID(req).ifPresent(bookDao::deleteBookById);
        resp.sendRedirect(req.getRequestURI());
//        req.getRequestDispatcher(req.getRequestURI()).forward(req, resp);
    }

}
