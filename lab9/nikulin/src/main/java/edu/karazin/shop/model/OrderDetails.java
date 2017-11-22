package edu.karazin.shop.model;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderdetails;

    private int orderid;

    private long bookid;

    private int quantity;

    public int getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(int orderdetails) {
        this.orderdetails = orderdetails;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "orderid", referencedColumnName = "orderid", insertable = false, updatable = false)
    private Order order;
}
