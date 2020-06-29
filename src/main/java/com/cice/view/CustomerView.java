package com.cice.view;

import com.cice.controller.CustomerController;
import com.cice.dao.CustomerDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                chooseAction();

            case 2:
                        CustomerController customerController = new CustomerController();
                        log.info("¿Qué clientes quieres consultar?");
                        log.info("1-:Todos");
                        log.info("2-:Filtrar por apellido");
                        Scanner sc2 = new Scanner(System.in);
                        int option2 = sc2.nextInt();
                        switch (option2) {
                            case 1:
                                customerController.getCustomers();
                                chooseAction();
                            case 2:
                                log.info("Introduce el apellido");
                                Scanner sc3 = new Scanner(System.in);
                                String lastName = sc3.next();
                                customerController.getCustomersByLastName(lastName);
                                chooseAction();
                            }

            case 3:
                CustomerView customerView2 = new CustomerView();
                customerView2.updateCustomer();
                chooseAction();

            case 4:
                CustomerView customerView3 = new CustomerView();
                customerView3.deleteCustomer();
                chooseAction();

            case 5:
                InitialView.appStart();
        }

    }

    public void createCustomer() throws SQLException {

        log.info("Introduce nombre del cliente");
        Scanner sc2 = new Scanner(System.in);
        String cust_first_name = sc2.next();

        log.info("Introduce apellido del cliente");
        Scanner sc3 = new Scanner(System.in);
        String cust_last_name = sc3.next();

        Customer customer = new Customer();
        customer.setCust_first_name(cust_first_name);
        customer.setCust_last_name(cust_last_name);

        ICustomerDao iCustomerDao = new CustomerDaoImpl();

        boolean isCreated = iCustomerDao.createCustomer(customer);
        if (isCreated) {
            log.info("Usuario " + cust_first_name + " " + cust_last_name + " registrado con éxito");
        }
    }

    public void deleteCustomer() throws SQLException{

        log.info("Introduce ID del cliente que quieres borrar");
        Scanner sc4 = new Scanner(System.in);
        int customerId= sc4.nextInt();

        ICustomerDao iCustomerDao = new CustomerDaoImpl();
        boolean isDeleted = iCustomerDao.deleteCustomer(customerId);
        if(isDeleted) {
            log.info("Usuario " + customerId + " eliminado con éxito");
        }
    }

    public void updateCustomer() throws SQLException {

        log.info("Introduce ID del cliente que quieres actualizar");
        Scanner sc5 = new Scanner(System.in);
        int customerId = sc5.nextInt();
        ICustomerDao iCustomerDao = new CustomerDaoImpl();
        log.info("Introduce los nuevos datos del cliente. Deja en blanco los datos que no quieras actualizar");
        log.info("Introduce el nombre");
        Scanner sc6 = new Scanner(System.in);
        String customerFirstName = sc6.next();
        log.info("Introduce el apellido");
        Scanner sc7 = new Scanner(System.in);
        String customerLastName = sc7.next();
        log.info("Introduce el límite de crédito ");
        Scanner sc8 = new Scanner(System.in);
        int creditLimit = sc8.nextInt();
        log.info("Introduce el email");
        Scanner sc9 = new Scanner(System.in);
        String email = sc9.next();
        log.info("Introduce ID del manager");
        Scanner sc10 = new Scanner(System.in);
        int managerID = sc10.nextInt();
        log.info("Introduce fecha de nacimiento en formato dd/mm/aaaa");
        Scanner sc11 = new Scanner(System.in);
        String birthDayString = sc11.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date JavaBirthDay = null;
        try {
            JavaBirthDay = sdf.parse(birthDayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date SqlBirthDay = convert(JavaBirthDay);
        log.info("Introduce estado civil");
        Scanner sc12 = new Scanner(System.in);
        String maritalStatus = sc12.next();
        log.info("Introduce sexo");
        Scanner sc13 = new Scanner(System.in);
        String gender = sc13.next();
        log.info("Introduce nivel de ingresos");
        Scanner sc14 = new Scanner(System.in);
        String incomeLevel = sc14.next();

        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        boolean isUpdated = customerDao.updateCustomer(customerId,customerFirstName,customerLastName,creditLimit,email,
                managerID,SqlBirthDay,maritalStatus,gender,incomeLevel);

        if(isUpdated) {
            log.info("Usuario " + customerId + " modificado con éxito");
        }



    }

    private static java.sql.Date convert(java.util.Date JavaDate) {
        java.sql.Date SqlDate = new java.sql.Date(JavaDate.getTime());
        return SqlDate;
    }



}
