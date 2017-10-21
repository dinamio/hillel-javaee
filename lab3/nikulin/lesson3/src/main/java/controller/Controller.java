package controller;

import model.Book;
import model.jdbc.JDBCBookDataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@WebServlet("/books")
public class Controller extends HttpServlet {

    private Logger logger = Logger.getLogger(Controller.class.getSimpleName());
    private FileHandler fileHandler = new FileHandler("/home/pikachu/IdeaProjects/lesson3/log_file.log");
    private SimpleFormatter formatter = new SimpleFormatter();


    public Controller() throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.addHandler(fileHandler);
        fileHandler.setFormatter(formatter);
        if (req.getParameter("submitButton") != null){
            Book book = new Book();
            book.setName(req.getParameter("BookName"));
            book.setAuthor(req.getParameter("BookAuthor"));
            book.setPublisher(req.getParameter("BookPublisher"));
            if (JDBCBookDataAccessObject.getJdbcBookDataAccessObject().addNewBook(book)) {
                resp.getWriter().write("DONE");
                logger.log(Level.INFO,"Book " + book + " added");
            }
            else {
                resp.getWriter().write("FAIL");
                logger.log(Level.INFO,"Book " + book + " not added");
            }
            resp.getWriter().flush();
            resp.getWriter().close();
        } else if (req.getParameter("findButton") != null){
            List <Book> books = JDBCBookDataAccessObject.getJdbcBookDataAccessObject().getAllBooks();
            logger.log(Level.INFO,"Showed list of books");
            for (Book book :
                    books) {
                resp.getWriter().write(book.getId() + " ");
                resp.getWriter().write(book.getName() + " ");
                resp.getWriter().write(book.getAuthor() + " ");
                resp.getWriter().write(book.getPublisher() + "\n");

            }
            resp.getWriter().flush();
            resp.getWriter().close();
        } else if (req.getParameter("deleteButton") != null){
            if (JDBCBookDataAccessObject.getJdbcBookDataAccessObject().removeBuId(Long.parseLong(req.getParameter("BookId")))) {
                resp.getWriter().write("DONE");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " was deleted");
            }
            else {
                resp.getWriter().write("FAIL");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " was deleted");
            }
            resp.getWriter().flush();
            resp.getWriter().close();
        } else if (req.getParameter("updateButton") != null){
            Book book = new Book();
            book.setId(Long.parseLong(req.getParameter("BookId")));
            book.setName(req.getParameter("BookName"));
            book.setAuthor(req.getParameter("BookAuthor"));
            book.setPublisher(req.getParameter("BookPublisher"));
            if (JDBCBookDataAccessObject.getJdbcBookDataAccessObject().update(book)) {
                resp.getWriter().write("DONE");
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
