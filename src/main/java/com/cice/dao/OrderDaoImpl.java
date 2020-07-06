package com.cice.dao;

import com.cice.connection.MyConnection;
import com.cice.idao.IOrderDao;
import com.cice.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements IOrderDao {

    public static Connection myconnection = MyConnection.getMyConnection();
    public static PreparedStatement stm1 = null;
    public static PreparedStatement stm2 = null;
    public static PreparedStatement stm3 = null;
    public static PreparedStatement stm4 = null;
    public static PreparedStatement stm5 = null;
    public static ResultSet rs1 = null;
    public static ResultSet rs2 = null;
    public static ResultSet rs3 = null;
    public static ResultSet rs4 = null;


    public List<Order> getOrders() throws SQLException {

        List<Order> orderList = new ArrayList<Order>();

        try {
            stm1 = myconnection.prepareStatement("SELECT * FROM ORDERS ORDER BY 1");
            rs1 = stm1.executeQuery();
            while (rs1.next()) {
                Order order = new Order();
                order.setOrder_id(rs1.getInt(1));
                order.setOrder_date(rs1.getDate(2));
                order.setOrder_mode(rs1.getString(3));
                order.setCustomer_id(rs1.getInt(4));
                order.setOrder_status(rs1.getInt(5));
                order.setOrder_total(rs1.getFloat(6));
                order.setSales_rep_id(rs1.getInt(7));
                order.setProduct_id(rs1.getInt(8));
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public Order getOrderById(int orderId) throws SQLException {
        Order order = new Order();

        try {
            stm1 = myconnection.prepareStatement("SELECT * FROM ORDERS WHERE ORDER_ID=?");
            stm1.setInt(1, orderId);
            rs1 = stm1.executeQuery();
            if (rs1.next()) {

                order.setOrder_id(rs1.getInt(1));
                order.setOrder_date(rs1.getDate(2));
                order.setOrder_mode(rs1.getString(3));
                order.setCustomer_id(rs1.getInt(4));
                order.setOrder_status(rs1.getInt(5));
                order.setOrder_total(rs1.getFloat(6));
                order.setSales_rep_id(rs1.getInt(7));
                order.setProduct_id(rs1.getInt(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public int createOrder(Order order, boolean isCreated) throws SQLException {

        try {

            while (!isCreated) {

                stm1 = myconnection.prepareStatement("INSERT INTO ORDERS (ORDER_ID, ORDER_DATE , ORDER_MODE , CUSTOMER_ID , ORDER_STATUS, ORDER_TOTAL, SALES_REP_ID)" +
                        "VALUES (NULL , CURRENT_TIMESTAMP , ? , ? , 0 , 0 , ?) ");
                stm1.setString(1, order.getOrder_mode());
                stm1.setInt(2, order.getCustomer_id());
                stm1.setInt(3, order.getSales_rep_id());
                stm1.execute();

                stm2 = myconnection.prepareStatement("SELECT ORDER_ID FROM ORDERS WHERE rownum <= 1 ORDER BY ORDER_ID DESC");
                rs2 = stm2.executeQuery();
                if (rs2.next()) {
                    order.setOrder_id(rs2.getInt(1));
                }

                isCreated = true;
            }

            if (isCreated) {
                addProductToOrder(order);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return order.getOrder_id();
    }

    public boolean addProductToOrder(Order order) throws SQLException {

        int line_item_id = 1;

        try {
            stm1 = myconnection.prepareStatement("SELECT PRODUCT_ID,QUANTITY FROM ORDER_ITEMS WHERE ORDER_ID=? AND PRODUCT_ID=?");
            stm1.setInt(1, order.getOrder_id());
            stm1.setInt(2, order.getProduct_id());
            rs1 = stm1.executeQuery();
            if (rs1.next()) {
                int oldQuantity = rs1.getInt(2);
                int newQuantity = oldQuantity + order.getQuantity();
                stm2 = myconnection.prepareStatement("UPDATE ORDER_ITEMS SET QUANTITY=? WHERE ORDER_ID=? AND PRODUCT_ID=?");
                stm2.setInt(1, newQuantity);
                stm2.setInt(2, order.getOrder_id());
                stm2.setInt(3, order.getProduct_id());
                stm2.execute();

            } else {
                stm3 = myconnection.prepareStatement("SELECT LIST_PRICE FROM PRODUCT_INFORMATION WHERE PRODUCT_ID=?");
                stm3.setInt(1, order.getProduct_id());
                rs3 = stm3.executeQuery();
                float unitPrice = 0;
                if (rs3.next()) {
                    unitPrice = (rs3.getFloat(1));
                }

                stm4 = myconnection.prepareStatement("SELECT LINE_ITEM_ID FROM ORDER_ITEMS WHERE ORDER_ID = ? AND rownum <= 1 ORDER BY LINE_ITEM_ID DESC");
                stm4.setInt(1, order.getOrder_id());
                rs4 = stm4.executeQuery();
                if (rs4.next()) {
                    line_item_id = (rs4.getInt(1));
                    line_item_id = line_item_id + 1;
                }

                stm5 = myconnection.prepareStatement("INSERT INTO ORDER_ITEMS (ORDER_ID,LINE_ITEM_ID,PRODUCT_ID,UNIT_PRICE,QUANTITY) VALUES ( ? , ? , ? , ? , ?)");
                stm5.setInt(1, order.getOrder_id());
                stm5.setInt(2, line_item_id);
                stm5.setInt(3, order.getProduct_id());
                stm5.setFloat(4, unitPrice);
                stm5.setInt(5, order.getQuantity());
                stm5.execute();
            }

            updateOrderTotal(order.getOrder_id());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public double updateOrderTotal(int orderId) throws SQLException {

        double rowOrderTotal;
        double orderTotal = 0;

        try {
            stm1 = myconnection.prepareStatement("SELECT UNIT_PRICE,QUANTITY FROM ORDER_ITEMS WHERE ORDER_ID=?");
            stm1.setInt(1, orderId);
            rs1 = stm1.executeQuery();

            while (rs1.next()) {
                double unitPrice = rs1.getDouble(1);
                int quantity = rs1.getInt(2);
                rowOrderTotal = unitPrice * quantity;
                orderTotal = orderTotal + rowOrderTotal;
            }

            stm2 = myconnection.prepareStatement("UPDATE ORDERS SET ORDER_TOTAL=? WHERE ORDER_ID=?");
            stm2.setDouble(1, orderTotal);
            stm2.setInt(2, orderId);
            stm2.execute();
            return orderTotal;

        } catch (SQLException e) {
           e.printStackTrace();
        }

        return orderTotal;
    }

    public boolean finishOrder(Order order) throws SQLException {

        try {
            stm1 = myconnection.prepareStatement("UPDATE ORDERS SET ORDER_STATUS = 1 WHERE ORDER_ID=?");
            stm1.setInt(1, order.getOrder_id());
            return stm1.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteOrder(Order order) throws SQLException {

        try {
            stm1 = myconnection.prepareStatement("DELETE FROM ORDER_ITEMS WHERE ORDER_ID=?");
            stm1.setInt(1, order.getOrder_id());
            stm1.execute();

            stm2 = myconnection.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID=?");
            stm2.setInt(1, order.getOrder_id());

            if (stm2.executeUpdate()!=0) {
                return true;
            } else return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Double> getOrderTotal() throws SQLException {
        List<Double> response = new ArrayList<Double>();
        stm1 = myconnection.prepareStatement("SELECT COUNT (ORDER_ID) FROM ORDERS");
        rs1 = stm1.executeQuery();
        if (rs1.next()) {
            double ordersCount = rs1.getDouble(1);
            response.add(ordersCount);
        }

        stm2 = myconnection.prepareStatement("SELECT SUM(ORDER_TOTAL) FROM ORDERS");
        rs2 = stm2.executeQuery();
        if (rs2.next()) {
            double ordersTotal = rs2.getDouble(1);
            response.add(ordersTotal);
        }
        return response;
    }

    public boolean updateOrder(Order order, int field) throws SQLException {

        boolean isUpdated = false;

        try {

            switch (field) {
                case 1:
                    stm1 = myconnection.prepareStatement(" UPDATE ORDERS SET ORDER_MODE=? WHERE ORDER_ID=? ");
                    stm1.setString(1, order.getOrder_mode());
                    stm1.setInt(2, order.getOrder_id());
                    rs1 = stm1.executeQuery();
                    break;

                case 2:
                    stm1 = myconnection.prepareStatement(" UPDATE ORDERS SET CUSTOMER_ID=? WHERE ORDER_ID=? ");
                    stm1.setInt(1, order.getCustomer_id());
                    stm1.setInt(2, order.getOrder_id());
                    rs1 = stm1.executeQuery();
                    break;

                case 3:
                    stm1 = myconnection.prepareStatement(" UPDATE ORDERS SET SALES_REP_ID=? WHERE ORDER_ID=? ");
                    stm1.setInt(1, order.getSales_rep_id());
                    stm1.setInt(2, order.getOrder_id());
                    rs1 = stm1.executeQuery();
                    break;

                case 4:
                    stm1 = myconnection.prepareStatement(" UPDATE ORDERS SET PROMOTION_ID=? WHERE ORDER_ID=? ");
                    stm1.setInt(1, order.getPromotion_id());
                    stm1.setInt(2, order.getOrder_id());
                    rs1 = stm1.executeQuery();
                    break;

                case 5:
                    isUpdated = addProductToOrder(order);
                    break;

                case 6:
                    isUpdated = deleteProductFromOrder(order);
                    break;

            }

            if (rs1 != null) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }


    public boolean deleteProductFromOrder(Order order) throws SQLException {

        try {
            stm1 = myconnection.prepareStatement("DELETE FROM ORDER_ITEMS WHERE ORDER_ID=? AND PRODUCT_ID=? ");
            stm1.setInt(1, order.getOrder_id());
            stm1.setInt(2, order.getProduct_id());
            stm1.execute();
            updateOrderTotal(order.getOrder_id());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}


