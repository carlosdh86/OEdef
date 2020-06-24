package com.cice;

import com.cice.connection.MyConnection;
import com.cice.controller.CustomerController;
import com.cice.dao.CustomerDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.view.CustomerView;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static Connection myConnection=null;

    public static Connection getMyConnection() {
        return myConnection;
    }

    public static void main (String[] args) throws SQLException {
        BasicConfigurator.configure();

        while (myConnection==null) {
            log.info("Introduce tu usuario de la BD");
            Scanner sc = new Scanner(System.in);
            String user = sc.next();
            log.info("Introduce tu contraseña de la BD");
            String pass = sc.next();
            try {
                //MyConnection.connect(user,pass);
                myConnection= MyConnection.connect(user,pass);
                if (myConnection==null) {
                    log.error("Usuario o contraseñas incorrectos, se le pedirán de nuevo");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        log.info("Login correcto, ahora elige una acción");


    log.info("Elige una opción: \1:añadir cliente  \2:mostrar todos los clientes");
    Scanner sc2 = new Scanner(System.in);
    int option = sc2.nextInt();
    CustomerController customerController = new CustomerController();
    CustomerView customerView = new CustomerView();

    switch (option) {
        case 1:
            customerView.createCustomer();
            break;

        case 2:
            customerController.getCustomers();
            break;

        default:
           log.error("Option unavalaible");
    }


    }
}
