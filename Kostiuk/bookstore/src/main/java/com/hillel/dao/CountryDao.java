package com.hillel.dao;

import com.hillel.model.Country;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryDao extends CrudRepository<Country, Long> {


    Optional<Country> getByName(@NonNull String name);


}
