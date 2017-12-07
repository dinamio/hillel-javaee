package com.hillel.bookstorespringboot.dao;

import com.hillel.bookstorespringboot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {

    User findUserByUserName(String principalName);
}
