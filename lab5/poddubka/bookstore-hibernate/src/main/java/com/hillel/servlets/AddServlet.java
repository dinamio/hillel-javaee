package com.hillel.servlets;

import com.hillel.dao.AuthorDao;
import com.hillel.dao.BookDao;
import com.hillel.dao.impl.AuthorDaoImpl;
import com.hillel.dao.impl.BookDaoImpl;
import com.hillel.model.Author;
import com.hillel.model.User;
import com.hillel.model.Book;
import com.hillel.service.BookStoreService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {

//    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public BookStoreService bookStoreService;
    public static final Book book = null; //??? static final ???

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/userListBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User addedName = (User)session.getAttribute("PRINCIPAL");

        String bookName = req.getParameter("book");
        String authorName = req.getParameter("author");

        if (bookName != null && authorName != null && !bookName.equals("") && !authorName.equals("")) {

            // парсим авторов
            List<String> authors = stringToList(authorName);

            // записываем автора(ов) в  лист и базу
            List<Author> listAuthor = new ArrayList<>();
            for (String s: authors) {
                Author author = new Author(s);
                listAuthor.add(author);
                bookStoreService.insertAuthor(s);
            }

            // записываем book и author в базу
            bookStoreService.insertBook(bookName, listAuthor, addedName);

            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");

            BookDao bookDao = new BookDaoImpl();
            List<Book> bookList = bookDao.getAllBook();

            req.setAttribute("listBook", bookList);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/userListBook.jsp");
        rd.forward(req, resp);
    }

    public static List<String> stringToList(String str) {
        String[] list = str.split(",");
        List<String> resault = new ArrayList<>(Arrays.asList(list));
        return resault;
    }

    public static String listToString(List<String> list) {
        StringBuilder sb  = new StringBuilder();
        for (String a: list) {
            sb.append(a);
            sb.append("\n");
        }
        return sb.toString();
    }
}



