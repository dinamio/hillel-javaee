package edu.karazin.shop.web;

import edu.karazin.shop.model.Author;
import edu.karazin.shop.model.Book;
import edu.karazin.shop.model.Genre;
import edu.karazin.shop.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "books")
public class BookController {

    @Autowired
    private BookStoreService bookStoreService;

    @RequestMapping(method = RequestMethod.GET)
    public String loadMain() {
        return "bookstore-main";
    }

    @RequestMapping(method = RequestMethod.GET, path = "menu")
    public String loadMenuPage() {
        return "bookstore-menu";
    }

    @RequestMapping(method = RequestMethod.POST, path = "menu")
    public String menuButtonHandler(HttpServletRequest httpServletRequest) {

        String page = "bookstore-menu";

        if (httpServletRequest.getParameter("show") != null) page = "redirect:/books/show";
        if (httpServletRequest.getParameter("add") != null) page = "redirect:/books/add";

        return page;
    }


    @RequestMapping(method = RequestMethod.GET, path = "show")
    public String loadBookList(){
        return "bookstore-show";
    }

    @RequestMapping(method = RequestMethod.POST, path = "show")
    public void loadBookListByGenre(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        httpServletResponse.setContentType("text/html");

        String result = "";
        List<Book> books;

        String genre = httpServletRequest.getParameter("genre");
        System.out.println("Genre = '" + genre + "'");
        if (Objects.equals(genre, "")) books = bookStoreService.getAllBooks();
        else books = bookStoreService.getBookListByGenre(genre);

        if (!Objects.equals(books, null)) {

            for (Book book :
                    books) {

                String authors = "";
                for (int i = 0; i < book.getAuthors().size(); i++) {
                    authors += book.getAuthors().get(i).getName();
                    if (i < (book.getAuthors().size() - 1)) authors += ", ";
                }

                String genres = "";
                for (int i = 0; i < book.getGenres().size(); i++) {
                    genres += book.getGenres().get(i).getGenrename();
                    if (i < (book.getGenres().size() - 1)) genres += ", ";
                }

                result += "<div class=\"jumbotron\"><h1>";
                result += book.getName() +
                        "</h1><p class=\"lead\">" + "Id: " +
                        book.getId() + "</p><p>" + "Author: " +
                        authors + "</p><p>" + "Genres: " +
                        genres +
                        "</p><p>" + "Publisher: " +
                        book.getPublisher() + "</p><p>" +
                        "Price: " + book.getPrice() + "</p>" +
                        "<p><a class=\"btn btn-lg btn-success\" href=\"#\" role=\"button\">Buy now</a></p>" +
                        "<p><a class=\"btn btn-lg btn-success\" href=\"/books/" + book.getId() +"\" role=\"button\">Edit</a></p>" +
                        "</div>";

            }
        }

        try {
            httpServletResponse.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public String loadBook(Model model, @PathVariable("id") Long bookId){

        Book book = bookStoreService.getBookById(bookId);
        model.addAttribute("product", book);

        return "product-edit";
    }

    @RequestMapping(method = RequestMethod.GET, path = "add")
    public String loadPageForCreating(){
        return "bookstore-add";
    }

    @RequestMapping(method = RequestMethod.POST, path = "add")
    public String addBook(@RequestParam(value = "BookName") String bookName,
                          @RequestParam(value = "Pages") String bookPages,
                          @RequestParam(value = "BookAuthor") String bookAuthor,
                          @RequestParam(value = "BookGenre") String bookGenre,
                          @RequestParam(value = "BookPublisher") String bookPublisher,
                          @RequestParam(value = "BookPrice") String bookPrice,
                          @RequestParam(value = "submitButton") String submitButton){

        if (submitButton != null){


            String[] authorsNames = bookAuthor.split(", ");
            ArrayList<Author> authors = new ArrayList<>();

            String[] genresNames = bookGenre.split(", ");
            ArrayList<Genre> genres = new ArrayList<>();

            for (String s :
                    authorsNames) {
                Author author = new Author();
                author.setName(s);
                authors.add(bookStoreService.insertAuthor(author));
            }

            for (String s :
                    genresNames) {
                Genre genre = new Genre();
                genre.setGenrename(s);
                System.out.println(genre);
                genres.add(bookStoreService.insertGenre(genre));
            }

            Book book = new Book(bookName, Integer.valueOf(bookPages), authors, genres, bookPublisher, Integer.valueOf(bookPrice));
            bookStoreService.insertBook(book);
        }
        return "bookstore-show";
    }



    @RequestMapping(method = RequestMethod.POST, path = "{id}")
    public String updateBook(Model model, Book book){
        bookStoreService.updateBook(book);
        return "redirect:/books/show";
    }

}
