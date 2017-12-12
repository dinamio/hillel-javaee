package com.hillel.blog.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hillel.blog.dao.MessagesDao;
import com.hillel.blog.model.Message;
import com.hillel.blog.utils.MessagePOSTDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@WebServlet("/blog")
public class MessageServlet extends HttpServlet {

    private MessagesDao messagesDao;

    @Override
    public void init() throws ServletException {
        messagesDao = new MessagesDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> messages = messagesDao.getAllMessages();
        log.info("Send all messages {} to {}", messages, req.getRequestURI());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), messages);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageJSON = IOUtils.toString(req.getInputStream(), Charset.forName("utf-8"));
        log.info(" Receive msg: {}", messageJSON);
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Message.class, new MessagePOSTDeserializer());
        objectMapper.registerModule(module);
        Message message = objectMapper.readValue(messageJSON, Message.class);
        messagesDao.addNewMessage(message);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        log.info(" Delete msg: {}", id);
        messagesDao.removeMessageById(id);
    }

}
