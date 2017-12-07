package service;

import dao.AuthorDao;
import dao.BookDao;
import dao.GenreDao;
import dao.IsbnDao;
import dao.impl.HibernateBookDao;
import dao.impl.IsbnDaoImpl;
import dao.impl.AuthorDaoImpl;
import dao.impl.GenreDaoImpl;
import model.Author;
import model.Book;
import model.Genre;
import model.ISBN;

import java.util.List;

/**
 * Created by eugen on 11/3/17.
 */
public class BookStoreService {

    GenreDao genreDao = new GenreDaoImpl();
    AuthorDao authorDao = new AuthorDaoImpl();
    IsbnDao isbnDao = new IsbnDaoImpl();
    BookDao bookDao = new HibernateBookDao();


    public Author insertAuthor(String name, String surname) {
        Author author = new Author(name,surname);
        Integer id = authorDao.insert(author);
        author.setId(id);
        return author;
    }

    public Genre insertGenre(String name) {
        Genre genre = new Genre(name);
        Integer id = genreDao.insert(genre);
        genre.setId(id);
        return genre;
    }

    public ISBN insertIsbn(String s, String s1, String s2, String s3) {
        ISBN isbn = new ISBN(s,s1,s2,s3);
        Integer id = isbnDao.insert(isbn);
        isbn.setId(id);
        return isbn;
    }

    public Book insertBook(String s, Integer pages, List<Author> authors, Genre comedy, ISBN isbn) {
        Book book = new Book(s,pages,authors,comedy,isbn);
        Integer id = bookDao.insert(book);
        book.setId(id);
        return book;
    }

    public Book getBook(Integer id) {
        return bookDao.findBookById(id);
    }
}
