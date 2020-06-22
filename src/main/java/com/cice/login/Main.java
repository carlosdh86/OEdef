package com.cice.login;

import com.cice.login.datasource.Datasource;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main (String[] args) {
        BasicConfigurator.configure();

        boolean response = false;

        while (!response) {
            log.info("Introduce tu usuario de la BD");
            Scanner sc = new Scanner(System.in);
            String user = sc.next();
            log.info("Introduce tu contraseña de la BD");
            String pass = sc.next();

            try {
                response = Datasource.isLogin(user,pass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(response);
        }

        log.info("Login correcto, ahora elige una acción");




    }
}
