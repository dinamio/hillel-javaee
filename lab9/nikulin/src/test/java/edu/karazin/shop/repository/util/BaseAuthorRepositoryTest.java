package edu.karazin.shop.repository.util;

import edu.karazin.shop.model.Author;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BaseAuthorRepositoryTest {

    public Author getAuthor(int id){
        Author author = new Author();
        author.setId(id);
        author.setName("Test" + id);
        author.setBookLists(new ArrayList<>());
        return author;
    }
}
