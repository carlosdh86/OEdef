package com.cice;

import com.cice.connection.MyConnection;
import com.cice.controller.ConnectionController;
import com.cice.controller.CustomerController;
import com.cice.dao.CustomerDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.view.ConnectionView;
import com.cice.view.CustomerView;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws SQLException {

        BasicConfigurator.configure();
        Main.appStart();

    }

    public static void appStart ()  throws SQLException {

        ConnectionController.control();

        log.info("Bienvenido " + ConnectionView.getUser() + ". Has iniciado sesión con éxito. Ahora elige los datos que quieres modificar:");
        log.info("-1:Clientes");
        log.info("-2:Pedidos");
        log.info("-3:Productos");
        log.info("-4:Cerrar sesión");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        CustomerController customerController = new CustomerController();
        CustomerView customerView = new CustomerView();

        switch (option) {
            case 1:
                customerView.chooseAction();
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:

                MyConnection.disconnect(MyConnection.getMyConnection());
                Main.appStart();
                break;

            default:
                log.error("Option unavalaible");
        }

    }

}
