import model.Author;
import model.Book;
import model.Genre;
import model.ISBN;
import service.BookStoreService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        BookStoreService bookStoreService = new BookStoreService();
        //System.out.println(bookStoreService.getBook(19));
        /*Genre comedy = bookStoreService.insertGenre("Comedy");
        Author ilf = bookStoreService.insertAuthor("Ilya", "Ilf");
        Author petrov = bookStoreService.insertAuthor("Eugen", "Petrov");
        ISBN isbn = bookStoreService.insertIsbn("12","23","34","56");
        Book book = bookStoreService.insertBook("Gold cow",555, Arrays.asList(ilf,petrov),comedy,isbn);*/
        System.out.println(bookStoreService.getBook(20));
    }


}
