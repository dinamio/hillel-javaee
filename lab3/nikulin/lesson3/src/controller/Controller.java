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

@WebServlet("/books")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("submitButton") != null){
            Book book = new Book();
            book.setName(req.getParameter("BookName"));
            book.setAuthor(req.getParameter("BookAuthor"));
            book.setPublisher(req.getParameter("BookPublisher"));
            if (JDBCBookDataAccessObject.getJdbcBookDataAccessObject().addNewBook(book)) resp.getWriter().write("DONE");
            else resp.getWriter().write("FAIL");
            resp.getWriter().flush();
            resp.getWriter().close();
        } else if (req.getParameter("findButton") != null){
            List <Book> books = JDBCBookDataAccessObject.getJdbcBookDataAccessObject().getAllBooks();
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
            if (JDBCBookDataAccessObject.getJdbcBookDataAccessObject().removeBuId(Long.parseLong(req.getParameter("BookId")))) resp.getWriter().write("DONE");
            else resp.getWriter().write("FAIL");
            resp.getWriter().flush();
            resp.getWriter().close();
        } else if (req.getParameter("updateButton") != null){
            Book book = new Book();
            book.setId(Long.parseLong(req.getParameter("BookId")));
            book.setName(req.getParameter("BookName"));
            book.setAuthor(req.getParameter("BookAuthor"));
            book.setPublisher(req.getParameter("BookPublisher"));
            if (JDBCBookDataAccessObject.getJdbcBookDataAccessObject().update(book)) resp.getWriter().write("DONE");
            else resp.getWriter().write("FAIL");
            resp.getWriter().flush();
            resp.getWriter().close();
        }
    }
}
