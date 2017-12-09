package edu.karazin.shop.repository;

import edu.karazin.shop.model.Author;
import edu.karazin.shop.repository.util.BaseAuthorRepositoryTest;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorRepositoryTest extends BaseAuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @After
    public void cleaner(){
        authorRepository.deleteAll();
    }

    @Test
    public void shouldAddAuthor(){
        Author author = getAuthor(1);

        Author save = authorRepository.save(author);
        Author actual = authorRepository.findOne(save.getId());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateAuthor(){
        Author author = getAuthor(1);

        Author save = authorRepository.save(author);
        Author actual = authorRepository.findOne(save.getId());

        assertEquals(save, actual);

        actual.setId(1);
        actual.setName("Test2");

        Author saveNew = authorRepository.save(actual);
        Author update = authorRepository.findOne(saveNew.getId());

        assertNotEquals(author, update);
        assertEquals(author.getId(), update.getId());
    }

    @Test
    public void shouldDeleteAuthor(){
        Author author = getAuthor(1);

        Author save = authorRepository.save(author);
        Author actual = authorRepository.findOne(save.getId());

        assertEquals(save, actual);

        authorRepository.delete(actual.getId());

        Author deleted = authorRepository.findOne(actual.getId());

        assertNull(deleted);
    }

    @Test
    public void shouldFindAll(){
        Author author1 = getAuthor(1);
        Author author2 = getAuthor(2);

        authorRepository.save(author1);
        authorRepository.save(author2);

        List<Author> authors = (List<Author>) authorRepository.findAll();

        assertEquals(authors.size(), 2);
    }
}
