package com.hillel.dao.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.model.Book;
import com.hillel.repository.Connector;
import com.hillel.model.User;
import com.hillel.dao.UserDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {

    static volatile JDBCUserDao instance;

    public static JDBCUserDao getInstance() {
        if (instance == null) {
            synchronized (JDBCUserDao.class) {
                if (instance == null) {
                    instance = new JDBCUserDao();
                }
            }
        }
        return instance;
    }

    private JDBCUserDao() {
    }


    @Override
    public List<User> getAllUsers() {
        String insertTableSQL = "SELECT * FROM user";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            ResultSet result = preparedStatement.executeQuery();
            List<User> resultList = new ArrayList<>();
            while (result.next()) {
                User user = deserializeUserFromResultSet(result);
                resultList.add(user);
            }
            return ImmutableList.copyOf(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ImmutableList.of();
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        String insertTableSQL = "SELECT * FROM user WHERE login=?";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            preparedStatement.setString(1, login);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                User user = deserializeUserFromResultSet(result);
                return Optional.ofNullable(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        String insertTableSQL = "SELECT * FROM user WHERE id=?";
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                User user = deserializeUserFromResultSet(result);
                return Optional.ofNullable(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> addNewUser(User user) {
        if (user != null) {
            String insertTableSQL = "INSERT INTO user"
                    + "(login, first_name, last_name, encoded_password) VALUES"
                    + "(?,?,?,?)";
            try (Connection connection = Connector.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL)) {
                preparedStatement.setString(1, user.getLoginName());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.setString(3, user.getLastName());
                preparedStatement.setString(4, user.getEncodedPassword());
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private User deserializeUserFromResultSet(ResultSet result) throws SQLException, IOException {
        return User.builder().id(result.getLong("id"))
                .loginName(result.getString("login"))
                .firstName(result.getString("first_name"))
                .lastName(result.getString("last_name"))
                .encodedPassword(result.getString("encoded_password"))
                .build();
    }
}
