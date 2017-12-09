package edu.karazin.shop.repository.util;

import edu.karazin.shop.model.OrderList;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BaseOrderRepositoryTest {

    public OrderList getOrderList(int id){
        OrderList orderList = new OrderList();
        orderList.setOrderId(id);
        orderList.setUserId(id);
        orderList.setTotalPrice((double) id);
        orderList.setBookLists(new ArrayList<>());
        orderList.setOrderDetails(new ArrayList<>());
        return orderList;
    }
}
