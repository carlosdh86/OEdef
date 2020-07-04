package com.cice.dao;

import com.cice.connection.MyConnection;
import com.cice.idao.IOrderDao;
import com.cice.model.Customer;
import com.cice.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static PreparedStatement stm11 = null;
    public static PreparedStatement stm12 = null;
    public static PreparedStatement stm13 = null;
    public static PreparedStatement stm14 = null;
    public static PreparedStatement stm15 = null;
    public static ResultSet rs2 = null;
    public static ResultSet rs4 = null;
    public static ResultSet rs5 = null;
    public static ResultSet rs6 = null;
    public static ResultSet rs7 = null;
    public static ResultSet rs11 = null;
    public static ResultSet rs12 = null;
    public static ResultSet rs13 = null;
    public static ResultSet rs14 = null;
    public static ResultSet rs15 = null;
    public static ResultSet rs16 = null;

    public List<Order> getOrders() throws SQLException {
        List<Order> orderList = new ArrayList<Order>();

        try {
            stm11 = myconnection.prepareStatement("SELECT * FROM ORDERS ORDER BY 1");
            rs11 = stm11.executeQuery();
            while (rs11.next()) {
                Order order = new Order();
                order.setOrder_id(rs11.getInt(1));
                order.setOrder_date(rs11.getDate(2));
                order.setOrder_mode(rs11.getString(3));
                order.setCustomer_id(rs11.getInt(4));
                order.setOrder_status(rs11.getInt(5));
                order.setOrder_total(rs11.getFloat(6));
                order.setSales_rep_id(rs11.getInt(7));
                order.setProduct_id(rs11.getInt(8));

                System.out.println(order);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public List<Order> getOrderById(int orderId) throws SQLException {
        List<Order> orderList = new ArrayList<Order>();

        try {
            stm12 = myconnection.prepareStatement("SELECT * FROM ORDERS WHERE ORDER_ID=?");
            stm12.setInt(1, orderId);
            rs12 = stm12.executeQuery();
            while (rs12.next()) {
                Order order = new Order();
                order.setOrder_id(rs12.getInt(1));
                order.setOrder_date(rs12.getDate(2));
                order.setOrder_mode(rs12.getString(3));
                order.setCustomer_id(rs12.getInt(4));
                order.setOrder_status(rs12.getInt(5));
                order.setOrder_total(rs12.getFloat(6));
                order.setSales_rep_id(rs12.getInt(7));
                order.setProduct_id(rs12.getInt(8));

                System.out.println(order);
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public int createOrder(Order order, boolean isCreated) throws SQLException {

        float unitPrice=0;
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
                unitPrice=(rs5.getFloat(1));
                System.out.println("El precio del producto es: " + unitPrice + " €");
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
            stm3.setFloat(4, unitPrice);
            stm3.setInt(5, order.getQuantity());
            stm3.execute();

            stm6 = myconnection.prepareStatement("SELECT ORDER_TOTAL FROM ORDERS WHERE ORDER_ID=?");
            stm6.setInt(1, order.getOrder_id());
            rs6 = stm6.executeQuery();
            if (rs6.next()) {
                orderTotalBefore = (rs6.getFloat(1));
                orderTotalAfter = orderTotalBefore + unitPrice * order.getQuantity();
            }

            stm7 = myconnection.prepareStatement("UPDATE ORDERS SET ORDER_TOTAL=? WHERE ORDER_ID=?");
            stm7.setFloat(1, orderTotalAfter);
            stm7.setInt(2, order.getOrder_id());
            rs7 = stm7.executeQuery();
            if (rs7.next()) {
                orderTotalBefore = (rs6.getFloat(1));
                orderTotalAfter = orderTotalBefore + unitPrice * order.getQuantity();
                System.out.println("Lo que llevo ahora de pedido es: " + orderTotalAfter + " €");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order.getOrder_id();
    }


    public void updateOrderTotal(int orderId) throws SQLException {


        stm15 = myconnection.prepareStatement("SELECT UNIT_PRICE , QUANTITY FROM ORDER_ITEMS WHERE ORDER_ID=?");
        stm15.setInt(1, orderId);
        rs15 = stm5.executeQuery();
        while (rs15.next()) {
            double unitPrice = rs15.getDouble(1);
            int quantity = rs15.getInt(2);
            double orderTotalBefore = unitPrice * quantity;
            double orderTotalAfter = orderTotalBefore + unitPrice * quantity;
        }


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

    public List<Double> getOrderTotal() throws SQLException {
        List <Double> response =  new ArrayList<Double>();
        stm13 = myconnection.prepareStatement("SELECT COUNT (ORDER_ID) FROM ORDERS");
        rs13 = stm13.executeQuery();
        if (rs13.next()) {
           double ordersCount = rs13.getDouble(1);
           response.add(ordersCount);
        }

        stm14 = myconnection.prepareStatement("SELECT SUM(ORDER_TOTAL) FROM ORDERS");
        rs14 = stm14.executeQuery();
        if (rs14.next()) {
            double ordersTotal = rs14.getDouble(1);
            response.add(ordersTotal);
        }
        return response;
    }

    public boolean updateOrder(Order order, int field) throws SQLException {

        boolean isUpdated=false;

        try {

            switch (field) {
                case 1:
                    stm = myconnection.prepareStatement(" UPDATE ORDERS SET ORDER_MODE=? WHERE ORDER_ID=? ");
                    stm.setString(1, order.getOrder_mode());
                    stm.setInt(2, order.getOrder_id());
                    break;

                case 2:
                    stm = myconnection.prepareStatement(" UPDATE ORDERS SET CUSTOMER_ID=? WHERE ORDER_ID=? ");
                    stm.setInt(1, order.getCustomer_id());
                    stm.setInt(2, order.getOrder_id());
                    break;

                case 3:
                    stm = myconnection.prepareStatement(" UPDATE ORDERS SET SALES_REP_ID=? WHERE ORDER_ID=? ");
                    stm.setInt(1, order.getSales_rep_id());
                    stm.setInt(2, order.getOrder_id());
                    break;

                case 4:
                    stm = myconnection.prepareStatement(" UPDATE ORDERS SET PROMOTION_ID=? WHERE ORDER_ID=? ");
                    stm.setInt(1, order.getPromotion_id());
                    stm.setInt(2, order.getOrder_id());
                    break;

                case 5:

                    /*stm = myconnection.prepareStatement("INSERT INTO ORDER_ITEMS (ORDER_ID,LINE_ITEM_ID,PRODUCT_ID,UNIT_PRICE,QUANTITY) VALUES ( ? , ? , ? , ? , ?)");
                    stm.setInt(1, order.getOrder_id());
                    stm.setInt(2, line_item_id);
                    stm.setInt(3, order.getProduct_id());
                    stm.setFloat(4, unitPrice);
                    stm.setInt(5, order.getQuantity());
                    stm.execute();*/

                    break;

                case 6:

                    break;
            }



            rs16 = stm.executeQuery();
            if(rs16!=null) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;

    }
}


