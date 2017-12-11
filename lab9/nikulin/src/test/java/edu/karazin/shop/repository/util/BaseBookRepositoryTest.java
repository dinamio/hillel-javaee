package edu.karazin.shop.repository.util;

import edu.karazin.shop.model.BookList;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BaseBookRepositoryTest {

    public BookList getBook(Long id){
        BookList book = new BookList();
        book.setId(id);
        book.setName("Test" + id);
        book.setAuthors(new ArrayList<>());
        book.setGenres(new ArrayList<>());
        book.setOrderLists(new ArrayList<>());
        book.setOrdersStory(new ArrayList<>());
        book.setPages(Integer.valueOf(String.valueOf(id)));
        book.setPrice(Double.valueOf(id));
        book.setPublisher("Test" + id);
        return book;
    }
}
