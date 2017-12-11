package edu.karazin.shop.service;

import edu.karazin.shop.model.Author;
import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.Genre;

import java.util.List;

public interface BookStoreService {
    Author insertAuthor(Author author);

    Genre insertGenre(Genre genre);

    void insertBook(BookList bookList);

    List<BookList> getBookListByGenre(String genre);

    BookList getBookById(Long id);

    void updateBook(BookList bookList);

    void deleteBook(Long id);

    List<String> getGenreNames(String genreName);

    List<String> getGenreNames();
}
