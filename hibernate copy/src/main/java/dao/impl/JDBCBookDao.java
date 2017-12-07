package dao.impl;

import dao.BookDao;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eugen on 10/29/17.
 */
public class JDBCBookDao implements BookDao {

    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "qwerty";

    public JDBCBookDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        final String query= "select * from book";
        List<Book> books = new ArrayList<Book>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt(1));
                book.setPages(resultSet.getInt(3));
                book.setName(resultSet.getString(2));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Integer insert(Book book) {
        String query= "insert into book(name,pages)  values (%s,%s)";
        query = String.format(query, addBrackets(book.getName()), book.getPages());
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

        }
        return 12321;
    }

    public Book findBookById(Integer id) {
        final String query= "select * from book where id = ?";
        System.out.println(query);
        List<Book> books = new ArrayList<Book>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(id));
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt(1));
                book.setPages(resultSet.getInt(3));
                book.setName(resultSet.getString(2));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books.get(0);
    }

    private String addBrackets(String value) {
        return "'"+value+"'";
    }
}
