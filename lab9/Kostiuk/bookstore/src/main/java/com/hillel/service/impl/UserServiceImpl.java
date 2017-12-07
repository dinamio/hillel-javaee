package com.hillel.service.impl;

import com.hillel.dao.UserDao;
import com.hillel.model.User;
import com.hillel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> insert(User user) {
        Optional.ofNullable(userDao.insert(user)).ifPresent(user::setId);
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public Optional<User> getEagerStateByLogin(String login) {
        return userDao.getEagerStateByLogin(login);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void delete(User user) {
        Optional.ofNullable(user).map(User::getId).ifPresent(userDao::deleteById);
    }

    @Override
    public void update(Long id, Consumer<User> updater) {
        userDao.update(id, updater);
    }

    @Override
    public void update(User entity, Consumer<User> updater) {
        userDao.update(entity, updater);
    }

}
