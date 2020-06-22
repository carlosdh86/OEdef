package com.cice.controller;

import com.cice.dao.CustomerDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.model.Customer;
import com.cice.view.CustomerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private CustomerView customerView = new CustomerView();

    public void getCustomers() {

        List<Customer> customers = new ArrayList<Customer>();
        ICustomerDao iCustomerDao = new CustomerDaoImpl();
        customers = iCustomerDao.read();
        customerView.getCustomers(customers);

    }

}




