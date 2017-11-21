package database.config;

import com.google.gson.Gson;
import database.dao.BookDao;
import database.dao.impl.BookDaoImpl;
import database.dto.BookDTO;
import database.model.Author;
import database.model.Book;
import database.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static{
        try{
            Configuration configuration = new Configuration().configure();
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Author.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory(registry);

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