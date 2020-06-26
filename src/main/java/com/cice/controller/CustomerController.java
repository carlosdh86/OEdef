package com.cice.controller;

import com.cice.dao.CustomerDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.model.Customer;
import com.cice.view.CustomerView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private CustomerView customerView = new CustomerView();

    public void createCustomer(Customer customer) throws SQLException {

        customerView.createCustomer();
    }


    public void getCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        ICustomerDao iCustomerDao = new CustomerDaoImpl();
        customers = iCustomerDao.getCustomers();
        //customerView.getCustomers(customers);
        for (Customer customer:customers) {
            System.out.println("Datos del cliente: "+customer);
        }
    }

    public void getCustomersByLastName(String lastName){
        List<Customer> customers = new ArrayList<Customer>();
        ICustomerDao iCustomerDao = new CustomerDaoImpl();
        customers = iCustomerDao.getCustomerById(lastName);
        //customerView.getCustomersById(customers);
        for (Customer customer:customers) {
            System.out.println("Datos del cliente: "+customer);
        }

    }

}




