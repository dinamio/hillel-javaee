package com.hillel.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.hillel.model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class BookDao {

    private final static Map<Long, Book> BOOKS_MAP = new HashMap<>();

    private Map<Long, Book> getDummyCacheStorage() {
        if (BOOKS_MAP.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Book[] booksFromStorage = objectMapper.readValue(getDummyResource(), Book[].class);
                BOOKS_MAP.putAll(Arrays.stream(booksFromStorage)
                        .collect(Collectors.toMap((Book::getId), v -> v)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return BOOKS_MAP;
    }

    private URL getDummyResource() {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResource("dummyStorage/books.json");
    }

    public List<Book> getAllBooks() {
        return ImmutableList.copyOf(getDummyCacheStorage().values());
    }

    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(getDummyCacheStorage().get(id));
    }

    public Optional<Book> deleteBookById(Long id) {
        return Optional.ofNullable(getDummyCacheStorage().remove(id));
    }

    public Optional<Long> addNewBook(Book book) {
        if (book != null) {
            getDummyCacheStorage().keySet().stream().max(Long::compare).ifPresent(id -> {
                book.setId(++id);
                getDummyCacheStorage().put(id, book);
            });
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.writeValue(new File(getDummyResource().toURI()), getAllBooks());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
        return Optional.ofNullable(book).map(Book::getId);
    }


}
