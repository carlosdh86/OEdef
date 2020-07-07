package com.cice.controller;

import com.cice.dao.CustomerDaoImpl;
import com.cice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    CustomerDaoImpl customerDao = new CustomerDaoImpl();


    public boolean createCustomer (Customer customer) throws SQLException {

        return customerDao.createCustomer(customer);
    }
    
    public void getCustomers() throws SQLException {

        List<Customer> customers = customerDao.getCustomers();
    }

    public void getCustomersByLastName(String lastName) throws SQLException {

        List<Customer> customers = customerDao.getCustomerById(lastName);

        if(customers.size()==0) {
            log.warn("No existe ningún cliente con ese apellido");
        }
    }

    public boolean updateCustomer(Customer customer, int field) throws SQLException {

        return customerDao.updateCustomer(customer,field);
    }

    public boolean deleteCustomer (int customerID) throws SQLException {

        return customerDao.deleteCustomer(customerID);
    }

}




