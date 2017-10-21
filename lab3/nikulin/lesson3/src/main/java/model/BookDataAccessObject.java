package model;

import java.util.List;

public interface BookDataAccessObject {
    boolean addNewBook(Book book);
    Book getBookById(long id);
    boolean removeBuId(long id);
    boolean update(Book book);
    List<Book> getAllBooks();
}
