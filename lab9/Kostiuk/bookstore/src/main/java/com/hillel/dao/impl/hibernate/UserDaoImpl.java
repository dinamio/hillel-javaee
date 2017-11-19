package com.hillel.dao.impl.hibernate;

import com.hillel.dao.UserDao;
import com.hillel.model.User;
import com.hillel.repository.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Repository
public class UserDaoImpl extends BasicCRUDImpl<User> implements UserDao {

    @PostConstruct
    private void initPersistableClass() {
        setPersistable(User.class);
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
                .setFetchMode("books", FetchMode.JOIN)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(user);
    }

}
