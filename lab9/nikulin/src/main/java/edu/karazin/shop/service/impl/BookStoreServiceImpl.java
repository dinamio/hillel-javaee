package edu.karazin.shop.service.impl;

import edu.karazin.shop.dao.AuthorDataAccessObject;
import edu.karazin.shop.dao.BookDataAccessObject;
import edu.karazin.shop.dao.GenreDataAccessObject;
import edu.karazin.shop.model.Author;
import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.Genre;
import edu.karazin.shop.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private AuthorDataAccessObject authorDataAccessObject;

    @Autowired
    private GenreDataAccessObject genreDataAccessObject;

    @Autowired
    private BookDataAccessObject bookDataAccessObject;

    @Override
    public Author insertAuthor(Author author) {
        Author currentAuthor = authorDataAccessObject.getAuthorByName(author.getName());
        if (Objects.equals(currentAuthor, null)) {
            author = authorDataAccessObject.save(author);
            return author;
        } else return currentAuthor;

    }

    @Override
    public Genre insertGenre(Genre genre) {
        Genre currentGenre = genreDataAccessObject.getGenreByGenrename(genre.getGenrename());
        if (Objects.equals(currentGenre, null)) {
            genre = genreDataAccessObject.save(genre);
            return genre;
        } else return currentGenre;
    }

    @Override
    public void insertBook(BookList bookList) {
        bookDataAccessObject.save(bookList);
    }

    @Override
    public List<BookList> getBookListByGenre(String genre) {
        if (genre != null){
            if (!Objects.equals(genre, "")) return genreDataAccessObject.getGenreByGenrename(genre).getBookLists();
            else return (List<BookList>) bookDataAccessObject.findAll();
        }
        else return (List<BookList>) bookDataAccessObject.findAll();
    }

    @Override
    public List<BookList> getAllBooks() {
        return (List<BookList>) bookDataAccessObject.findAll();
    }

    @Override
    public BookList getBookById(Long id) {
        return bookDataAccessObject.findOne(id);
    }

    @Override
    public void updateBook(BookList bookList) {
        bookDataAccessObject.save(bookList);
    }

    @Override
    public void deleteBook(Long id) { bookDataAccessObject.delete(id);}

    @Override
    public List<String> getGenreNames(String genreName){
        List<Genre> genreList = (List<Genre>) genreDataAccessObject.findAll();
        List<String> genres = new ArrayList<>();
        for (Genre genre :
             genreList) {
            if (genre.getGenrename().toLowerCase().contains(genreName.toLowerCase())) genres.add(genre.getGenrename());
        }
        return genres;
    }
}
