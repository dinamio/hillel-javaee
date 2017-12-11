package com.hillel.dao;

import com.hillel.model.Author;

public interface AuthorDao {

    Author findById(Integer id);

    Integer insertAuthor(Author author);

}
