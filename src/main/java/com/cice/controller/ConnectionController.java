package com.cice.controller;

import com.cice.connection.MyConnection;
import com.cice.view.ConnectionView;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSOutput;


import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionController {

    private static final Logger log = LoggerFactory.getLogger(ConnectionController.class);
    public static Connection myConnection = null;

    public static void control () {

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




