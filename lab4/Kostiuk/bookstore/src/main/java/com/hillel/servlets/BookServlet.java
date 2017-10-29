package com.hillel.servlets;

import com.hillel.dao.BookDao;
import com.hillel.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookDao bookDao;

    public BookServlet() {
        bookDao = new BookDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = getRequestBookID(req).orElse(-1L);
        if (id != -1) {
            bookDao.getBookById(id).ifPresent(book -> req.setAttribute("book", book));
            req.getRequestDispatcher("/views/book.jsp").forward(req, resp);
            return;
        }

        List<Book> books = bookDao.getAllBooks();
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

        Integer pagesValue = getNumberOrZero(pages);
        Integer yearValue = getNumberOrZero(year);

        Book book = new Book();
        book.setAuthor(author);
        book.setCountry(country);
        book.setLanguage(language);
        book.setPages(pagesValue);
        book.setTitle(title);
        book.setYear(yearValue);

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
        getRequestBookID(req).ifPresent(bookDao::deleteBookById);
    }

    private Optional<Long> getRequestBookID(HttpServletRequest req) throws IOException {
        return Optional.ofNullable(req.getParameter("id")).map(Long::valueOf);
    }

}
