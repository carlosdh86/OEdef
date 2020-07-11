package com.cice.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class ConnectionView {

    private static final Logger log = LoggerFactory.getLogger(ConnectionView.class);

    public static String user = null;
    public static String pass = null;

    public static void getCredentials () {

        log.info("Sesión no iniciada. Se te pedirán tus credenciales para iniciar sesión en la Base de Datos Oracle.");
        log.info("Introduce tu usuario de la Base de Datos Oracle");
        Scanner sc = new Scanner(System.in);
        user = sc.next();

        log.info("Introduce tu contraseña de la Base de Datos Oracle");
        Scanner sc2 = new Scanner(System.in);
        pass = sc2.next();
    }


    public static String getUser() {
        return user;
    }

    public static String getpass() {
        return pass;
    }

}
