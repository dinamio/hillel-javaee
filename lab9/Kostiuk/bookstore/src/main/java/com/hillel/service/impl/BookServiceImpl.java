package com.hillel.service.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.BookDao;
import com.hillel.model.Book;
import com.hillel.model.Country;
import com.hillel.model.Writer;
import com.hillel.service.BookService;
import com.hillel.service.CountryService;
import com.hillel.service.WriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao        bookDao;
    @Autowired
    private WriterService  writerService;
    @Autowired
    private CountryService countryService;

    @Override
    public Optional<Book> getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public void delete(Book book) {
        Optional.ofNullable(book).map(Book::getId).ifPresent(bookDao::deleteById);
    }

    @Override
    public Optional<Book> insert(Book book) {
        Optional.ofNullable(bookDao.insert(book)).ifPresent(book::setId);
        return Optional.of(book);
    }

    @Override
    public void update(Long id, Consumer<Book> updater) {
        bookDao.update(id, updater);
    }

    @Override
    public void update(Book entity, Consumer<Book> updater) {
        bookDao.update(entity, updater);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Optional<Book> getEagerStateById(Long id) {
        return bookDao.getEagerStateById(id);
    }


    @Override
    public List<Book> getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public Optional<Book> insert(String language, String link, int pages, String title, int year, String author, String countryName) {

        Optional<Country> optCountry = countryService.getByName(countryName);
        Country country = optCountry.orElseGet(() -> countryService.insert(new Country(countryName)).get());

        Optional<Writer> optAuthor = writerService.getByFullName(author);
        Writer writer = optAuthor.orElseGet(() -> writerService.insert(new Writer(author, ImmutableList.of(country))).get());

        Book result = new Book(language, link, pages, title, year, null, writer);
        return insert(result);
    }

    @Override
    public List<Book> getByParams(Map<String, Object> params) {
        return bookDao.getByParams(params);
    }
}
