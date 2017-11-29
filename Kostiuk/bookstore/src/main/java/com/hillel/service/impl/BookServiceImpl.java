package com.hillel.service.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.BookDao;
import com.hillel.dto.BookFormDto;
import com.hillel.model.Book;
import com.hillel.model.Country;
import com.hillel.model.User;
import com.hillel.model.Writer;
import com.hillel.service.BookService;
import com.hillel.service.CountryService;
import com.hillel.service.UserService;
import com.hillel.service.WriterService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.hillel.util.Utils.splitOnCommas;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final WriterService writerService;
    private final CountryService countryService;
    private final UserService userService;

    @Autowired
    public BookServiceImpl(BookDao bookDao, WriterService writerService, CountryService countryService, UserService userService) {
        this.bookDao = bookDao;
        this.writerService = writerService;
        this.countryService = countryService;
        this.userService = userService;
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(bookDao.findOne(id));
    }

    @Override
    public void delete(Book book) {
        Optional.ofNullable(book).map(Book::getId).ifPresent(bookDao::delete);
    }

    @Override
    public Optional<Book> insert(Book book) {
        return Optional.ofNullable(bookDao.save(book));
    }

    @Override
    public Optional<Book> update(Long id, Consumer<Book> updater) {
        Book book = bookDao.findOne(id);
        updater.accept(book);
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> getAll() {
        return ImmutableList.copyOf(bookDao.findAll());
    }

    @Override
    public Optional<Book> getEagerStateById(Long id) {
//        return bookDao.getEagerStateById(id);
        return getById(id);
    }


    @Override
    public List<Book> getByTitle(String title) {
        return bookDao.getByTitle(title);
    }


    @Override
    @SneakyThrows
    public Optional<Book> insertOrUpdate(BookFormDto bookFormDto) {
        String[] countriesNames = splitOnCommas(bookFormDto.getCountries());
        String[] reviewersNames = splitOnCommas(bookFormDto.getReviewers());
        String authorName = bookFormDto.getAuthor();

        //Update countries
        List<Country> countries = Arrays.stream(countriesNames)
                .map(c -> countryService.getByName(c).orElseGet(() -> countryService.insert(new Country(c)).get()))
                .collect(Collectors.toList());

        //Update author
        Optional<Writer> authorOpt = writerService.getByFullName(authorName, true);
        Writer author;
        if (authorOpt.isPresent()) {
            author = authorOpt.get();
            List<Country> currentCountries = author.getCountries();
            List<Country> newCountries = countries.stream().distinct().filter(c -> !currentCountries.contains(c)).collect(Collectors.toList());
            if (!newCountries.isEmpty()) {
                writerService.update(author.getId(), wr -> wr.getCountries().addAll(newCountries));
            }
        } else {
            author = writerService.insert(new Writer(authorName, countries)).get();
        }

        //Update reviewers
        List<Writer> reviewers = Arrays.stream(reviewersNames)
                .distinct()
                .map(w -> writerService.getByFullName(w, false).orElseGet(() -> writerService.insert(new Writer(w)).get()))
                .collect(Collectors.toList());

        //Insert or update the book
        bookFormDto.setLink("https://www.google.com/search?q=" + bookFormDto.getTitle());

        //Update user
        User user = userService.getByLogin(bookFormDto.getUser()).get();

        //Update or insertOrUpdate new Book
        Long id = bookFormDto.getId();
        Book book;
        if (bookDao.exists(id)) {
            return update(id, b -> updateBook(b, bookFormDto, reviewers, author, user));
        } else {
            book = new Book();
            updateBook(book, bookFormDto, reviewers, author, user);
            return insert(book);
        }
    }

    @SneakyThrows
    private void updateBook(Book target, BookFormDto formDto, List<Writer> reviewers, Writer author, User user) {
        target.setLink(formDto.getLink());
        target.setAuthor(author);
        byte[] image = formDto.getBookImage().isEmpty() ? target.getImage() : formDto.getBookImage().getBytes();
        target.setImage(image);
        target.setPages(formDto.getPages());
        target.setYear(formDto.getYear());
        target.setTitle(formDto.getTitle());
        target.setReviewers(reviewers);
        target.setLanguage(formDto.getLanguage());
        target.setUser(user);
    }

}
