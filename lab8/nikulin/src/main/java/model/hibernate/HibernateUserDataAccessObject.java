package model.hibernate;

import model.User;
import model.UsersDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryImplementation;

import java.util.List;
import java.util.Objects;

public class HibernateUserDataAccessObject implements UsersDataAccessObject {

    private SessionFactory sessionFactory = SessionFactoryImplementation.getSessionFactory();
    private static HibernateUserDataAccessObject hibernateUserDataAccessObject;

    private HibernateUserDataAccessObject(){}

    public static HibernateUserDataAccessObject getHibernateUserDataAccessObject() {
        if (hibernateUserDataAccessObject != null) return hibernateUserDataAccessObject;
        else {
            hibernateUserDataAccessObject = new HibernateUserDataAccessObject();
            return hibernateUserDataAccessObject;
        }
    }

    @Override
    public boolean getUser(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from User u where u.ulogin = '" + login + "'";
        Query query = session.createQuery(hql);
        User user = ((User) query.uniqueResult());
        System.out.println("User " + (query.uniqueResult()));
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
}
