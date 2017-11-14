package model;

import java.util.List;

public interface BookDataAccessObject {
    boolean addNewBook(Book book);
    Book getBookById(long id);
    Book getBookByName(String name);
    boolean removeById(long id);
    boolean update(Book book);
    List<Book> getAllBooks();
}
