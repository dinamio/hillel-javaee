package edu.karazin.shop.dao.hibernate;

import edu.karazin.shop.model.Order;
import edu.karazin.shop.dao.OrderDataAccessObject;
import edu.karazin.shop.model.OrderDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import edu.karazin.shop.util.SessionFactoryImpl;

import java.util.Objects;


public class HibernateOrderDataAccessObject implements OrderDataAccessObject {

    private SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();
    private static HibernateOrderDataAccessObject hibernateOrderDataAccessObject;

    public static HibernateOrderDataAccessObject getHibernateOrderDataAccessObject(){
        if (Objects.equals(hibernateOrderDataAccessObject, null)) hibernateOrderDataAccessObject = new HibernateOrderDataAccessObject();
        return hibernateOrderDataAccessObject;
    }

    @Override
    public Order getOrderById(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from Order o where o.orderid = " + id;
            Query query = session.createQuery(hql);
            Order order = (Order) query.uniqueResult();
            transaction.commit();
            return order;
        } finally {
            session.close();
        }
    }

    @Override
    public int createOrder(Order order, OrderDetails orderDetails) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(order);
            session.save(orderDetails);
            transaction.commit();
            return order.getOrderid();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean completeOrder(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Order order = getOrderById(id);
            session.remove(order);
            transaction.commit();
            return true;
        } finally {
            session.close();
        }
    }
}
