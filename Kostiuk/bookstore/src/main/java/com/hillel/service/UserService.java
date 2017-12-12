package com.hillel.service;

import com.hillel.model.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends BasicCRUDService<User>, UserDetailsService {

    Optional<User> getByLogin(@NonNull String login);

}
