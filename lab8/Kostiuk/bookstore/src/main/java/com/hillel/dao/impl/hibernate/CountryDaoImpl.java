package com.hillel.dao.impl.hibernate;

import com.hillel.dao.CountryDao;
import com.hillel.model.Country;
import com.hillel.repository.HibernateUtils;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

public class CountryDaoImpl extends BasicCRUDImpl<Country> implements CountryDao {

    static volatile CountryDaoImpl instance;

    private CountryDaoImpl(Class<Country> persistable) {
        super(persistable);
    }

    public static CountryDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CountryDaoImpl.class) {
                if (instance == null) {
                    instance = new CountryDaoImpl(Country.class);
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<Country> getByName(String name) {
        StatelessSession session = HibernateUtils.getSessionFactory().openStatelessSession();
        Country result = (Country) session.createCriteria(Country.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
//        session.close();
        return Optional.ofNullable(result);
    }
}
