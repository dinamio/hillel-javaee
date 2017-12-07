package edu.karazin.shop.service;

import edu.karazin.shop.model.User;

public interface UserService {
    void createUser(User user);

    boolean getUser(String login, String password);

    User getUserByName(String login);
}
