package model.hibernate;

import model.Book;
import model.BookDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.SessionFactoryImplementation;

import java.util.List;

public class HibernateBookDataAccessObject implements BookDataAccessObject {

    private static HibernateBookDataAccessObject hibernateBookDataAccessObject;
    private SessionFactory sessionFactory = SessionFactoryImplementation.getSessionFactory();

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
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(book);
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Book getBookById(long id) {
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("select b from Book b where b.id = :id");
            query.setParameter("id", id);
            session.close();
            return (Book) query.uniqueResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean removeById(long id) {
        try {
            Book book = getBookById(id);
            Session session = sessionFactory.openSession();
            session.remove(book);
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(Book book) {
        try{
            Session session = sessionFactory.openSession();
            session.update(book);
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession().createQuery("from Book b").list();
    }
}
