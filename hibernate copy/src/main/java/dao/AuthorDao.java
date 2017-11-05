package dao;

import model.Author;

/**
 * Created by eugen on 11/3/17.
 */
public interface AuthorDao {

    Author findById(Integer id);

    Integer insert(Author author);
}
