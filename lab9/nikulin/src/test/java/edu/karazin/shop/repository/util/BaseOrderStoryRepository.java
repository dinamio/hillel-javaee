package edu.karazin.shop.repository.util;

import edu.karazin.shop.model.OrderStory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BaseOrderStoryRepository {

    public OrderStory getOrderStory(int id){
        OrderStory orderStory = new OrderStory();
        orderStory.setId(id);
        orderStory.setDescription("Test" + id);
        orderStory.setName("Test" + id);
        orderStory.setDate(new Date());
        orderStory.setBooks(new ArrayList<>());
        return orderStory;
    }
}
