package com.hillel.service;

import com.hillel.dto.BookFormDto;
import com.hillel.model.Book;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface BookService extends BasicCRUDService<Book> {

    List<Book> getByTitle(@NonNull String title);

    Optional<Book> insertOrUpdate(BookFormDto bookFormDto);

}
