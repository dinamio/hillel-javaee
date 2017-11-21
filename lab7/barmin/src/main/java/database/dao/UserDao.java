package database.dao;

import database.model.User;

public interface UserDao {
    User getUserByID(String id);
    int loginUser(String username, String password);
    boolean registerUser(String username, String password);
}
