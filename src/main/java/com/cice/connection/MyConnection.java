package com.cice.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private static java.sql.Connection myConnection = null;

    public static Connection connect (String user, String pass) throws SQLException, ClassNotFoundException {

    String url = "jdbc:oracle:oci:@localhost:1521:OEDB";

        try {
        Class.forName("oracle.jdbc.OracleDriver");
        myConnection = DriverManager.getConnection(url, user, pass);
         } catch (
            SQLException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }

        return myConnection;
    }

    public static void disconnect (Connection myConnection) {

        try {
            myConnection.close();
            setMyConnection(null);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getMyConnection() {

        return myConnection;
    }

    public static void setMyConnection(Connection myConnection) {

        MyConnection.myConnection =myConnection;
    }
}
