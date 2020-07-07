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
            stm = myconnection.prepareStatement("INSERT INTO CUSTOMERS (CUSTOMER_ID,CUST_FIRST_NAME,CUST_LAST_NAME,CUST_EMAIL) VALUES (NULL,?,?,?)");
            stm.setString(1, customer.getCust_first_name());
            stm.setString(2, customer.getCust_last_name());
            stm.setString(3, customer.getCust_email());
            if (stm.executeUpdate()!=0) {
                isCreated = true;
                return isCreated;
            } else return isCreated;

        } catch (SQLException e) {
            e.printStackTrace();
            return isCreated;
        }
    }

    public List<Customer> getCustomers() throws SQLException {

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
                customer.setDate_of_birth(rs.getDate(9));
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

    public List<Customer> getCustomerById(String lastName) throws SQLException {

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
                customer.setDate_of_birth(rs.getDate(9));
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

    public boolean updateCustomer(Customer customer, int field) throws SQLException {

        boolean isUpdated=false;

        try {

            switch (field) {
                case 1:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET CUST_FIRST_NAME=? WHERE CUSTOMER_ID=? ");
                    stm.setString(1, customer.getCust_first_name());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 2:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET CUST_LAST_NAME=? WHERE CUSTOMER_ID=? ");
                    stm.setString(1, customer.getCust_last_name());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 3:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET CREDIT_LIMIT=? WHERE CUSTOMER_ID=? ");
                    stm.setInt(1, customer.getCredit_limit());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 4:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET CUST_EMAIL=? WHERE CUSTOMER_ID=? ");
                    stm.setString(1, customer.getCust_email());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 5:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET ACCOUNT_MGR_ID=? WHERE CUSTOMER_ID=? ");
                    stm.setInt(1, customer.getAccount_mgr_id());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 6:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET DATE_OF_BIRTH=? WHERE CUSTOMER_ID=? ");
                    java.sql.Date sqlBirthday = convertJavaDateToSqlDate(customer.getDate_of_birth());
                    stm.setDate(1, sqlBirthday);
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 7:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET MARITAL_STATUS=? WHERE CUSTOMER_ID=? ");
                    stm.setString(1, customer.getMarital_status());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 8:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET GENDER=? WHERE CUSTOMER_ID=? ");
                    stm.setString(1, customer.getGender());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

                case 9:
                    stm = myconnection.prepareStatement(" UPDATE CUSTOMERS SET INCOME_LEVEL=? WHERE CUSTOMER_ID=? ");
                    stm.setString(1, customer.getIncome_level());
                    stm.setInt(2, customer.getCustomer_id());
                    break;

            }

            rs = stm.executeQuery();
            if(rs!=null) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

    public boolean deleteCustomer(int customerID) {

        try {
            stm = myconnection.prepareStatement("DELETE FROM CUSTOMERS WHERE CUSTOMER_ID=?");
            stm.setInt(1, customerID);

            if (stm.executeUpdate()!=0) {
                return true;
            } else return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

}
