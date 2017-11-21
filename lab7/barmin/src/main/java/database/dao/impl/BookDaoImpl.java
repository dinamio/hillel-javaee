package database.dao.impl;

import database.config.HibernateUtil;
import database.dao.BookDao;
import database.model.Book;
import database.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookDaoImpl implements BookDao{

    public List<Book> getAllBooks(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> list = session.createQuery("from Book").list();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addBook(Book arg) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(arg);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(String id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Book.class, Integer.parseInt(id)));
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
