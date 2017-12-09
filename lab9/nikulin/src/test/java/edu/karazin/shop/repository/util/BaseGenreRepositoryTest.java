package edu.karazin.shop.repository.util;

import edu.karazin.shop.model.Genre;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BaseGenreRepositoryTest {

    public Genre getGenre(int id){
        Genre genre = new Genre();
        genre.setGenreid(id);
        genre.setGenrename("Test" + id);
        genre.setBookLists(new ArrayList<>());
        return genre;
    }
}
