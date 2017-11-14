package model;

public interface GenreDataAccessObject {
    Genre getGenreByName(String genrename);
    Genre getGenreById(long id);
    Genre createGenre(Genre genre);
}
