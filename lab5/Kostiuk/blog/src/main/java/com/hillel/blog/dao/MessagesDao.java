package com.hillel.blog.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.ImmutableList;
import com.hillel.blog.model.Message;
import com.hillel.blog.utils.MessageBaseDeserializer;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MessagesDao {

    private static final Map<Long, Message> messages;

    static {
        messages = new HashMap<>();
    }

    public Map<Long, Message> getMessagesStorage() {
        return messages;
    }

    private void addBasicMessages() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Message.class, new MessageBaseDeserializer());
            objectMapper.registerModule(module);
            Message[] messagesFromStorage = objectMapper.readValue(getMessagesResource(), Message[].class);
            messages.putAll(Arrays.stream(messagesFromStorage)
                    .collect(Collectors.toMap((Message::getId), v -> v)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private URL getMessagesResource() {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResource("messages/base_messages.json");
    }

    public MessagesDao() {
        addBasicMessages();
    }

    public List<Message> getAllMessages() {
        return ImmutableList.copyOf(getMessagesStorage().values());
    }

    public Optional<Message> getMessageById(@NonNull Long id) {
        return Optional.ofNullable(getMessagesStorage().get(id));
    }

    public void addNewMessage(Message message) {
        long id = messages.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
        message.setId(id);
        messages.put(id, message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(getMessagesResource().toURI()), getAllMessages());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Optional<Message> removeMessageById(@NonNull Long id) {
        return Optional.ofNullable(getMessagesStorage().remove(id));
    }

//    private List<Message> getMessagesByUser(String userLogin) {
//        return userDao.getUserByLogin(userLogin)
//                .map(User::getMessages)
//                .map(ImmutableList::copyOf)
//                .orElse(ImmutableList.of());
//    }


}
