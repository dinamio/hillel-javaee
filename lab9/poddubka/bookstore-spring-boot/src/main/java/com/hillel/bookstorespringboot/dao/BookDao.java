package com.hillel.bookstorespringboot.dao;

import com.hillel.bookstorespringboot.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<Book, Integer> {

    Book findBookByBookName(String bookName);
}
