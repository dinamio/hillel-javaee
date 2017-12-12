package com.hillel.service;

import com.hillel.model.Country;
import lombok.NonNull;

import java.util.Optional;

public interface CountryService extends BasicCRUDService<Country> {

    Optional<Country> getByName(@NonNull String name);

}
