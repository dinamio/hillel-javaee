package com.hillel;

import com.google.common.collect.ImmutableList;
import com.hillel.model.Book;
import com.hillel.model.Country;
import com.hillel.model.User;
import com.hillel.model.Writer;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    public static Book getBook(int i) {
        Book book = new Book();
        book.setUser(getUser(i));
        book.setAuthor(getWriter(i));
        book.setTitle("Duck Stories_" + i);
        book.setReviewers(ImmutableList.of(getWriter(++i), getWriter(++i)));
        book.setLanguage("English");
        book.setPages(123);
        book.setYear(1929);
        book.setImage(new byte[]{});
        book.setLink("http://www.google.com");
        return book;
    }

    public static Writer getWriter(int i) {
        Writer writer = new Writer();
        writer.setFullName("Walt Disney_" + i);
        writer.setCountries(IntStream.of(0, 3).mapToObj(TestUtils::getCounty).collect(Collectors.toList()));
        return writer;
    }

    public static Country getCounty(int i) {
        Country country = new Country();
        country.setName("USA_" + i);
        return country;
    }


    public static User getUser(int i) {
        User user = new User();
        user.setFirstName("Donald_" + i);
        user.setLastName("Duck_" + i);
        user.setLoginName("Crazy_bird_" + i);
        user.setPassword("qwerty_" + i);
        return user;
    }


}
