package model;

public interface OrderDataAccessObject {

    Order getOrderById(int id);

    int createOrder(Order order, OrderDetails orderDetails);

    boolean completeOrder(int id);
}
