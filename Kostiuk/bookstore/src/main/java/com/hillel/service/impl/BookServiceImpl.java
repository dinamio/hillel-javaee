package com.hillel.service.impl;

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
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.hillel.util.Utils.splitOnCommas;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private WriterService writerService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserService userService;

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
    @SneakyThrows
    public Optional<Book> insertOrUpdate(BookFormDto dto) {
        String[] countriesNames = splitOnCommas(dto.getCountries());
        String[] reviewersNames = splitOnCommas(dto.getReviewers());
        String authorName = dto.getAuthor();

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
                writerService.update(author, wr -> wr.getCountries().addAll(newCountries));
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
        dto.setLink("https://www.google.com/search?q=" + dto.getTitle());

        //Update user
        User user = userService.getByLogin(dto.getUser()).get();

        //Update or insertOrUpdate new Book
        Long id = dto.getId();
        Book book = bookDao.update(id, b -> updateBook(b, dto, reviewers, author, user)).orElseGet(() -> {
            Book result = new Book();
            updateBook(result, dto, reviewers, author, user);
            result.setId(bookDao.insert(result));
            return result;
        });

        return Optional.of(book);
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

    @Override
    public List<Book> getByParams(Map<String, Object> params) {
        return bookDao.getByParams(params);
    }
}
