package edu.karazin.shop.dao;

import edu.karazin.shop.model.OrderStory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderStoryDataAccessObject extends CrudRepository<OrderStory, Integer> {
    List<OrderStory> findAllByName(String name);
}
