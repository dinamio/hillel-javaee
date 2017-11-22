package edu.karazin.shop.dao;

import edu.karazin.shop.model.Order;
import edu.karazin.shop.model.OrderDetails;

public interface OrderDataAccessObject {

    Order getOrderById(int id);

    int createOrder(Order order, OrderDetails orderDetails);

    boolean completeOrder(int id);
}
