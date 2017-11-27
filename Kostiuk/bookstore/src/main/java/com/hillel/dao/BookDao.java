package com.hillel.dao;

import com.hillel.model.Book;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface BookDao extends BasicCRUD<Book> {

    Optional<Book> getEagerStateById(@NonNull Long id);

    List<Book> getByTitle(@NonNull String title);

}
