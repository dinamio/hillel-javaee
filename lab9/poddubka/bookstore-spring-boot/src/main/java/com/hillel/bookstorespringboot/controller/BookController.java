package com.hillel.bookstorespringboot.controller;

import com.hillel.bookstorespringboot.dao.AuthorDao;
import com.hillel.bookstorespringboot.dao.BookDao;
import com.hillel.bookstorespringboot.dao.UserDao;
import com.hillel.bookstorespringboot.dto.BookDTO;
import com.hillel.bookstorespringboot.model.Author;
import com.hillel.bookstorespringboot.model.Book;
import com.hillel.bookstorespringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    public BookController() {
    }

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    AuthorDao authorDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listBook(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "/book/listBook";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getBook(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("books", bookDao.findOne(id));
        return "/book/book";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String getAddBookPage(Model model, Principal principal) {
        if(principal == null) {
            return "security/registration";
        }
        model.addAttribute("book", new BookDTO() );
        return "book/addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String setAddBook(@ModelAttribute(name = "book") BookDTO bookDTO, Principal principal) {
        String bookName = bookDTO.getBookName();
        List<Author> listAuthors = listFromString(bookDTO);
        User user = userDao.findUserByUserName(principal.getName());
        Book book = new Book(bookName, listAuthors, user);//
        bookDao.save(book);

        return "redirect:/book";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteBook(@PathVariable Integer id, Principal principal) {
        if(principal.getName().equals(bookDao.findOne(id).getUser().getUserName())
                || principal.getName().equals("admin")) {
            bookDao.delete(id);

            return "redirect:/book";
        }
        return "redirect:/book";
    }

    @RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
    public String changeBook(@PathVariable Integer id, Model model, Principal principal) {
        if(principal.getName().equals(bookDao.findOne(id).getUser().getUserName())
                || principal.getName().equals("admin")) {
            model.addAttribute("book", bookDao.findOne(id));

            return "book/update";
        }
        return  "redirect:/book";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute(name = "bookDTO") BookDTO bookDTO, @PathVariable Integer id, Principal principal) {
        String bookName = bookDTO.getBookName();
        if(principal.getName().equals(bookDao.findOne(id).getUser().getUserName())
                || principal.getName().equals("admin")) {
            List<Author> listAuthors = listFromString(bookDTO);
            User user = userDao.findUserByUserName(principal.getName());
            Book book = new Book(bookName, listAuthors, user);
            book.setId(id);
            bookDao.save(book);

            return "redirect:/book";
        }
        return  "redirect:/book";
    }

    private List<Author> listFromString(BookDTO bookDTO) {
        List<Author> list = new ArrayList<>();
        String[] array = bookDTO.getAuthors().split(",");
        for (int i = 0; i < array.length; i++) {
            String name = array[i].trim();
            Author author = authorDao.findByAuthorName(name);
            if(author == null) {
                list.add(new Author(name));
            } else {
                list.add(author);
            }
        }
        return list;
    }
}
