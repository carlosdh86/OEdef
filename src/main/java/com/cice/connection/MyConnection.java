package com.cice.connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private static final Logger log = LoggerFactory.getLogger(MyConnection.class);
    private static java.sql.Connection myConnection = null;

    public static Connection connect (String user, String pass) throws SQLException, ClassNotFoundException {

    String url = "jdbc:oracle:oci:@localhost:1521:OEDB";

        try {
        Class.forName("oracle.jdbc.OracleDriver");
        myConnection = DriverManager.getConnection(url, user, pass);
            System.out.println(myConnection);
            System.out.println("hola");


         } catch (
            SQLException e) {
            e.printStackTrace();


         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }

        return myConnection;

    }

    /*public static boolean isLogin (String user String pass) throws SQLException, ClassNotFoundException{

        boolean response = false;

            if (connection != null) {
                log.info("Has hecho login");
                response=true;
                System.out.println(response);
                return response;
            } else {
                log.info("Usuario o contraseña incorrectos");
                response=false;
                return response;
            }

        } */


}
