package com.hillel.dao;


import com.google.common.collect.ImmutableList;
import com.hillel.TestUtils;
import com.hillel.model.Book;
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
public class BookDaoTest {


    @Autowired
    protected BookDao bookDao;


    @Test
    public void shouldAddNewBookAndFindByID() {
        Book book = TestUtils.getBook(1);
        Book saved = bookDao.save(book);
        Book actual = bookDao.findOne(saved.getId());
        assertEquals(actual, saved);
    }

    @Test
    public void shouldAddNewBookAndFindByTitle() {
        Book book = TestUtils.getBook(1);
        Book saved = bookDao.save(book);
        List<Book> byTitleLike = bookDao.getByTitleLike(saved.getTitle());
        assertTrue(byTitleLike.stream().map(Book::getTitle).anyMatch(saved.getTitle()::equals));
    }


    @Test
    public void shouldSaveEditedBookAndFindByID() {
        Book book = TestUtils.getBook(1);
        Book saved = bookDao.save(book);
        saved.setTitle("New Title");
        Book actualAfterEdit = bookDao.findOne(saved.getId());
        assertEquals("New Title", actualAfterEdit.getTitle());
    }

    @Test
    public void shouldAddNewBooksAndFindAll() {
        Book book_1 = TestUtils.getBook(1);
        Book book_2 = TestUtils.getBook(2);
        bookDao.save(ImmutableList.of(book_1, book_2));
        List<Book> actual = (List<Book>) bookDao.findAll();
        assertEquals(2, actual.size());
    }


}
