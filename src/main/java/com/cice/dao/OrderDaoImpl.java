package com.cice.dao;

import com.cice.connection.MyConnection;
import com.cice.idao.IOrderDao;
import com.cice.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements IOrderDao {

    public static Connection myconnection = MyConnection.getMyConnection();
    public static PreparedStatement stm = null;
    public static PreparedStatement stm2 = null;
    public static PreparedStatement stm3 = null;
    public static PreparedStatement stm4 = null;
    public static PreparedStatement stm5 = null;
    public static ResultSet rs2 = null;
    public static ResultSet rs4 = null;


    public boolean createOrder(Order order, boolean isStarted) throws SQLException {
        boolean isCreated = false;

        while (!isStarted) {

            try {

                stm = myconnection.prepareStatement("INSERT INTO ORDERS (ORDER_ID, ORDER_DATE , ORDER_MODE , CUSTOMER_ID , ORDER_STATUS, SALES_REP_ID)" +
                        "VALUES (NULL , CURRENT_TIMESTAMP , ? , ? , 0 , ?) ");
                stm.setString(1, order.getOrder_mode());
                stm.setInt(2, order.getCustomer_id());
                stm.setInt(3, order.getSales_rep_id());
                stm.execute();

                stm2 = myconnection.prepareStatement("SELECT ORDER_ID FROM ORDERS WHERE rownum <= 1 ORDER BY ORDER_ID DESC");
                rs2 = stm2.executeQuery();
                if (rs2.next()) {
                    order.setOrder_id(rs2.getInt(1));
                    System.out.println("lA NIÑA BONITA ES: "+order.getOrder_id());
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }
            isStarted = true;
        }

        try {

            stm3 = myconnection.prepareStatement("INSERT INTO ORDER_ITEMS (ORDER_ID,LINE_ITEM_ID,PRODUCT_ID,QUANTITY) VALUES ( ? , ? , ? , ?)");
            stm3.setInt(1, order.getOrder_id());
            stm4= myconnection.prepareStatement("SELECT LINE_ITEM_ID FROM ORDER_ITEMS WHERE ORDER_ID = ? AND rownum <= 1 ORDER BY LINE_ITEM_ID DESC");
            stm4.setInt(1,order.getOrder_id());
            rs4 = stm4.executeQuery();
            if (rs4.next()) {
                int line_item_id=(rs4.getInt(1));
                line_item_id=line_item_id+1;
                System.out.println("lA Nena BONITA ES: " + line_item_id);
                stm3.setInt(2, line_item_id);
                stm3.setInt(2, line_item_id);
                stm3.setInt(3, order.getProduct_id());
                stm3.setInt(4, order.getQuantity());
                stm3.execute();
            }else{
                int line_item_id=1;
                System.out.println("lA Nena BONITA ES: " + line_item_id);
                stm3.setInt(2, line_item_id);
                stm3.setInt(3, order.getProduct_id());
                stm3.setInt(4, order.getQuantity());
                stm3.execute();
            }

            isCreated=true;
            stm5 = myconnection.prepareStatement("UPDATE SET O");



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isCreated;
    }
}
