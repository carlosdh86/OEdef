package com.cice.controller;

import com.cice.dao.OrderDaoImpl;
import com.cice.idao.IOrderDao;
import com.cice.model.Order;
import com.cice.view.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class OrderController {


    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    OrderDaoImpl orderDao = new OrderDaoImpl();

    public List<Order> getOrders() throws SQLException {
        IOrderDao iOrderDao = new OrderDaoImpl();
        return iOrderDao.getOrders();
    }

    public Order getOrderById(int orderId) throws SQLException {
        return orderDao.getOrderById(orderId);
    }

    public List<Double> getOrderTotal() throws SQLException {
        List<Double> response = orderDao.getOrderTotal();
        return response;
    }

    public boolean updateOrder(Order order,int field) throws SQLException {
        return orderDao.updateOrder(order,field);
    }

    public boolean deleteOrder (Order order) throws SQLException {
        return orderDao.deleteOrder(order);
    }

    public int createOrder (Order order, boolean isCreated) throws SQLException {
        return orderDao.createOrder(order,isCreated);

    }

    public boolean finishOrder (Order order) throws SQLException {
        //System.out.println("El importe del pedido es " + orderDao.updateOrderTotal(order.getOrder_id()) + " €");
        System.out.println("El importe del pedido es " + new DecimalFormat("#.##").format(orderDao.updateOrderTotal(order.getOrder_id())) + " €");
        return orderDao.finishOrder(order);

    }
}
