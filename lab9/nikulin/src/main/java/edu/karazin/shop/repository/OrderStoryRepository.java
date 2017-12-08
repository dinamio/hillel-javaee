package edu.karazin.shop.repository;

import edu.karazin.shop.model.OrderStory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderStoryRepository extends CrudRepository<OrderStory, Integer> {
    List<OrderStory> findAllByName(String name);
}
