package controller;

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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/books/delete")
public class DeleteController extends HttpServlet {

    private Logger logger = LoggerClass.getInstance();
    private BookStoreService bookStoreService = BookStoreService.getBookStoreService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("deleteButton") != null){
            boolean isBookDeleted = bookStoreService.removeBookById(Long.parseLong(req.getParameter("BookId")));
            if (isBookDeleted) {
                resp.sendRedirect("delete.jsp");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " was deleted");
            }
            else {
                resp.getWriter().write("FAIL");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " not deleted");
            }
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }
}
