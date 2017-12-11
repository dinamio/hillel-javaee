package edu.karazin.shop.repository;

import edu.karazin.shop.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author getAuthorByName(String name);
}
