package com.hillel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.model.Book;

import java.io.IOException;

public class TestStore {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book[] books = objectMapper.readValue(Thread.currentThread().getContextClassLoader().getResource("dummyStorage/books.json"), Book[].class);
        for (int i = 0; i < books.length; i++) {

            books[i].setId(i);
        }
    }
}
