package com.hillel.bookstorespringboot.dao;

import com.hillel.bookstorespringboot.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDao extends CrudRepository<Author, Integer> {

    Author findByAuthorName(String name);

}
