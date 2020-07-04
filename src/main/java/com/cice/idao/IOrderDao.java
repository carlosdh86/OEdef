package com.cice.idao;

import com.cice.model.Customer;
import com.cice.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDao {

    public List<Order> getOrders() throws SQLException;

    public List<Order> getOrderById (int orderId) throws SQLException;

    public int createOrder (Order order, boolean isCreated) throws SQLException;

    public boolean finishOrder (Order order) throws SQLException;

    public boolean deleteOrder (Order order) throws SQLException;

    public List<Double> getOrderTotal () throws SQLException;



    //public boolean updateOrder (Order order, int field) throws SQLException;

}
