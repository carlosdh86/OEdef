package com.cice.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Datasource {



    private static final Logger log = LoggerFactory.getLogger(Datasource.class);
    private static PreparedStatement statement = null;
    private static Connection connection = null;



    public static boolean isLogin (String user , String pass) throws SQLException, ClassNotFoundException{

        boolean response = false;
        String url = "jdbc:oracle:oci:@localhost:1521:OEDB";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(url, user, pass);
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

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Usuario o contraseña incorrectos");
            return response;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return response;
        }

    }

}
