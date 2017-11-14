package model;

public interface UsersDataAccessObject {
    boolean getUser(String login, String password);
    boolean createUser(User user);
}
