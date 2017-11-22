package edu.karazin.shop.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;


public class SessionFactoryImpl {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (Objects.equals(sessionFactory, null))
            sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }

}
