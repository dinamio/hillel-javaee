package database.dao;

import database.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();
    void addBook(Book arg);
    void deleteBook(String id);
}
