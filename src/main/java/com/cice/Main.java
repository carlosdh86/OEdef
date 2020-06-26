package com.cice;
import com.cice.view.InitialView;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        BasicConfigurator.configure();
        InitialView.appStart();

    }

}
