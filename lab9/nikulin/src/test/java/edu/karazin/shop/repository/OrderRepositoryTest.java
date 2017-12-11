package edu.karazin.shop.repository;

import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.repository.util.BaseOrderRepositoryTest;
import org.hibernate.criterion.Order;
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
public class OrderRepositoryTest extends BaseOrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @After
    public void cleaner(){
        orderRepository.deleteAll();
    }

    @Test
    public void shouldAddOrder(){
        OrderList order = getOrderList(1);

        OrderList save = orderRepository.save(order);
        OrderList actual = orderRepository.findOne(save.getOrderId());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateOrder(){
        OrderList order = getOrderList(1);

        OrderList save = orderRepository.save(order);
        OrderList actual = orderRepository.findOne(save.getOrderId());

        assertEquals(save, actual);

        actual.setUserId(2);
        actual.setTotalPrice(2d);

        OrderList saveNew = orderRepository.save(actual);
        OrderList update = orderRepository.findOne(saveNew.getOrderId());

        assertNotEquals(update, save);
    }

    @Test
    public void shouldDeleteOrder(){
        OrderList order = getOrderList(1);

        OrderList save = orderRepository.save(order);
        OrderList actual = orderRepository.findOne(save.getOrderId());

        assertEquals(save, actual);

        orderRepository.delete(actual.getOrderId());

        OrderList delete = orderRepository.findOne(actual.getOrderId());

        assertNull(delete);
    }

    @Test
    public void shouldFindAll(){
        OrderList order1 = getOrderList(1);
        OrderList order2 = getOrderList(2);

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<OrderList> orders = (List<OrderList>) orderRepository.findAll();
        assertEquals(orders.size(), 2);
    }

    @Test
    public void shouldGetOrderListByUserId(){
        OrderList order = getOrderList(1);

        OrderList save = orderRepository.save(order);
        OrderList actual = orderRepository.getOrderListByUserId(save.getUserId());

        assertEquals(save, actual);
    }
}
