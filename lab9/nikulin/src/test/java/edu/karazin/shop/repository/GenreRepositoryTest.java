package edu.karazin.shop.repository;

import edu.karazin.shop.model.Genre;
import edu.karazin.shop.repository.util.BaseGenreRepositoryTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreRepositoryTest extends BaseGenreRepositoryTest{

    @Autowired
    GenreRepository genreRepository;

    @After
    public void cleaner(){
        genreRepository.deleteAll();
    }

    @Test
    public void shouldAddGenre(){
        Genre genre = getGenre(1);

        Genre save = genreRepository.save(genre);
        Genre actual = genreRepository.findOne(save.getGenreid());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateGenre(){
        Genre genre = getGenre(1);

        genreRepository.save(genre);

        Genre save = genreRepository.findOne(genre.getGenreid());
        save.setGenrename("Test2");
        genreRepository.save(save);

        Genre actual = genreRepository.findOne(save.getGenreid());

        assertNotEquals(genre, actual);
        assertEquals(genre.getGenreid(), actual.getGenreid());
    }

    @Test
    public void shouldDeleteGenre(){
        Genre genre = getGenre(1);

        Genre save = genreRepository.save(genre);
        Genre actual = genreRepository.findOne(save.getGenreid());

        assertEquals(save, actual);

        genreRepository.delete(actual.getGenreid());
    }
}
