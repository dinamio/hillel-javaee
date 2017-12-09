package edu.karazin.shop.repository.util;

import edu.karazin.shop.model.User;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BaseUserRepositoryTest {

    public User getUser(int id){
        User user = new User();
        user.setId(id);
        user.setUsername("Test" + id);
        user.setPassword("Test" + id);
        user.setEmail("Test" + id);
        user.setName("Test" + id);
        user.setSurname("Test" + id);
        user.setRole("Test" + id);
        return user;
    }
}
