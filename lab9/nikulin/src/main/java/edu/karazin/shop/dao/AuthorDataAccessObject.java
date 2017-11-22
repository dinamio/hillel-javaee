package edu.karazin.shop.dao;

import edu.karazin.shop.model.Author;

public interface AuthorDataAccessObject {

    Author createAuthor(Author author);

    Author getAuthorByName(String name);

    Author getAuthorById(long id);
}
