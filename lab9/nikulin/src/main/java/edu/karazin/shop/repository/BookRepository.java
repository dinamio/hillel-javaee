package edu.karazin.shop.repository;

import edu.karazin.shop.model.BookList;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookList, Long> {
}
