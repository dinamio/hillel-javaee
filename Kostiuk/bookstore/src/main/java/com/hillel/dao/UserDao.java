package com.hillel.dao;

import com.hillel.model.User;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {

    Optional<User> getByLoginName(@NonNull String login);

//    Optional<User> getEagerStateByLoginName(@NonNull String login);

}
