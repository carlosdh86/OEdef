package com.cice.idao;

import com.cice.model.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ICustomerDao {
    public boolean createCustomer (Customer customer) throws SQLException;
    public List<Customer> getCustomers();
    public List<Customer> getCustomerById (String lastName);
    public boolean updateCustomer(int customerID, String cust_first_name, String cust_last_name, int credit_limit, String cust_email,
                                  int accountManagerId, Date dateOfBirth, String maritalStatus, String gender, String incomeLevel) throws SQLException;
    public boolean deleteCustomer(int customerID);


}
