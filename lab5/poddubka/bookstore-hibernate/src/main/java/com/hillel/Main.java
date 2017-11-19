package com.hillel;

import com.hillel.model.Author;
import com.hillel.model.Book;
import com.hillel.model.User;
import com.hillel.service.BookStoreService;
import com.hillel.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookStoreService bookStoreService = new BookStoreService();

  //      User user = bookStoreService.insertUser("userName", "userPassword");
        Author arnold = bookStoreService.insertAuthor("Ken Arnold");
        Author gosling = bookStoreService.insertAuthor("James Gosling");

  //      Book bookName = bookStoreService.insertBook("The Java Programming Language");
        List<Author> listAuthor = new ArrayList<>();
        listAuthor.add(arnold);
        listAuthor.add(gosling);
  //      Book book = new Book("The Java Programming Language", listAuthor, user);
        Book book = new Book("The Java Programming Language", listAuthor);

        Book book1 = bookStoreService.getBook(1);



    }
}
