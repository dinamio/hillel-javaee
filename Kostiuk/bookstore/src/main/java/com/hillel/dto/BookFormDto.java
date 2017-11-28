package com.hillel.dto;

import com.hillel.model.Book;
import com.hillel.model.Country;
import com.hillel.model.Writer;
import com.hillel.validation.OnCommaSeparated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFormDto {

    private Long id;

    @Min(-2000)
    @Max(2017)
    @NotNull
    private Integer year;

    @Positive
    @NotNull
    private Integer pages;

    private String user;

    private String link;

    @Size(min = 2, max = 50)
    private String title;

    @Size(min = 2, max = 50)
    private String author;

    @Size(min = 2, max = 50)
    private String language;

    @OnCommaSeparated
    private String countries;

    @OnCommaSeparated
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
