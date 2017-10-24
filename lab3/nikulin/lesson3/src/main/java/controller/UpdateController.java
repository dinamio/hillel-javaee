package controller;

import model.Book;
import model.jdbc.JDBCBookDataAccessObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/books/update")
public class UpdateController extends HttpServlet {

    private Logger logger = controller.Logger.getInstance();


    public UpdateController() throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("updateButton") != null){
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
        } else if (req.getParameter("back") != null){
            resp.sendRedirect("menu.jsp");
        }
    }
}
