package com.hillel.service.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.CountryDao;
import com.hillel.model.Country;
import com.hillel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public Optional<Country> insert(Country country) {
        return Optional.ofNullable(countryDao.save(country));
    }

    @Override
    public List<Country> getAll() {
        return ImmutableList.copyOf(countryDao.findAll());
    }

    @Override
    public void delete(Country country) {
        Optional.ofNullable(country).ifPresent(countryDao::delete);
    }

    @Override
    public Optional<Country> update(Long id, Consumer<Country> updater) {
        Country country = countryDao.findOne(id);
        updater.accept(country);
        return Optional.ofNullable(country);
    }


    @Override
    public Optional<Country> getById(Long id) {
        return Optional.ofNullable(countryDao.findOne(id));
    }

    @Override
    public Optional<Country> getByName(String name) {
        return countryDao.getByName(name);
    }
}
