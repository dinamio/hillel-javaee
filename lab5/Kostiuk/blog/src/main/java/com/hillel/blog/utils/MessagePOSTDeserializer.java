package com.hillel.blog.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hillel.blog.model.Author;
import com.hillel.blog.model.Message;
import com.hillel.blog.model.MessageMeta;

import java.io.IOException;

public class MessagePOSTDeserializer extends JsonDeserializer<Message> {

    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Message message = new Message();
        message.setBody(node.get("body").asText());
        message.setTitle(node.get("title").asText());

        message.setMessageMeta(getCurrentMeta(node.get("author").asText()));
        return message;
    }

    private MessageMeta getCurrentMeta(String author) {
        MessageMeta messageMeta = new MessageMeta();
        messageMeta.setDate(System.currentTimeMillis());
        messageMeta.setAuthor(new Author(author));
        return messageMeta;
    }


}
