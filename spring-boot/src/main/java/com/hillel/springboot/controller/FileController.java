package com.hillel.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by eugen on 11/27/17.
 */
@RestController
@RequestMapping("file")
public class FileController {

    @PostMapping
    public void uploadFile(@RequestParam(name = "file") MultipartFile file) {
        System.out.println(file.getSize());
        System.out.println("file uploaded!");
    }

}
