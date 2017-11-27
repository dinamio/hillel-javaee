package com.hillel.controllers;

import com.hillel.dto.BookFormDto;
import com.hillel.model.Book;
import com.hillel.model.User;
import com.hillel.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.hillel.dto.BookFormDto.extractFromBook;
import static com.hillel.util.Utils.*;

@Slf4j
@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    private byte[] noImage = resourceAsBytes("noimg.jpg");


    @GetMapping
    public String getBooks(Model model) {
        log.info("Get all BOOKS request");
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/{id}")
    public String getSingleBook(@PathVariable(value = "id") Long id, Model model) {
        bookService.getEagerStateById(id).ifPresent(book -> {
            model.addAttribute("book", book);
            model.addAttribute("image", base64Encoded(Optional.ofNullable(book.getImage()).orElse(noImage)));
        });
        return "book";
    }

    @GetMapping("/form")
    public ModelAndView addBookForm() {
        return new ModelAndView("formBook", "book", new BookFormDto());
    }

    @GetMapping("/edit")
    public ModelAndView editBookForm(@RequestParam("id") Long id) {
        return new ModelAndView("formBook", "book", extractFromBook(bookService.getById(id)));
    }


    @PostMapping
    public String addNewBook(@ModelAttribute BookFormDto book, BindingResult result, HttpServletRequest httpRequest) {
        log.info("receive: {}", book);
        String userName = ((User) httpRequest.getAttribute("user")).getLoginName();
        book.setUser(userName);
        bookService.insertOrUpdate(book);
        return redirectTo("books");
    }


    @DeleteMapping("/delete")
    public String deleteBook(@RequestParam(value = "id") Long id) {
        bookService.getById(id).ifPresent(bookService::delete);
        return redirectTo("books");
    }

}
