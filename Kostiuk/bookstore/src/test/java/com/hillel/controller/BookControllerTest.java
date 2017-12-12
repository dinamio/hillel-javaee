package com.hillel.controller;

import com.google.common.collect.ImmutableList;
import com.hillel.model.Book;
import com.hillel.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private BookService bookService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
    }


    @Test
    public void shouldGetBooksViewOnBooksRequest() throws Exception {
        when(bookService.getAll()).thenReturn(ImmutableList.of());
        MockHttpServletRequestBuilder request = get("/books");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    public void shouldGetBookViewOnBookIDRequest() throws Exception {
        when(bookService.getById(anyLong())).thenReturn(Optional.of(new Book()));
        MockHttpServletRequestBuilder request = get("/books/4");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(view().name("book"))
                .andExpect(model().attributeExists("book"));
    }

}
