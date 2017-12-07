package com.hillel.dao.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.BookDao;
import com.hillel.dao.UserDao;
import com.hillel.model.Book;
import com.hillel.model.User;
import com.hillel.repository.Connector;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hillel.model.User.UNDEFINED_USER;

@Slf4j
public class JDBCBookDao implements BookDao {


    private static volatile JDBCBookDao instance;

    public static JDBCBookDao getInstance() {
        if (instance == null) {
            synchronized (Connector.class) {
                if (instance == null) {
                    instance = new JDBCBookDao();
                }
            }
        }
        return instance;
    }

    private UserDao userDao;

    private JDBCBookDao() {
        userDao = JDBCUserDao.getInstance();
    }

    @Override
    public List<Book> getAllBooks() {
        String insertTableSQL = "SELECT id, country,author, language, link, pages, title, year FROM book";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            ResultSet result = preparedStatement.executeQuery();
            List<Book> resultList = new ArrayList<>();
            while (result.next()) {
                Book book = deserializeSimpleBookFromResultSet(result);
                resultList.add(book);
            }
            return ImmutableList.copyOf(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ImmutableList.of();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        String insertTableSQL = "SELECT * FROM book WHERE id=?";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                Book book = deserializeBookFromResultSet(result);
                return Optional.ofNullable(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public void deleteBookById(Long id) {
        String insertTableSQL = "DELETE FROM book WHERE id=?";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<Object> addNewBook(Book book) {
        if (book != null) {
            String insertTableSQL = "INSERT INTO book"
                    + "(author, country, language, link, pages, title, year, user_id) VALUES"
                    + "(?,?,?,?,?,?,?,?)";
            try (Connection connection = Connector.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
                preparedStatement.setString(1, book.getAuthor());
                preparedStatement.setString(2, book.getCountry());
                preparedStatement.setString(3, book.getLanguage());
                preparedStatement.setString(4, book.getLink());
                preparedStatement.setInt(5, book.getPages());
                preparedStatement.setString(6, book.getTitle());
                preparedStatement.setInt(7, book.getYear());
                preparedStatement.setLong(8, book.getUser().getId());
//                preparedStatement.setBlob(9, new ByteArrayInputStream(book.getImage()));
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> getBooksByUser(Long userId) {
        String insertTableSQL = "SELECT * FROM book WHERE user_id=?";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            preparedStatement.setLong(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            List<Book> resultList = new ArrayList<>();
            while (result.next()) {
                Book book = deserializeSimpleBookFromResultSet(result);
                resultList.add(book);
            }
            return ImmutableList.copyOf(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ImmutableList.of();
    }

    private Book deserializeSimpleBookFromResultSet(ResultSet result) throws SQLException, IOException {

        return Book.builder().id(result.getLong("id"))
                .year(result.getInt("year"))
                .title(result.getString("title"))
                .language(result.getString("language"))
                .author(result.getString("author"))
                .link(result.getString("link"))
                .pages(result.getInt("pages"))
                .country(result.getString("country"))
                .build();
    }


    private Book deserializeBookFromResultSet(ResultSet result) throws SQLException, IOException {

        Book book = deserializeSimpleBookFromResultSet(result);
        try {
            Long userId = result.getLong("user_id");
            User user = userDao.getUserById(userId).orElse(UNDEFINED_USER);
            book.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Blob blob = result.getBlob("image");
            if (blob != null) {
                byte[] image = IOUtils.toByteArray(blob.getBinaryStream());
                book.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

}
