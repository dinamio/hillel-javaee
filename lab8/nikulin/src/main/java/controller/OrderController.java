package controller;

import model.Order;
import model.OrderDetails;
import model.hibernate.HibernateOrderDataAccessObject;
import model.hibernate.HibernateUserDataAccessObject;
import util.CurrentUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("buy") != null){
            Order order = new Order();
            order.setUserid(CurrentUser.getUser().getId());
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setBookid(Long.parseLong(req.getParameter("bookid")));
            orderDetails.setOrderid(order.getOrderid());
            orderDetails.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            HibernateOrderDataAccessObject.getHibernateOrderDataAccessObject().createOrder(order, orderDetails);
        }
    }
}
