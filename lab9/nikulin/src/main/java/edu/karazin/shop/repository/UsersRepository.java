package edu.karazin.shop.repository;

import edu.karazin.shop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Integer>{
    User getUserByUsername(String username);
}
