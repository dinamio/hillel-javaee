package edu.karazin.shop.repository;

import edu.karazin.shop.model.User;
import edu.karazin.shop.repository.util.BaseUserRepositoryTest;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserRepositoryTest extends BaseUserRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @After
    public void cleaner(){
        usersRepository.deleteAll();
    }

    @Test
    public void shouldAddUser(){
        User user = getUser(1);

        User save = usersRepository.save(user);
        User actual = usersRepository.findOne(save.getId());

        assertEquals(save, actual);
    }


    @Test
    public void shouldUpdateUser(){
        User user = getUser(1);

        usersRepository.save(user);

        User save = usersRepository.findOne(user.getId());
        save.setUsername("Test2");
        save.setPassword("Test2");
        save.setEmail("Test2");
        save.setName("Test2");
        save.setSurname("Test2");
        save.setRole("Test2");

        usersRepository.save(save);

        User actual = usersRepository.findOne(save.getId());

        assertNotEquals(user, actual);
        assertEquals(user.getId(), actual.getId());
    }

    @Test
    public void shouldDeleteUser(){
        User user = getUser(1);

        User save = usersRepository.save(user);
        User actual = usersRepository.findOne(save.getId());

        assertEquals(save, actual);

        usersRepository.delete(save.getId());
        User delete = usersRepository.findOne(user.getId());

        assertNull(delete);
    }

    @Test
    public void shouldFindUserByUsername(){
        User user = getUser(1);

        User save = usersRepository.save(user);

        User actual = usersRepository.getUserByUsername(save.getUsername());

        assertEquals(save, actual);
    }

    @Test
    public void shouldFindAll(){
        User user1 = getUser(1);
        User user2 = getUser(2);

        usersRepository.save(user1);
        usersRepository.save(user2);

        List<User> users = (List<User>) usersRepository.findAll();
        assertEquals(users.size(), 2);
    }
}
