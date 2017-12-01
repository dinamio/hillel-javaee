package edu.karazin.shop.dao;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDataAccessObject extends CrudRepository<OrderList, Integer> {

//    boolean completeOrder(int id);

    @Query(value = "select b.bookLists from OrderList b where b.orderId=:id")
    List<BookList> getOrderBooks(int id);

//    @Query(value = "select o from OrderList o where o.userid=:id")
    OrderList getOrderListByUserId(Integer id);
}
