package com.cice.idao;

import com.cice.model.Order;

import java.sql.SQLException;

public interface IOrderDao {

    public int createOrder (Order order, boolean isCreated) throws SQLException;

    public boolean finishOrder (Order order) throws SQLException;

    public boolean deleteOrder (Order order) throws SQLException;



}
