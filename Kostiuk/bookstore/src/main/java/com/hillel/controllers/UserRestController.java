package com.hillel.controllers;

import com.google.common.collect.ImmutableList;
import com.hillel.model.User;
import com.hillel.dto.SimpleBookDto;
import com.hillel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest/users")
public class UserRestController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}/books")
    public List<SimpleBookDto> getUserBooks(@PathVariable("id") Long id) {
        return userService.getById(id)
                .map(User::getBooks)
                .map(ImmutableList::copyOf)
                .orElse(ImmutableList.of())
                .stream()
                .map(SimpleBookDto::extractFromBook)
                .collect(Collectors.toList());
    }

}
