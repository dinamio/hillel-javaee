package com.hillel.dao.impl;

import com.hillel.dao.AuthorDao;
import com.hillel.model.Author;
import com.hillel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AuthorDaoImpl implements AuthorDao{

    @Override
    public Author findById(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Author author = (Author) session.get(Author.class, id);
        transaction.commit();

        return author;
    }

    @Override
    public Integer insertAuthor(Author author) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(author);
        transaction.commit();

        return id;
    }
}
