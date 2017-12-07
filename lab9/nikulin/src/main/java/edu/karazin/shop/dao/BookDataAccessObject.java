package edu.karazin.shop.dao;

import edu.karazin.shop.model.BookList;
import org.springframework.data.repository.CrudRepository;

public interface BookDataAccessObject extends CrudRepository<BookList, Long> {
    BookList getBookByName(String name);
}
