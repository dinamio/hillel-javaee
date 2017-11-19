package com.hillel.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.dto.WriterNameDto;
import com.hillel.service.WriterService;
import com.hillel.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/rest/writers")
@Component
public class WriterServlet extends HttpServlet {

    @Autowired
    private WriterService writerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Utils.requestID(req).orElse(-1L);
        log.info("GET ALL WRITERS");
        if (id == -1) {
            List<WriterNameDto> writerNames = writerService.getAllWriterNames();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(), writerNames);
            return;
        }

        resp.sendRedirect(req.getRequestURI());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utils.requestID(req).flatMap(writerService::getById).ifPresent(writerService::delete);
        resp.sendRedirect(req.getRequestURI());
    }

}
