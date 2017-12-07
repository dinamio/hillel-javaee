package com.hillel.dao;

import com.hillel.model.Book;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends CrudRepository<Book, Long> {

//    Optional<Book> getEagerStateById(@NonNull Long id);

    List<Book> getByTitle(@NonNull String title);

}
