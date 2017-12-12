package database.dao;

import database.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, String>{
}
