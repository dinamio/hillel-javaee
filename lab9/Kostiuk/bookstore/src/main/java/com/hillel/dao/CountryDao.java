package com.hillel.dao;

import com.hillel.model.Country;
import lombok.NonNull;

import java.util.Optional;

public interface CountryDao extends BasicCRUD<Country> {


    Optional<Country> getByName(@NonNull String name);


}
