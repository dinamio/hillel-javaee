package com.hillel.dao.impl.hibernate;

import com.hillel.dao.UserDao;
import com.hillel.model.User;
import com.hillel.repository.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

@Slf4j
public class UserDaoImpl extends BasicCRUDImpl<User> implements UserDao {

    static volatile UserDaoImpl instance;

    private UserDaoImpl(Class<User> persistable) {
        super(persistable);
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl(User.class);
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        User user = (User) session.createQuery("FROM User WHERE loginName=:login")
                .setParameter("login", login)
                .uniqueResult();
//        session.close();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getEagerStateByLogin(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.enableFetchProfile("eager_profile");
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("loginName", login))
                .setFetchMode("books", FetchMode.EAGER)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(user);
    }

}
