package com.hillel.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hillel.model.Book;
import com.hillel.model.presentation.SimpleBookPresentation;

import java.io.IOException;

public class SimpleBookPresentationSerializer extends JsonSerializer<SimpleBookPresentation> {

    @Override
    public void serialize(SimpleBookPresentation book, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("author", book.getAuthor());
        jsonGenerator.writeStringField("title", book.getTitle());
        jsonGenerator.writeNumberField("year", book.getYear());
        jsonGenerator.writeNumberField("pages", book.getPages());
        jsonGenerator.writeNumberField("id", book.getId());
        jsonGenerator.writeEndObject();
    }

}
