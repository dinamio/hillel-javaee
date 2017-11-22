package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;


public class SessionFactoryImplementation {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (Objects.equals(sessionFactory, null))
            sessionFactory = new Configuration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }

}
