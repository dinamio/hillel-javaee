package edu.karazin.shop.service;

import edu.karazin.shop.model.User;

import java.util.List;

public interface UserService {
    boolean createUser(User user);
    List<User> getAllUsers();
    User getUserByName(String login);
    User getUserById(Integer id);
    User updateUser(User user);
    void deleteUser(Integer id);
}
