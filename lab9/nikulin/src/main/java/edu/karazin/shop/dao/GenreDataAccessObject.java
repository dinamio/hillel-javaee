package edu.karazin.shop.dao;

import edu.karazin.shop.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreDataAccessObject extends CrudRepository<Genre, Integer> {
    Genre getGenreByGenrename(String genrename);
}
