package edu.karazin.shop.service.impl;

import edu.karazin.shop.repository.UsersRepository;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user){
        if (usersRepository.getUserByUsername(user.getUsername()) == null){
            user.setRole(user.getUsername().equals("admin") ? "ROLE_ADMIN" : "ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersRepository.save(user);
            return true;
        } else return false;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) usersRepository.findAll();
    }

    @Override
    public User getUserByName(String login){
        return usersRepository.getUserByUsername(login);
    }

    @Override
    public User getUserById(Integer id) {
        return usersRepository.findOne(id);
    }

    @Override
    public User updateUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        usersRepository.delete(id);
    }
}
