package model.jdbc;

import model.Book;
import model.BookDataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class JDBCBookDataAccessObject implements BookDataAccessObject {

    private Properties connInfo;
    private String dataBaseUrl = "jdbc:mysql://localhost:3306/Books?useUnicode=true&characterEncoding=utf8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String userName = "root";
    private String password = "123";
    private Connection connection = null;

    private static JDBCBookDataAccessObject jdbcBookDataAccessObject;

    public static JDBCBookDataAccessObject getJdbcBookDataAccessObject(){
        if (Objects.equals(jdbcBookDataAccessObject, null)){
            jdbcBookDataAccessObject = new JDBCBookDataAccessObject();
            return jdbcBookDataAccessObject;
        } else return jdbcBookDataAccessObject;
    }

    private JDBCBookDataAccessObject() {
        connInfo = new Properties();
        connInfo.put("user", userName);
        connInfo.put("password", password);
        connInfo.put("useUnicode", "true"); // (1)
        connInfo.put("charSet", "UTF8");
        System.out.println(connInfo);
        System.out.println(dataBaseUrl);

    }

    @Override
    public boolean addNewBook(Book book) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dataBaseUrl, connInfo);
            String query = "INSERT INTO BookList (Id, BookName, Author, Publisher) VALUES (UUID_SHORT(), '"+ book.getName()+ "', '" + book.getAuthor() + "', '" + book.getPublisher() + "')";
            Statement statement = connection.createStatement();
            statement.execute("SET NAMES utf8");
            statement.execute("SET collation_connection='utf8_general_ci'");
            statement.execute(query);
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dataBaseUrl, userName, password);
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }

        String query = "SELECT  *FROM BookList";

        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            ResultSet rs4 = stmt.executeQuery(query);

            while (rs4.next()) {
                Book book = new Book();
                book.setId(Long.valueOf(rs4.getString(1)));
                book.setName(rs4.getString(2));
                book.setAuthor(rs4.getString(3));
                book.setPublisher(rs4.getString(4));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }

    @Override
    public Book getBookById(long id) {

        try {
            connection = DriverManager.getConnection(dataBaseUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT BookName, Author, Publisher COUNT(*) FROM BookList WHERE User=" + id;

        Statement stmt = null;

        Book book = new Book();
        try {
            stmt = connection.createStatement();
            ResultSet rs4 = stmt.executeQuery(query);

            while (rs4.next()) {
                book.setName(rs4.getString(1));
                book.setAuthor(rs4.getString(2));
                book.setPublisher(rs4.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return book;
    }

    @Override
    public boolean removeById(long id) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dataBaseUrl, connInfo);
            String query = "DELETE FROM BookList WHERE Id=" + id;
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(Book book) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dataBaseUrl, connInfo);
            String query = "UPDATE BookList SET BookName='" + book.getName() + "', Author='" + book.getAuthor() + "', Publisher='" + book.getPublisher() + "' WHERE Id=" + book.getId();
            Statement statement = connection.createStatement();
            statement.execute("SET NAMES utf8");
            statement.execute("SET collation_connection='utf8_general_ci'");
            statement.execute(query);
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
