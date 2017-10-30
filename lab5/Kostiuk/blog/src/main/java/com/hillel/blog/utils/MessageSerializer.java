package com.hillel.blog.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hillel.blog.model.Message;

import java.io.IOException;

public class MessageSerializer extends JsonSerializer<Message> {

    @Override
    public void serialize(Message message, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", message.getId());
        jsonGenerator.writeStringField("title", message.getTitle());
        jsonGenerator.writeStringField("body", message.getBody());
        jsonGenerator.writeObjectField("author", message.getMessageMeta().getAuthor());
        jsonGenerator.writeNumberField("time", message.getMessageMeta().getDate());
        jsonGenerator.writeEndObject();
    }

}
