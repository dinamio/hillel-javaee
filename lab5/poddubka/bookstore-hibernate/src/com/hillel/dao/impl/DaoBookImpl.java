package com.hillel.dao.impl;

import com.hillel.dao.BookDao;
import com.hillel.model.Book;

import java.util.List;

public class DaoBookImpl implements BookDao {
    @Override
    public List<Book> getAllBook() {
        return null;
    }

    @Override
    public void insertBook(Book book) {
        String query = "insert into book ";

    }

    @Override
    public Book findBookById(Integer id) {
        final String query = "select * from book where id=" + id;
        System.out.println(query);


        return null;
    }
}
