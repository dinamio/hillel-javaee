package edu.karazin.shop.repository;

import edu.karazin.shop.model.OrderStory;
import edu.karazin.shop.repository.util.BaseOrderStoryRepository;
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
public class OrderStoryRepositoryTest extends BaseOrderStoryRepository {

    @Autowired
    OrderStoryRepository orderStoryRepository;

    @After
    public void cleaner(){
        orderStoryRepository.deleteAll();
    }

    @Test
    public void shouldAddOrderStory(){
        OrderStory orderStory = getOrderStory(1);

        OrderStory save = orderStoryRepository.save(orderStory);
        OrderStory actual = orderStoryRepository.findOne(save.getId());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateOrderStory(){
        OrderStory orderStory = getOrderStory(1);

        OrderStory save = orderStoryRepository.save(orderStory);
        OrderStory actual = orderStoryRepository.findOne(save.getId());

        assertEquals(save, actual);

        actual.setId(1);
        actual.setName("Test2");

        OrderStory saveNew = orderStoryRepository.save(actual);
        OrderStory update = orderStoryRepository.findOne(saveNew.getId());

        assertNotEquals(update, save);
        assertEquals(update.getId(), save.getId());
    }

    @Test
    public void shouldDeleteOrderStory(){
        OrderStory orderStory = getOrderStory(1);

        OrderStory save = orderStoryRepository.save(orderStory);
        OrderStory actual = orderStoryRepository.findOne(save.getId());

        assertEquals(save, actual);

        orderStoryRepository.delete(actual.getId());
        OrderStory delete = orderStoryRepository.findOne(actual.getId());

        assertNull(delete);
    }

    @Test
    public void shouldFindAll(){
        OrderStory orderStory1 = getOrderStory(1);
        OrderStory orderStory2 = getOrderStory(2);

        orderStoryRepository.save(orderStory1);
        orderStoryRepository.save(orderStory2);

        List<OrderStory> orderStories = (List<OrderStory>) orderStoryRepository.findAll();

        assertEquals(orderStories.size(), 2);
    }

    @Test
    public void shouldFindAllByName(){
        OrderStory orderStory1 = getOrderStory(1);
        OrderStory orderStory2 = getOrderStory(2);

        OrderStory save1 = orderStoryRepository.save(orderStory1);
        OrderStory save2 = orderStoryRepository.save(orderStory2);

        List<OrderStory> orderStories = orderStoryRepository.findAllByName(save1.getName());

        assertEquals(orderStories.size(), 1);
    }

}
