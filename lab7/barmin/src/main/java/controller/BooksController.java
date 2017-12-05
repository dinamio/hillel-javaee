package controller;

import database.dao.BookDao;
import database.dao.UserDao;
import database.model.Author;
import database.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping(path = "/booksController")
public class BooksController extends HttpServlet {

    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    protected List<Book> getAllBooks() {
        return (List<Book>) bookDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String addNewBook(@RequestParam("bname") String bookName,
                                @RequestParam("bauthorName") String[] names,
                                @RequestParam("bauthorSurname") String[] surnames,
                                Principal principal) {
        Book book = new Book();
        Set<Author> authors = new HashSet<>();
        book.setName(bookName);

        for(int i = 0; i<names.length; i++){
            Author temp = new Author();
            temp.setName(names[i]);
            temp.setSurname(surnames[i]);
            authors.add(temp);
        }

        book.setAuthors(authors);
        book.setUserID(userDao.findOne(principal.getName()));
        bookDao.save(book);
        return "redirect:/welcome";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE)
    protected void deleteBook(@RequestParam("id") String id ) {
        bookDao.delete(Integer.parseInt(id));
    }
}
