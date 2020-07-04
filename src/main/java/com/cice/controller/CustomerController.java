package com.cice.controller;

import com.cice.dao.CustomerDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.model.Customer;
import com.cice.view.CustomerView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private CustomerView customerView = new CustomerView();

    public void createCustomer(Customer customer) throws SQLException {

        customerView.createCustomer();
    }
    
    public void getCustomers() {

        ICustomerDao iCustomerDao = new CustomerDaoImpl();
        List<Customer> customers = iCustomerDao.getCustomers();
    }

    public void getCustomersByLastName(String lastName){
        ICustomerDao iCustomerDao = new CustomerDaoImpl();
        List<Customer> customers = iCustomerDao.getCustomerById(lastName);
        if(customers.size()==0) {
            log.warn("No existe ningún cliente con ese apellido");
        }
    }

}




