package edu.karazin.shop.dao;

import edu.karazin.shop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersDataAccessObject extends CrudRepository<User, Integer>{
    boolean getUserByUsernameAndPassword(String username, String password);
    User getUserByUsername(String username);
}
