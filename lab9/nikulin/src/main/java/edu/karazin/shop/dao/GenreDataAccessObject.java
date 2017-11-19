package edu.karazin.shop.dao;

import edu.karazin.shop.model.Genre;

public interface GenreDataAccessObject {
    Genre getGenreByName(String genrename);
    Genre getGenreById(long id);
    Genre createGenre(Genre genre);
}
