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
    public static PreparedStatement stm6 = null;
    public static PreparedStatement stm7 = null;
    public static PreparedStatement stm8 = null;
    public static PreparedStatement stm9 = null;
    public static PreparedStatement stm10 = null;
    public static ResultSet rs2 = null;
    public static ResultSet rs4 = null;
    public static ResultSet rs5 = null;
    public static ResultSet rs6 = null;
    public static ResultSet rs7 = null;

    public int createOrder(Order order, boolean isCreated) throws SQLException {

        float productPrice=0;
        float orderTotalBefore=0;
        float orderTotalAfter=0;
        int line_item_id=1;

        while (!isCreated) {

            try {

                stm = myconnection.prepareStatement("INSERT INTO ORDERS (ORDER_ID, ORDER_DATE , ORDER_MODE , CUSTOMER_ID , ORDER_STATUS, ORDER_TOTAL, SALES_REP_ID)" +
                        "VALUES (NULL , CURRENT_TIMESTAMP , ? , ? , 0 , 0 , ?) ");
                stm.setString(1, order.getOrder_mode());
                stm.setInt(2, order.getCustomer_id());
                stm.setInt(3, order.getSales_rep_id());
                stm.execute();

                stm2 = myconnection.prepareStatement("SELECT ORDER_ID FROM ORDERS WHERE rownum <= 1 ORDER BY ORDER_ID DESC");
                rs2 = stm2.executeQuery();
                if (rs2.next()) {
                    order.setOrder_id(rs2.getInt(1));
                }

                isCreated = true;

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            stm5 = myconnection.prepareStatement("SELECT LIST_PRICE FROM PRODUCT_INFORMATION WHERE PRODUCT_ID=?");
            stm5.setInt(1,order.getProduct_id());
            rs5 = stm5.executeQuery();

            if (rs5.next()) {
                productPrice=(rs5.getFloat(1));
                System.out.println("El precio del producto es: " + productPrice + " €");
            }

            if(isCreated) {
                stm4 = myconnection.prepareStatement("SELECT LINE_ITEM_ID FROM ORDER_ITEMS WHERE ORDER_ID = ? AND rownum <= 1 ORDER BY LINE_ITEM_ID DESC");
                stm4.setInt(1, order.getOrder_id());
                rs4 = stm4.executeQuery();
                if (rs4.next()) {
                    line_item_id = (rs4.getInt(1));
                    line_item_id = line_item_id + 1;
                }
            }

            stm3 = myconnection.prepareStatement("INSERT INTO ORDER_ITEMS (ORDER_ID,LINE_ITEM_ID,PRODUCT_ID,UNIT_PRICE,QUANTITY) VALUES ( ? , ? , ? , ? , ?)");
            stm3.setInt(1, order.getOrder_id());
            stm3.setInt(2, line_item_id);
            stm3.setInt(3, order.getProduct_id());
            stm3.setFloat(4, productPrice);
            stm3.setInt(5, order.getQuantity());
            stm3.execute();

            stm6 = myconnection.prepareStatement("SELECT ORDER_TOTAL FROM ORDERS WHERE ORDER_ID=?");
            stm6.setInt(1, order.getOrder_id());
            rs6 = stm6.executeQuery();
            if (rs6.next()) {
                orderTotalBefore = (rs6.getFloat(1));
                orderTotalAfter = orderTotalBefore + productPrice * order.getQuantity();
            }

            stm7 = myconnection.prepareStatement("UPDATE ORDERS SET ORDER_TOTAL=? WHERE ORDER_ID=?");
            stm7.setFloat(1, orderTotalAfter);
            stm7.setInt(2, order.getOrder_id());
            rs7 = stm7.executeQuery();
            if (rs7.next()) {
                orderTotalBefore = (rs6.getFloat(1));
                orderTotalAfter = orderTotalBefore + productPrice * order.getQuantity();
                System.out.println("Lo que llevo ahora de pedido es: " + orderTotalAfter + " €");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order.getOrder_id();
    }

    public boolean finishOrder(Order order) throws SQLException {

        stm8 = myconnection.prepareStatement("UPDATE ORDERS SET ORDER_STATUS = 1 WHERE ORDER_ID=?");
        stm8.setInt(1,order.getOrder_id());
        return stm8.execute();

    }

    public boolean deleteOrder(Order order) throws SQLException {

            stm9 = myconnection.prepareStatement("DELETE FROM ORDER_ITEMS WHERE ORDER_ID=?");
            stm9.setInt(1,order.getOrder_id());
            stm9.execute();

            stm10 = myconnection.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID=?");
            stm10.setInt(1,order.getOrder_id());
            if (stm10.executeUpdate()!=0) {
                return true;
            } else return false;
    }
}


