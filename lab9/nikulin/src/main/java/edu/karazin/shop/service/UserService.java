package edu.karazin.shop.service;

import edu.karazin.shop.model.User;
import edu.karazin.shop.dao.hibernate.HibernateUserDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private static UserService userService;

    @Autowired
    private HibernateUserDataAccessObject hibernateUserDataAccessObject;

    public boolean createUser(User user){
        return hibernateUserDataAccessObject.createUser(user);
    }

    public boolean getUser(String login, String password){
        return hibernateUserDataAccessObject.getUser(login, password);
    }

    public User getUserByName(String login){
        return hibernateUserDataAccessObject.getUserByName(login);
    }
}
