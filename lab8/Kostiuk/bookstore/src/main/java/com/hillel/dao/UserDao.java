package com.hillel.dao;

import com.hillel.model.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserDao extends BasicCRUD<User> {

    Optional<User> getByLogin(@NonNull String login);

    Optional<User> getEagerStateByLogin(@NonNull String login);

}
