package edu.karazin.shop.repository;

import edu.karazin.shop.model.OrderList;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderList, Integer> {
    OrderList getOrderListByUserId(Integer id);
}
