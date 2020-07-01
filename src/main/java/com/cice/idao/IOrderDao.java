package com.cice.idao;

import com.cice.model.Order;

import java.sql.SQLException;

public interface IOrderDao {

    public boolean createOrder (Order order, boolean isCreated) throws SQLException;

}
