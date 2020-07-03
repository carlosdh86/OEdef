package com.cice.idao;

import com.cice.model.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ICustomerDao {
    public boolean createCustomer (Customer customer) throws SQLException;
    public List<Customer> getCustomers();
    public List<Customer> getCustomerById (String lastName);
    public boolean updateCustomer(Customer customer, int field) throws SQLException;
    public boolean deleteCustomer(int customerID);


}
