package edu.karazin.shop.repository;

import edu.karazin.shop.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
    Genre getGenreByGenrename(String genrename);
}
