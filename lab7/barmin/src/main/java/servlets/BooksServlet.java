package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.dao.impl.BookDaoImpl;
import database.dao.impl.UserDaoImpl;
import database.dto.BookDTO;
import database.model.Author;
import database.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static servlets.LoginServlet.currentSessions;


@WebServlet(value = "/booksServlet")
public class BooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List list =  BookDTO.booksToDTO(new BookDaoImpl().getAllBooks());
        String json = new Gson().toJson(list);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bname");
        String[] names = req.getParameterValues("bauthorName");
        String[] surnames = req.getParameterValues("bauthorSurname");
        Book book = new Book();
        book.setName(bookName);
        Set<Author> authors = new HashSet<>();
        for(int i = 0; i<names.length; i++){
            Author temp = new Author();
            temp.setName(names[i]);
            temp.setSurname(surnames[i]);
            authors.add(temp);
        }
        book.setAuthors(authors);
        book.setUser_id(new UserDaoImpl().getUserByID(currentSessions.get(req.getSession().getId())));
        new BookDaoImpl().addBook(book);
        req.getRequestDispatcher("/view/registered/welcome.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String bookId = data.get("id").getAsString();
        new BookDaoImpl().deleteBook(bookId);

    }
}
