package com.hillel.dao.impl.hibernate;

import com.hillel.dao.CountryDao;
import com.hillel.model.Country;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Repository
public class CountryDaoImpl extends BasicCRUDImpl<Country> implements CountryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    private void initPersistableClass() {
        setPersistable(Country.class);
    }


    @Override
    public Optional<Country> getByName(String name) {
        StatelessSession session = sessionFactory.openStatelessSession();
        Country result = (Country) session.createCriteria(Country.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        session.close();
        return Optional.ofNullable(result);
    }
}
