package service;

import model.User;
import model.hibernate.HibernateUserDataAccessObject;

import java.util.Objects;

public class UserService {

    private static UserService userService;
    private HibernateUserDataAccessObject hibernateUserDataAccessObject;

    public static UserService getUserService(){
        if (Objects.equals(userService, null)) userService = new UserService();
        return userService;
    }

    public boolean createUser(User user){
        return hibernateUserDataAccessObject.createUser(user);
    }

    public boolean getUser(String login, String password){
        return hibernateUserDataAccessObject.getUser(login, password);
    }
}
