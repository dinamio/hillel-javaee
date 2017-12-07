package controller;

import model.jdbc.JDBCBookDataAccessObject;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("deleteButton") != null){
            if (JDBCBookDataAccessObject.getJdbcBookDataAccessObject().removeById(Long.parseLong(req.getParameter("BookId")))) {
                resp.getWriter().write("DONE");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " was deleted");
            }
            else {
                resp.getWriter().write("FAIL");
                logger.log(Level.INFO,"Book with id " + req.getParameter("BookId") + " was deleted");
            }
            resp.getWriter().flush();
            resp.getWriter().close();
        } else if (req.getParameter("back") != null){
            resp.sendRedirect("menu.jsp");
        }
    }
}
