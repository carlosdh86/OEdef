package com.cice.controller;

import com.cice.connection.MyConnection;
import com.cice.view.ConnectionView;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionController {

    public static Connection myConnection = null;

    public static void controlConnection() {

        myConnection=MyConnection.getMyConnection();

        while (myConnection == null) {
            ConnectionView.getCredentials();
            try {
                myConnection = MyConnection.connect(ConnectionView.getUser(), ConnectionView.getpass());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}




