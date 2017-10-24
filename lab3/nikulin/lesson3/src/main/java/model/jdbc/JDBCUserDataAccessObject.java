package model.jdbc;

import model.User;
import model.UsersDataAccessObject;

import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class JDBCUserDataAccessObject implements UsersDataAccessObject {


    private Properties connInfo;
    private String dataBaseUrl = "jdbc:mysql://localhost:3306/Books?useUnicode=true&characterEncoding=utf8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String userName = "root";
    private String password = "123";
    private Connection connection = null;

    private static JDBCUserDataAccessObject jdbcUserDataAccessObject;

    public static JDBCUserDataAccessObject getJdbcUserDataAccessObject(){
        if (Objects.equals(jdbcUserDataAccessObject, null)){
            jdbcUserDataAccessObject = new JDBCUserDataAccessObject();
            return jdbcUserDataAccessObject;
        } else return jdbcUserDataAccessObject;
    }

    private JDBCUserDataAccessObject() {
        connInfo = new Properties();
        connInfo.put("user", userName);
        connInfo.put("password", password);
        connInfo.put("useUnicode", "true"); // (1)
        connInfo.put("charSet", "UTF8");
        System.out.println(connInfo);
        System.out.println(dataBaseUrl);

    }



    @Override
    public boolean getUser(String login, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dataBaseUrl, userName, this.password);
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }

        String query = "SELECT UserLogin, UserPassword FROM Users WHERE UserLogin='" + login + "'";

        Statement stmt = null;

        User user = new User();
        try {
            stmt = connection.createStatement();
            ResultSet rs4 = stmt.executeQuery(query);
            if (Objects.equals(rs4, null)) return false;
            while (rs4.next()) {
                user.setLogin(rs4.getString(1));
                user.setPassword(rs4.getString(2));
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

        return Objects.equals(user.getPassword(), password);
    }

    @Override
    public boolean createUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dataBaseUrl, connInfo);
            String query = "INSERT INTO Users VALUES ('"+ user.getLogin() + "', '" + user.getPassword() + "')";
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
