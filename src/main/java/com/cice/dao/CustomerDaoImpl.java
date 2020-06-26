package com.cice.dao;
import com.cice.connection.MyConnection;
import com.cice.idao.ICustomerDao;
import com.cice.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class CustomerDaoImpl implements ICustomerDao {

    public static Connection myconnection = MyConnection.getMyConnection();
    public static PreparedStatement stm = null;
    public static ResultSet rs = null;


    public boolean createCustomer(Customer customer) throws SQLException{

        boolean isCreated = false;

        try {
            stm = myconnection.prepareStatement("INSERT INTO CUSTOMERS (CUSTOMER_ID , CUST_FIRST_NAME , CUST_LAST_NAME) VALUES (?,?,?)");
            stm.setInt(1, customer.getCustomer_id());
            stm.setString(2, customer.getCust_first_name());
            stm.setString(3, customer.getCust_last_name());

            stm.execute();

            isCreated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCreated;
    }


    public List<Customer> getCustomers() {

        List<Customer> customerList = new ArrayList<Customer>();

        try {
            stm = myconnection.prepareStatement("SELECT * FROM CUSTOMERS ORDER BY 1");
            rs = stm.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt(1));
                customer.setCust_first_name(rs.getString(2));
                customer.setCust_last_name(rs.getString(3));
                customer.setNls_language(rs.getString(4));
                customer.setNls_territory(rs.getString(5));
                customer.setCredit_limit(rs.getInt(6));
                customer.setCust_email(rs.getString(7));
                customer.setAccount_mgr_id(rs.getInt(8));
                customer.setDate_of_bird(rs.getDate(9));
                customer.setMarital_status(rs.getString(10));
                customer.setGender(rs.getString(11));
                customer.setIncome_level(rs.getString(12));
                System.out.println(customer);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    public List<Customer> getCustomerById(String lastName) {

        List<Customer> customerList = new ArrayList<Customer>();

        try {
            stm = myconnection.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUST_LAST_NAME=?");
            stm.setString(1, lastName);
            rs = stm.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt(1));
                customer.setCust_first_name(rs.getString(2));
                customer.setCust_last_name(rs.getString(3));
                customer.setNls_language(rs.getString(4));
                customer.setNls_territory(rs.getString(5));
                customer.setCredit_limit(rs.getInt(6));
                customer.setCust_email(rs.getString(7));
                customer.setAccount_mgr_id(rs.getInt(8));
                customer.setDate_of_bird(rs.getDate(9));
                customer.setMarital_status(rs.getString(10));
                customer.setGender(rs.getString(11));
                customer.setIncome_level(rs.getString(12));
                System.out.println(customer);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    public boolean updateCustomer(Customer customer) {
        return false;
    }

    public boolean deleteCustomer(Customer customer) {

        return false;
    }
}
