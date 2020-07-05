package com.cice.controller;

import com.cice.dao.CustomerDaoImpl;
import com.cice.dao.OrderDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.idao.IOrderDao;
import com.cice.model.Customer;
import com.cice.model.Order;
import com.cice.view.CustomerView;
import com.cice.view.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderView orderView = new OrderView();


    public void getOrders() throws SQLException {

        IOrderDao iOrderDao = new OrderDaoImpl();
        List<Order> orders = iOrderDao.getOrders();
    }

    public void getOrderById(int orderId) throws SQLException {
        IOrderDao iOrderDao = new OrderDaoImpl();
        List<Order> orders = iOrderDao.getOrderById(orderId);
        if(orders.size()==0) {
            log.warn("No existe ningún pedido con ese ID");
        }
    }

    public List<Double> getOrderTotal() throws SQLException {
        IOrderDao iOrderDao = new OrderDaoImpl();
        List<Double>response = iOrderDao.getOrderTotal();
        return response;
    }



}
