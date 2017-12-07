package com.hillel.dao.impl.hibernate;

import com.hillel.dao.UserDao;
import com.hillel.model.User;
import com.hillel.repository.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Slf4j
public class UserDaoImpl implements UserDao {


    static volatile UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        transaction.commit();
        return users;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE loginName=:login")
                .setParameter("login", login)
                .uniqueResult();
        transaction.commit();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        transaction.commit();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> addNewUser(User user) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(user);
        user.setId(id);
        transaction.commit();
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Delete user by id {}", id);
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.flush();
        transaction.commit();
    }
}
