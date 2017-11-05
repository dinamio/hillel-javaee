package config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by eugen on 11/2/17.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static{
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}