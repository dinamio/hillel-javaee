package model.hibernate;

import model.User;
import model.UsersDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.SessionFactoryImplementation;

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
        Query query = session.createQuery("select u from User u where u.login = :login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        session.close();
        return Objects.equals(user.getPassword(), password);
    }

    @Override
    public boolean createUser(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
