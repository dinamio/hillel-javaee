package com.hillel.service;


import com.google.common.collect.ImmutableList;
import com.hillel.dao.BookDao;
import com.hillel.dao.CountryDao;
import com.hillel.dto.BookFormDto;
import com.hillel.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookDao bookDao;

    @Test
    public void shouldFindById() {
        when(bookDao.findOne(anyLong())).thenReturn(new Book());
        Optional<Book> byId = bookService.getById(1L);
        assertTrue(byId.isPresent());
    }

    @Test
    public void shouldFindAll() {
        when(bookDao.findAll()).thenReturn(ImmutableList.of(new Book(), new Book()));
        List<Book> all = bookService.getAll();
        assertEquals(2, all.size());
    }



}
