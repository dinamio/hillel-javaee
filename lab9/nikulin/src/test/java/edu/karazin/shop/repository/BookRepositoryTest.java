package edu.karazin.shop.repository;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.repository.util.BaseBookRepositoryTest;
import edu.karazin.shop.repository.util.BaseUserRepositoryTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookRepositoryTest extends BaseBookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @After
    public void cleaner(){
        bookRepository.deleteAll();
    }

    @Test
    public void shouldAddBook(){
        BookList book = getBook(1L);

        BookList save = bookRepository.save(book);

        BookList actual = bookRepository.findOne(save.getId());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateBook(){
        BookList book1 = getBook(1L);

        BookList save1 = bookRepository.save(book1);

        BookList save = bookRepository.findOne(save1.getId());

        save1.setName("Test2");

        BookList save2 = bookRepository.save(save1);

        BookList actual = bookRepository.findOne(save2.getId());

        assertNotEquals(save, actual);
        assertEquals(save.getId(), actual.getId());
    }

    @Test
    public void shouldDeleteBook(){
        BookList book = new BookList();
        book.setId(1L);
        book.setName("Test");

        BookList save = bookRepository.save(book);

        BookList actual = bookRepository.findOne(1L);
        assertEquals(save, actual);

        bookRepository.delete(1L);

        BookList deleted = bookRepository.findOne(1L);
        assertNull(deleted);
    }
}
