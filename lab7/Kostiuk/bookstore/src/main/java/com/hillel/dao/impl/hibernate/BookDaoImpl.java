package com.hillel.dao.impl.hibernate;

import com.hillel.dao.BookDao;
import com.hillel.model.Book;
import com.hillel.repository.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BookDaoImpl implements BookDao {

    static volatile BookDaoImpl instance;

    public static BookDaoImpl getInstance() {
        if (instance == null) {
            synchronized (BookDaoImpl.class) {
                if (instance == null) {
                    instance = new BookDaoImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public List<Book> getAllBooks() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.createQuery("from Book").list();
        log.info("Get all books {}, last: {}", books.size(), books.get(books.size() - 1).getId());
        transaction.commit();
        return books;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        transaction.commit();
        return Optional.of(book);
    }

    @Override
    public void deleteBookById(Long id) {
        log.info("Delete book by id {}", id);
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Book WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.flush();
        transaction.commit();
    }

    @Override
    public Optional<Book> addNewBook(Book book) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(book);
        book.setId(id);
        session.flush();
        transaction.commit();
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> getBooksByUser(Long userId) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.createQuery("from Book where user_id = :userId")
                .setParameter("userId", userId)
                .list();
        transaction.commit();
        return books;
    }
}
