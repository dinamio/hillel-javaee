package com.hillel.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hillel.model.Book;

import java.io.IOException;

public class BookSerializer extends JsonSerializer<Book> {

    @Override
    public void serialize(Book book, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("author", book.getAuthor());
        jsonGenerator.writeStringField("country", book.getCountry());
        jsonGenerator.writeStringField("imageLink", book.getImageLink());
        jsonGenerator.writeStringField("language", book.getLanguage());
        jsonGenerator.writeStringField("link", book.getLink());
        jsonGenerator.writeStringField("title", book.getTitle());
        jsonGenerator.writeNumberField("year", book.getYear());
        jsonGenerator.writeNumberField("pages", book.getPages());
        jsonGenerator.writeNumberField("id", book.getId());
        jsonGenerator.writeEndObject();
    }

}
