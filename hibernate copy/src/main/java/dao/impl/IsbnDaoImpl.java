package dao.impl;

import config.HibernateUtil;
import dao.IsbnDao;
import model.ISBN;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by eugen on 11/3/17.
 */
public class IsbnDaoImpl implements IsbnDao {
    public Integer insert(ISBN isbn) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(isbn);
        transaction.commit();
        return id;
    }
}
