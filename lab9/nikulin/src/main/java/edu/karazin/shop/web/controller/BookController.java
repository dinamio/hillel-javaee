package edu.karazin.shop.web.controller;

import edu.karazin.shop.model.Author;
import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.Genre;
import edu.karazin.shop.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "books")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BookController {

    @Autowired
    private BookStoreService bookStoreServiceImpl;

    @GetMapping
    public String loadMain() {
        return "bookstore-main";
    }

    @GetMapping(path = "show")
    public String loadBookList(Model model, @RequestParam(value = "searchText", required = false) String genre){
        model.addAttribute("products", bookStoreServiceImpl.getBookListByGenre(genre));
        model.addAttribute("searchForm", new BookSearchForm(genre));
        return "bookstore-show";
    }

    @PostMapping(path = "show")
    public String loadBookListByGenre(Model model, @ModelAttribute(value = "searchForm") BookSearchForm genre) {
        if (bookStoreServiceImpl.getGenreNames().contains(genre.getSearchText())){
            model.addAttribute("products", bookStoreServiceImpl.getBookListByGenre(genre.getSearchText()));
        }
        return "bookstore-show";

    }

    @GetMapping( path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String loadBook(Model model, @PathVariable("id") Long bookId){
        BookList bookList = bookStoreServiceImpl.getBookById(bookId);
        model.addAttribute("product", bookList);
        return "product-edit";
    }

    @PostMapping(path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateBook(BookList bookList,
                             BindingResult bindingResult,
                             @ModelAttribute(value="authors") String authorsForm,
                             @ModelAttribute(value="genres") String genresForm){

        String[] authorsNames = authorsForm.split(", ");
        ArrayList<Author> authors = new ArrayList<>();

        String[] genresNames = genresForm.split(", ");
        ArrayList<Genre> genres = new ArrayList<>();

        for (String s :
                authorsNames) {
            Author author = new Author();
            author.setName(s);
            authors.add(bookStoreServiceImpl.insertAuthor(author));
        }

        for (String s :
                genresNames) {
            Genre genre = new Genre();
            genre.setGenrename(s);
            System.out.println(genre);
            genres.add(bookStoreServiceImpl.insertGenre(genre));
        }

        bookList.setAuthors(authors);
        bookList.setGenres(genres);

        bookStoreServiceImpl.updateBook(bookList);
        return "redirect:/books/show";
    }

    @GetMapping(path = "add")
    @PreAuthorize("hasRole('ADMIN')")
    public String loadPageForCreating(Model model){
        BookList bookList = new BookList();
        model.addAttribute("product", bookList);
        return "bookstore-add";
    }

    @PostMapping(path = "add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addBook(BookList bookList,
                          BindingResult bindingResult,
                          @ModelAttribute(value="authors")String authorsForm,
                          @ModelAttribute(value="genres")String genresForm){

        String[] authorsNames = authorsForm.split(", ");
        ArrayList<Author> authors = new ArrayList<>();

        String[] genresNames = genresForm.split(", ");
        ArrayList<Genre> genres = new ArrayList<>();

        for (String s :
                authorsNames) {
            Author author = new Author();
            author.setName(s);
            authors.add(bookStoreServiceImpl.insertAuthor(author));
        }

        for (String s :
                genresNames) {
            Genre genre = new Genre();
            genre.setGenrename(s);
            System.out.println(genre);
            genres.add(bookStoreServiceImpl.insertGenre(genre));
        }

        bookList.setGenres(genres);
        bookList.setAuthors(authors);

        bookStoreServiceImpl.insertBook(bookList);
        return "redirect:/books/show";
    }

    @GetMapping(path = "remove/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable("id") Long id){
        bookStoreServiceImpl.deleteBook(id);
        return "redirect:/books/show";
    }

    @GetMapping(path = "showhints")
    @ResponseBody
    public List<String> showHints(@RequestParam(value = "genre")String genre){
        return bookStoreServiceImpl.getGenreNames(genre);
    }

}
