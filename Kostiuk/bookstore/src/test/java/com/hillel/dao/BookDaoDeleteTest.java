package com.hillel.dao;

import com.google.common.collect.ImmutableList;
import com.hillel.TestUtils;
import com.hillel.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class BookDaoDeleteTest{

    @Autowired
    private BookDao bookDao;

    @Before
    public void addBooks() {
        Book book_1 = TestUtils.getBook(1);
        Book book_2 = TestUtils.getBook(2);
        bookDao.save(ImmutableList.of(book_1, book_2));
    }

    @Test
    public void shouldDeleteAllBooks() {
        bookDao.deleteAll();
        List<Book> all = (List<Book>) bookDao.findAll();
        assertTrue(all.isEmpty());
    }

    @Test
    public void shouldDeleteBookByID() {
        List<Book> allBooks = (List<Book>) bookDao.findAll();
        bookDao.delete(allBooks.get(0).getId());
        assertEquals(1, bookDao.count());
    }


}
