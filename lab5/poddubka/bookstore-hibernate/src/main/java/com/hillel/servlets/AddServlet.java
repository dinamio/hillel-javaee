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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddServlet extends HttpServlet {

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    public static final BookStoreService BOOK_STORE_SERVICE = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/changingListBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User addedName = (User)session.getAttribute("PRINCIPAL");

        String bookName = req.getParameter("book");
        String authorName = req.getParameter("author");

        if (bookName != null && authorName != null && !bookName.equals("") && !authorName.equals("")) {

            // записываем book и author в базу
            BOOK_STORE_SERVICE.insertAuthor(authorName);

            List<Author> listAuthor = null;
            BOOK_STORE_SERVICE.insertBook(bookName, listAuthor, addedName);

//            Author author = new Author(authorName);
//            AuthorDao authorDao = new AuthorDaoImpl();
//            Integer id = authorDao.insertAuthor(author);
//
//            Book book = new Book(bookName);
//            book.setBookName(bookName);
//            book.setId(id);
//            BookDao bookDao = new BookDaoImpl();
//            bookDao.insertBook(book);
//
//            BOOK_LIST.add(book);


            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");

            req.setAttribute("listBook", BOOK_LIST);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/changingListBook.jsp");
        rd.forward(req, resp);
    }

//    private static List<Author> parseAuthors(String authorNames) {
//        Author author = new Author();
//        List<Author> list = null;
//
//        StringBuilder sb = new StringBuilder(authorNames);
//        StringBuilder resault = new StringBuilder();
//        for (int i = 0; i < sb.length() - 1; i++) {
//            if (sb.charAt(i) == ',') {
//                author.setAuthorName(resault.toString());
//                list.add(author);
//                resault = new StringBuilder();
//            } else {
//                resault.append(resault.charAt(i));
//            }
//        }
//        return list;
//
//    }
}



