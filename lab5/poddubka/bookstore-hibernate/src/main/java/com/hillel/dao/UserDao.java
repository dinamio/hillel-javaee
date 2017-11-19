package com.hillel.dao;

import com.hillel.model.User;

public interface UserDao {

    User findById(Integer id);

    User findByName(String name);

    Integer insertUser(User user);

}
