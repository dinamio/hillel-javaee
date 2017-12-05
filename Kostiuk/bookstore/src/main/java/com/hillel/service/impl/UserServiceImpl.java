package com.hillel.service.impl;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.UserDao;
import com.hillel.model.User;
import com.hillel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        return Optional.ofNullable(userDao.save(user));
    }

    @Override
    public List<User> getAll() {
        return ImmutableList.copyOf(userDao.findAll());
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userDao.getByLoginName(login);
    }


    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(userDao.findOne(id));
    }

    @Override
    public void delete(User user) {
        Optional.ofNullable(user).ifPresent(userDao::delete);
    }

    @Override
    public Optional<User> update(Long id, Consumer<User> updater) {
        User user = userDao.findOne(id);
        updater.accept(user);
        return Optional.ofNullable(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByLogin(username).get();
    }
}
