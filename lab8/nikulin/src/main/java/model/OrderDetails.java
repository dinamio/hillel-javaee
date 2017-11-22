package model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderdetails;

    private int orderid;

    private long bookid;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "orderid", referencedColumnName = "orderid", insertable = false, updatable = false)
    private Order order;
}
