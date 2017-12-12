package dao.impl;

import config.HibernateUtil;
import dao.BookDao;
import model.Book;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by eugen on 11/2/17.
 */
public class HibernateBookDao implements BookDao {
    public List<Book> getAllBooks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Book> list = session.createQuery("from Book").list();
        session.close();
        return list;
    }

    public Integer insert(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(book);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public Book findBookById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Book book = (Book)session.get(Book.class, id);
        session.close();
        return book;
    }
}
