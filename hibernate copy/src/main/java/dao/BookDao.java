package dao;

import model.Book;

import java.util.List;

/**
 * Created by eugen on 10/29/17.
 */
public interface BookDao {

    List<Book> getAllBooks();

    Integer insert(Book book);

    Book findBookById(Integer id);

}
