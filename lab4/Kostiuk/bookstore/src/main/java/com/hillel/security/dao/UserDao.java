package com.hillel.security.dao;

import com.google.common.collect.ImmutableList;
import com.hillel.security.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDao {

    private final static Map<String, User> USERS_MAP = new HashMap<>();

    static {
        User admin = User.builder()
                .firstName("Nikolai")
                .lastName("Gogol")
                .loginName("admin")
                .encodedPassword("$2a$10$LEE91eMuUrCq8fSUzuR72eYoS8YYfEp3ebKhzrCHc7ltnc40BtcFO")
                .build();
        USERS_MAP.put("admin", admin);
    }

    private Map<String, User> getUsersMap() {
        return USERS_MAP;
    }

    public List<User> getAllUsers() {
        return ImmutableList.copyOf(getUsersMap().values());
    }

    public Optional<User> getUserByLogin(String login) {
        return Optional.ofNullable(USERS_MAP.get(login));
    }

    public Optional<User> addNewUser(User user) {
        Optional.ofNullable(user).ifPresent(u -> getUsersMap().put(u.getLoginName(), u));
        return Optional.ofNullable(user);
    }

    public boolean isNonExist(String login) {
        return !USERS_MAP.containsKey(login);
    }


}
