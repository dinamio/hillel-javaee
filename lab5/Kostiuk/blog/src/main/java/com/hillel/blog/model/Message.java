package com.hillel.blog.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hillel.blog.utils.MessageSerializer;
import lombok.Data;

@Data
@JsonSerialize(using = MessageSerializer.class)
public class Message {

    private MessageMeta messageMeta;

    private Long   id;
    private String title;
    private String body;

}
