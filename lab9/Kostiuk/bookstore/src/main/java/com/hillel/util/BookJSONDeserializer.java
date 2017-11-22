package com.hillel.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hillel.model.Book;
import com.hillel.model.BookJSON;

import java.io.IOException;

public class BookJSONDeserializer extends JsonDeserializer<BookJSON> {

    @Override
    public BookJSON deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        BookJSON book = new BookJSON();
        book.setAuthor(node.get("author").asText());
        book.setCountry(node.get("country").asText());
        book.setImage(Utils.resourceAsBytes(node.get("imageLink").asText()));
        book.setLanguage(node.get("language").asText());
        book.setLink(node.get("link").asText());
        book.setTitle(node.get("title").asText());
        book.setYear(node.get("year").asInt());
        book.setPages(node.get("pages").asInt());
        return book;
    }

}
