package com.hillel.dto;

import com.hillel.model.Book;
import com.hillel.model.Country;
import com.hillel.model.Writer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFormDto {

    private Long id;
    private int year;
    private int pages;
    private String user;
    private String link;
    private String title;
    private String author;
    private String language;
    private String countries;
    private String reviewers;
    private MultipartFile bookImage;

    public static BookFormDto extractFromBook(Optional<Book> book) {
        return book.map(BookFormDto::extractFromBook).orElseGet(BookFormDto::new);
    }

    private static BookFormDto extractFromBook(Book book) {
        return new BookFormDto(book.getId(),
                book.getYear(),
                book.getPages(),
                book.getUser().getLoginName(),
                book.getLink(),
                book.getTitle(),
                book.getAuthor().getFullName(),
                book.getLanguage(),
                book.getAuthor().getCountries().stream().map(Country::getName).collect(Collectors.joining(", ")),
                book.getReviewers().stream().map(Writer::getFullName).collect(Collectors.joining(", ")),
                null
        );
    }

}
