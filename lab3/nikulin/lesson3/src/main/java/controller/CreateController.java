package controller;

import model.Book;
import model.jdbc.JDBCBookDataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@WebServlet("/books/add")
public class CreateController extends HttpServlet {


    private Logger logger = controller.Logger.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        } else if (req.getParameter("back") != null){
            resp.sendRedirect("menu.jsp");
        }
    }
}
