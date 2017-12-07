package com.hillel.bookstorespringboot.dao;

import com.hillel.bookstorespringboot.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorDao extends CrudRepository<Author, Integer> {

  //  List<Author> findAuthorsByAuthorName(List<Author> authorList);

    List<Author> findAuthorsByAuthorName(String authorName);
}
