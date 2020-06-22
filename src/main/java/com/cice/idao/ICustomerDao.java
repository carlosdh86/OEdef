package com.cice.idao;

import com.cice.model.Customer;

import java.util.List;

public interface ICustomerDao {
    public boolean create (Customer customer);
    public List<Customer> read();
    public boolean update(Customer customer);
    public boolean delete(Customer customer);

}
