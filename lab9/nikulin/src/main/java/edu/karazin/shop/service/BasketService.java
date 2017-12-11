package edu.karazin.shop.service;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.model.OrderStory;
import edu.karazin.shop.model.User;

import java.util.List;

public interface BasketService {
    OrderList createOrder(User user);

    OrderList getOrderByUserId(User user);

    OrderList addBookToOrder(User user, BookList bookList);

    OrderList removeBookFromOrder(User user, Long id);

    void removeOrder(User user);

    void addOrderToStory(OrderList order);

    List<OrderStory> getOrderStory(String name);

    List<OrderStory> getAllOrders();
}
