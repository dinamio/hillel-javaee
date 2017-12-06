package com.hillel.bookstorespringboot.controller;

import com.hillel.bookstorespringboot.dao.BookDao;
import com.hillel.bookstorespringboot.dao.UserDao;
import com.hillel.bookstorespringboot.dto.BookDTO;
import com.hillel.bookstorespringboot.model.Author;
import com.hillel.bookstorespringboot.model.Book;
import com.hillel.bookstorespringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.hillel.bookstorespringboot.dto.BookDTO.listFromString;

@Controller
@RequestMapping("book")
public class BookController {

    public BookController() {
    }

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookDTO bookDTO;

    @Autowired
    UserDao userDao;

// +++
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String listBook(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "/book/listBook";
    }

//+++
    // TODO: Add UI(зачем?:))
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getBook(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("books", bookDao.findOne(id));
        return "/book/book";
    }

// +++
    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String getAddBookPage(Model model) {
        model.addAttribute("book", bookDTO );
        return "book/addBook";
    }

// TODO: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.hillel.bookstorespringboot.model.Book.user -> com.hillel.bookstorespringboot.model.User; nested exception is java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.hillel.bookstorespringboot.model.Book.user -> com.hillel.bookstorespringboot.model.User
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String setAddBook(@ModelAttribute(name = "book") BookDTO bookDTO, Principal principal) { // BookDTO bookDTO - ???????

        String bookName = bookDTO.getBookName();
        List<Author> listAuthors = listFromString(bookDTO.getAuthors());

        User user = new User("username");
    //    User u = userDao.findOne(1);



     //   user.setUserName(principal.getName());  TODO: раскомментировать после настройки security
        Book book = new Book(bookName, listAuthors, user);

// TODO: Связать bookDao с bookDTO (Service?)
        bookDao.save(book);
        return "redirect:/book/book";
    }

//+++
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteBook(@PathVariable Integer id) {
        bookDao.delete(id);
        return "redirect:/book/book";
    }

// TODO: Сделать страницу(форму) для редактирования
    @PutMapping(value = "update/{id}")
    public String updateBook(@RequestBody Book book, @PathVariable Integer id) {
        book.setId(id);
        bookDao.save(book);
        return "redirect:/book/book";
    }
}
