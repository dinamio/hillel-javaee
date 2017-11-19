package edu.karazin.shop.dao;

import edu.karazin.shop.model.User;

public interface UsersDataAccessObject {
    boolean getUser(String login, String password);
    boolean createUser(User user);
    User getUserByName(String login);
}
