package com.hillel.dao.impl;

import com.hillel.dao.UserDao;
import com.hillel.model.User;
import com.hillel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoImpl implements UserDao{

    User user;

    public UserDaoImpl() {
    }

    public UserDaoImpl(User user) {
        this.user = user;
    }

    @Override
    public User findById(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        user = (User) session.get(User.class, id);
        transaction.commit();

        return user;
    }

    @Override
    public User findByName(String name) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        user = (User) session.get(User.class, name);
        transaction.commit();

        return user;
    }

    // TO DO:
/////////////////////////////////
//    @Override
//    public boolean findByName(String name) {
//        Session session = HibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        boolean b = true;
//
//        return b;
//    }
//////////////////////////////



    @Override
    public Integer insertUser(User user) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(user);
        transaction.commit();

        return id;
    }
}
