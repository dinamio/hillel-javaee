package edu.karazin.shop.dao.hibernate;

import edu.karazin.shop.model.Book;
import edu.karazin.shop.dao.BookDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import edu.karazin.shop.util.SessionFactoryImpl;

import java.util.List;

public class HibernateBookDataAccessObject implements BookDataAccessObject {

    private static HibernateBookDataAccessObject hibernateBookDataAccessObject;
    private SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();

    private HibernateBookDataAccessObject(){}

    public static HibernateBookDataAccessObject getHibernateBookDataAccessObject() {
        if (hibernateBookDataAccessObject != null) return hibernateBookDataAccessObject;
        else {
            hibernateBookDataAccessObject = new HibernateBookDataAccessObject();
            return hibernateBookDataAccessObject;
        }
    }

    @Override
    public boolean addNewBook(Book book) {
        Session session = null;
        try {
            session = SessionFactoryImpl.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    @Override
    public Book getBookById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("select b from Book b where b.id = :id");
            query.setParameter("id", id);
            transaction.commit();
            return (Book) query.uniqueResult();
        } catch (Exception e){
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean removeById(long id) {
        Book book = getBookById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(book);
            transaction.commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(book);
            transaction.commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from Book b";
            Query query = session.createQuery(hql);
            return query.getResultList();
        } finally {
            transaction.commit();
            session.close();
        }

    }

    @Override
    public Book getBookByName(String name){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "from Book b where b.name = '" + name + "'";
            Query query = session.createQuery(hql);
            Book book = (Book) query.uniqueResult();
            session.getTransaction().commit();
            return book;
        }
    }
}
