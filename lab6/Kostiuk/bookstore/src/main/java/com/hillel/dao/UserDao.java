package com.hillel.dao;

import com.hillel.model.Book;
import com.hillel.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUsers();

    Optional<User> getUserByLogin(String login);

    Optional<User> getUserById(Long id);

    Optional<User> addNewUser(User user);

}
