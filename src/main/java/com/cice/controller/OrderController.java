package com.cice.controller;

import com.cice.dao.OrderDaoImpl;
import com.cice.model.Order;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class OrderController {

    OrderDaoImpl orderDao = new OrderDaoImpl();

    public int createOrder (Order order, boolean isCreated) throws SQLException {
        return orderDao.createOrder(order,isCreated);
    }

    public List<Order> getOrders() throws SQLException {
        return orderDao.getOrders();
    }

    public Order getOrderById(int orderId) throws SQLException {
        return orderDao.getOrderById(orderId);
    }

    public boolean updateOrder(Order order,int field) throws SQLException {
        return orderDao.updateOrder(order,field);
    }

    public boolean deleteOrder (Order order) throws SQLException {
        return orderDao.deleteOrder(order);
    }

    public List<Double> getOrderTotal() throws SQLException {
        return orderDao.getOrderTotal();
    }

    public boolean finishOrder (Order order) throws SQLException {
        return orderDao.finishOrder(order);
    }

}

