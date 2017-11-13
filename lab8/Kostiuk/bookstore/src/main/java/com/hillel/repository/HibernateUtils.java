package com.hillel.repository;

import com.hillel.model.Book;
import com.hillel.model.Country;
import com.hillel.model.User;
import com.hillel.model.Writer;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtils {

    @Getter
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Writer.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(User.class);
        configuration.configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }


    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }


}
