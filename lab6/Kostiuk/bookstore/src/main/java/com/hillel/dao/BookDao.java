package com.hillel.dao;

import com.hillel.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    void deleteBookById(Long id);

    Optional<Object> addNewBook(Book book);

    List<Book> getBooksByUser(Long userId);
}
