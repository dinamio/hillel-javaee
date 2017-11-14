package model.hibernate;

import model.Author;
import model.AuthorDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryImplementation;

import java.util.Objects;

public class HibernateAuthorDataAccessObject implements AuthorDataAccessObject {

    private SessionFactory sessionFactory = SessionFactoryImplementation.getSessionFactory();

    private static HibernateAuthorDataAccessObject hibernateAuthorDataAccessObject;

    public static HibernateAuthorDataAccessObject getHibernateAuthorDataAccessObject(){
        if (Objects.equals(hibernateAuthorDataAccessObject, null)) hibernateAuthorDataAccessObject = new HibernateAuthorDataAccessObject();
        return hibernateAuthorDataAccessObject;
    }


    @Override
    public Author createAuthor(Author author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(author);
            transaction.commit();
            return getAuthorByName(author.getName());
        } finally {
            session.close();
        }
    }

    @Override
    public Author getAuthorByName(String name) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "from Author a where a.name = '" + name + "'";
            Query query = session.createQuery(hql);
            return (Author) query.uniqueResult();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Author getAuthorById(long id) {
        return null;
    }
}
