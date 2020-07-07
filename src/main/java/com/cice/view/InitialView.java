package com.cice.view;

import com.cice.connection.MyConnection;
import com.cice.controller.ConnectionController;
import com.cice.controller.CustomerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.Scanner;

public class InitialView {

    private static final Logger log = LoggerFactory.getLogger(InitialView.class);

    public static void appStart ()  throws SQLException {

        ConnectionController.controlConnection();

        log.info("Bienvenido " + ConnectionView.getUser() + ". Has iniciado sesión con éxito. Elige una opción:");
        log.info("-1:Clientes");
        log.info("-2:Pedidos");
        log.info("-3:Productos");
        log.info("-4:Cerrar sesión");

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                CustomerView customerView = new CustomerView();
                customerView.chooseAction();
                break;

            case 2:
                OrderView orderView = new OrderView();
                orderView.chooseAction();
                break;

            case 3:
                ProductView productView = new ProductView();
                productView.chooseAction();
                break;

            case 4:
                MyConnection.disconnect(MyConnection.getMyConnection());
                InitialView.appStart();
                break;

            default:
                log.error("Opción incorrecta");
                appStart();
        }

    }
}
