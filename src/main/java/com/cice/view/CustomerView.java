package com.cice.view;

import com.cice.Main;
import com.cice.controller.ConnectionController;
import com.cice.controller.CustomerController;
import com.cice.dao.CustomerDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerView {

    private static final Logger log = LoggerFactory.getLogger(CustomerView.class);

    public static void chooseAction () throws SQLException {
        log.info("Elige la acción que quieres realizar sobre clientes");
        log.info("-1:Crear cliente");
        log.info("-2:Consultar cliente");
        log.info("-3:Modificar cliente");
        log.info("-4:Borrar cliente");
        log.info("-5:Volver al menú principal");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {

            case 1:
                CustomerView customerView = new CustomerView();
                        try {
                            customerView.createCustomer();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                break;

            case 2:
                        CustomerController customerController = new CustomerController();
                        customerController.getCustomers();
                        break;

            case 5:

                Main.appStart();

                break;
        }

    }

    public void getCustomers (List<Customer> customers) {

        for (Customer customer:customers) {
            System.out.println("Datos del cliente: "+customer);
        }

    }

    public void createCustomer() throws SQLException {

        log.info("Introduce ID del cliente");
        Scanner sc = new Scanner(System.in);
        int customer_id = sc.nextInt();

        log.info("Introduce nombre del cliente");
        Scanner sc2 = new Scanner(System.in);
        String cust_first_name = sc2.next();

        log.info("Introduce apellido del cliente");
        Scanner sc3 = new Scanner(System.in);
        String cust_last_name = sc3.next();

        Customer customer = new Customer();
        customer.setCustomer_id(customer_id);
        customer.setCust_first_name(cust_first_name);
        customer.setCust_last_name(cust_last_name);

        ICustomerDao iCustomerDao = new CustomerDaoImpl();

        boolean isCreated = iCustomerDao.create(customer);
        if (isCreated) {
            log.info("Usuario " +customer_id+ " registrado con éxito");
        }
    }


}
