package com.hillel.blog.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserSerializer extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

    }

//    @Override
//    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeStringField("firstName", user.getFirstName());
//        jsonGenerator.writeStringField("lastName", user.getLastName());
//        jsonGenerator.writeStringField("login", user.getLogin());
//        jsonGenerator.writeArrayFieldStart("messages");
//        for (Message message : user.getMessages()) {
//            jsonGenerator.writeNumber(message.getId());
//        }
//        jsonGenerator.writeEndArray();
//        jsonGenerator.writeEndObject();
//    }
}
