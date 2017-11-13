package com.hillel.service.impl;

import com.hillel.dao.CountryDao;
import com.hillel.dao.impl.hibernate.CountryDaoImpl;
import com.hillel.model.Country;
import com.hillel.service.CountryService;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class CountryServiceImpl implements CountryService {


    @Getter
    static volatile CountryService instance;

    public static CountryService getInstance() {
        if (instance == null) {
            synchronized (CountryServiceImpl.class) {
                if (instance == null) {
                    instance = new CountryServiceImpl();
                }
            }
        }
        return instance;
    }

    private CountryDao countryDao;

    public CountryServiceImpl() {
        countryDao = CountryDaoImpl.getInstance();
    }

    @Override
    public Optional<Country> insert(Country country) {
        Optional.ofNullable(countryDao.insert(country)).ifPresent(country::setId);
        return Optional.of(country);
    }

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }

    @Override
    public void delete(Country country) {
        Optional.ofNullable(country).map(Country::getId).ifPresent(countryDao::deleteById);
    }

    @Override
    public void update(Long id, Consumer<Country> updater) {
        countryDao.update(id, updater);
    }

    @Override
    public void update(Country entity, Consumer<Country> updater) {
        countryDao.update(entity, updater);
    }

    @Override
    public Optional<Country> getById(Long id) {
        return countryDao.getById(id);
    }

    @Override
    public Optional<Country> getByName(String name) {
        return countryDao.getByName(name);
    }
}
