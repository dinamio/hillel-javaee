package edu.karazin.shop.service.impl;

import edu.karazin.shop.dao.UsersDataAccessObject;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersDataAccessObject usersDataAccessObject;

    @Override
    public void createUser(User user){
        usersDataAccessObject.save(user);
    }

    @Override
    public boolean getUser(String login, String password){
        return usersDataAccessObject.getUserByUsernameAndPassword(login, password);
    }

    @Override
    public User getUserByName(String login){
        return usersDataAccessObject.getUserByUsername(login);
    }
}
