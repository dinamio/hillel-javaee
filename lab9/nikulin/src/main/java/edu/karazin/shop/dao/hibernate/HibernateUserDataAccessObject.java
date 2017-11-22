package edu.karazin.shop.dao.hibernate;

import edu.karazin.shop.model.User;
import edu.karazin.shop.dao.UsersDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import edu.karazin.shop.util.SessionFactoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class HibernateUserDataAccessObject implements UsersDataAccessObject {

    private SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();

    private HibernateUserDataAccessObject(){}

    @Override
    public boolean getUser(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from User u where u.ulogin = '" + login + "'";
        Query query = session.createQuery(hql);
        User user = ((User) query.uniqueResult());
        transaction.commit();
        session.close();
        return Objects.equals(user.getUpassword(), password);
    }

    @Override
    public boolean createUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally {
            session.close();
        }
    }

    public List<User> getAll(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from User";
            Query query = session.createQuery(hql);
            List <User> users = query.getResultList();
            transaction.commit();
            return users;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByName(String login){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from User u where u.ulogin='" + login + "'";
            Query query = session.createQuery(hql);
            User user = (User) query.uniqueResult();
            transaction.commit();
            return user;
        } finally {
            session.close();
        }
    }
}
