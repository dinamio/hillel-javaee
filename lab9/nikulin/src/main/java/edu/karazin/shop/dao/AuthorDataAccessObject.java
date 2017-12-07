package edu.karazin.shop.dao;

import edu.karazin.shop.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDataAccessObject extends CrudRepository<Author, Integer> {

    Author getAuthorByName(String name);
}
