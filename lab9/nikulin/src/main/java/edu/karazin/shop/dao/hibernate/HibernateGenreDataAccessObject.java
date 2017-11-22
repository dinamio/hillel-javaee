package edu.karazin.shop.dao.hibernate;

import edu.karazin.shop.model.Genre;
import edu.karazin.shop.dao.GenreDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import edu.karazin.shop.util.SessionFactoryImpl;

import java.util.Objects;

public class HibernateGenreDataAccessObject implements GenreDataAccessObject {

    private static  HibernateGenreDataAccessObject hibernateGenreDataAccessObject;
    private SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();

    public static HibernateGenreDataAccessObject getHibernateGenreDataAccessObject(){
        if (Objects.equals(hibernateGenreDataAccessObject, null)) hibernateGenreDataAccessObject = new HibernateGenreDataAccessObject();
        return hibernateGenreDataAccessObject;
    }

    @Override
    public Genre getGenreByName(String genrename) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "select g from Genre g where g.genrename = '" + genrename + "'";
            Query query = session.createQuery(hql);
            Genre genre = (Genre) query.uniqueResult();
            transaction.commit();
            return genre;
        } finally {
            session.close();
        }
    }

    @Override
    public Genre getGenreById(long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "from Genre g where g.genreid = " + id;
            Query query = session.createQuery(hql);
            return (Genre) query.uniqueResult();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Genre createGenre(Genre genre) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "from Genre g where g.genrename = '" + genre.getGenrename() + "'";
            Query query = session.createQuery(hql);
            return (Genre) query.uniqueResult();
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
