package controller;

import util.LoggerClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;

@WebServlet("/books/show")
public class ShowController extends HttpServlet {

    private java.util.logging.Logger logger = LoggerClass.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("back") != null){
            resp.sendRedirect("menu.jsp");
            logger.log(Level.INFO,"Books was shown");
        }
    }
}
