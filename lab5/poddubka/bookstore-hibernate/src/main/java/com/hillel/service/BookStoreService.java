package com.hillel.service;

import com.hillel.dao.AuthorDao;
import com.hillel.dao.BookDao;
import com.hillel.dao.UserDao;
import com.hillel.dao.impl.AuthorDaoImpl;
import com.hillel.dao.impl.BookDaoImpl;
import com.hillel.dao.impl.UserDaoImpl;
import com.hillel.model.Author;
import com.hillel.model.Book;
import com.hillel.model.User;

import java.util.List;

public class BookStoreService {

    AuthorDao authorDao = new AuthorDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    UserDao userDao = new UserDaoImpl();

    // Author
    public Author insertAuthor(String name) {

        Author author = new Author();
        Integer id = authorDao.insertAuthor(author);
        author.setId(id);

        return author;
    }

    public Author getAuthor(Integer id) {
        return authorDao.findById(id);
    }

    // Book
    public Book insertBook(String bookName, List<Author> authors, User user) {
        Book book = new Book(bookName, authors, user);
        Integer id = bookDao.insertBook(book);
        book.setId(id);

        return book;
    }

    public Book getBook(Integer id) {
        return bookDao.findBookById(id);
    }

    // User
    public User insertUser(String name, String password) {
        User user = new User();
        Integer id = userDao.insertUser(user);
        user.setId(id);
        return user;
    }

    public User getUser(Integer id) {
        return userDao.findById(id);
    }
}
