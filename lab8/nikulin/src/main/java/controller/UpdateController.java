package controller;

import model.Author;
import model.Book;
import model.Genre;
import model.hibernate.HibernateBookDataAccessObject;
import model.jdbc.JDBCBookDataAccessObject;
import service.BookStoreService;
import util.LoggerClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/books/update")
public class UpdateController extends HttpServlet {

    private Logger logger = LoggerClass.getInstance();
    private BookStoreService bookStoreService = BookStoreService.getBookStoreService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("updateButton") != null){

            String[] authorsNames = req.getParameter("BookAuthor").split(", ");
            ArrayList<Author> authors = new ArrayList<>();

            String[] genresNames = req.getParameter("BookGenre").split(", ");
            ArrayList<Genre> genres = new ArrayList<>();

            for (String s :
                    authorsNames) {
                Author author = new Author();
                author.setName(s);
                authors.add(bookStoreService.insertAuthor(author));
            }
            System.out.println("Authors: " + authors);

            for (String s :
                    genresNames) {
                Genre genre = new Genre();
                genre.setGenrename(s);
                System.out.println(genre);
                genres.add(bookStoreService.insertGenre(genre));
            }

            System.out.println("Genres: " + genres);

            Book book = new Book();
            book.setId(Long.parseLong(req.getParameter("BookId")));
            book.setName(req.getParameter("BookName"));
            book.setPages(Integer.parseInt(req.getParameter("Pages")));
            book.setPublisher(req.getParameter("BookPublisher"));
            book.setPrice(Integer.parseInt(req.getParameter("BookPrice")));
            book.setAuthors(authors);
            book.setGenres(genres);

            boolean isBookUpdated = bookStoreService.updateBook(book);
            if (isBookUpdated) {
                resp.sendRedirect("update.jsp");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " was updated");
            }
            else {
                resp.getWriter().write("FAIL");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " wasn't updated");
            }
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }
}
