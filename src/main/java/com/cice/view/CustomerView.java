package com.cice.view;

import com.cice.model.Customer;

import java.util.List;

public class CustomerView {

    public void getCustomers (List<Customer> customers) {
        for (Customer customer:customers) {
            System.out.println("Datos del cliente: "+customer);
        }
    }
}
