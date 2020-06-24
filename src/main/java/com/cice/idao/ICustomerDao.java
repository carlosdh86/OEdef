package com.cice.idao;

import com.cice.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDao {
    public boolean create (Customer customer) throws SQLException;
    public List<Customer> read();
    public boolean update(Customer customer);
    public boolean delete(Customer customer);

}
