package com.hillel.service;

import com.hillel.model.Book;
import com.hillel.model.Writer;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface BookService extends BasicCRUDService<Book> {

    Optional<Book> getEagerStateById(@NonNull Long id);

    List<Book> getByTitle(@NonNull String title);

    Optional<Book> insert(String language, String link, int pages, String title, int year, String author, String country);

}
