package com.hillel.blog.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.hillel.blog.model.Author;
import com.hillel.blog.model.Message;
import com.hillel.blog.model.MessageMeta;

import java.io.IOException;

public class MessageBaseDeserializer extends JsonDeserializer<Message> {

    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectNode node = jsonParser.getCodec().readTree(jsonParser);
        Message message = new Message();
        message.setId(node.get("id").asLong());
        message.setBody(node.get("body").asText());
        message.setTitle(node.get("title").asText());
        long time = node.get("time").asLong(System.currentTimeMillis());
        String author = node.get("author").get("name").asText();
        message.setMessageMeta(getCurrentMeta(author, time));
        return message;
    }

    private MessageMeta getCurrentMeta(String author, Long time) {
        MessageMeta messageMeta = new MessageMeta();
        messageMeta.setDate(time);
        messageMeta.setAuthor(new Author(author));
        return messageMeta;
    }


}
