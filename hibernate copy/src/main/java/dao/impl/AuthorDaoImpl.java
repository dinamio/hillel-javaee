package dao.impl;

import config.HibernateUtil;
import dao.AuthorDao;
import model.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

/**
 * Created by eugen on 11/3/17.
 */
public class AuthorDaoImpl implements AuthorDao {
    public Author findById(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Author author = (Author) HibernateUtil.getSession().get(Author.class, id);
        transaction.commit();
        return author;
    }

    public Integer insert(Author author) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(author);
        transaction.commit();
        return id;
    }
}
