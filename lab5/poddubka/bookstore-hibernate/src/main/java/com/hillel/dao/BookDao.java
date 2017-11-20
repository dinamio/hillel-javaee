package com.hillel.dao;

import com.hillel.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAllBook();

    Integer insertBook(Book book);

    Book findBookById(Integer id);

    void deleteBookById(Integer id);
}
