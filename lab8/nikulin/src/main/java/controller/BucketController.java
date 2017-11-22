package controller;

import model.Order;
import model.OrderDetails;
import model.hibernate.HibernateOrderDataAccessObject;
import util.CurrentUser;

public class BucketController {



    public static void addBook(){
        Order order = new Order();
        order.setUserid(CurrentUser.getUser().getId());
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setBookid(1);
        orderDetails.setQuantity(1);
        orderDetails.setOrderid(order.getOrderid());
        HibernateOrderDataAccessObject.getHibernateOrderDataAccessObject().createOrder(order, orderDetails);
    }
}
