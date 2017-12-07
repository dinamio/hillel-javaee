package com.hillel.service;

import com.hillel.model.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService extends BasicCRUDService<User> {

    Optional<User> getByLogin(@NonNull String login);

    Optional<User> getEagerStateByLogin(@NonNull String login);
}
