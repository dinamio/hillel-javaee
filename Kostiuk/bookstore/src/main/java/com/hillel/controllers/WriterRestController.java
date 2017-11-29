package com.hillel.controllers;

import com.hillel.dto.WriterNameDto;
import com.hillel.service.WriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("rest/writers")
public class WriterRestController {

    @Autowired
    private WriterService writerService;


    @GetMapping
    public List<WriterNameDto> getAllWriterNames() {
        return writerService.getAllWriterNames();
    }

}