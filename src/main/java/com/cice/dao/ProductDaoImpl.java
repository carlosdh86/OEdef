package com.cice.dao;

import com.cice.connection.MyConnection;
import com.cice.idao.IProductDao;
import com.cice.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {

    public static Connection myconnection = MyConnection.getMyConnection();
    public static PreparedStatement stm = null;
    public static ResultSet rs = null;

    public List<Product> getAllProducts() throws SQLException {

        List<Product> productList = new ArrayList<Product>();

        try {
            stm = myconnection.prepareStatement("SELECT PRODUCT_INFORMATION.PRODUCT_ID,PRODUCT_INFORMATION.PRODUCT_NAME," +
                    "PRODUCT_INFORMATION.PRODUCT_DESCRIPTION,PRODUCT_INFORMATION.CATEGORY_ID,PRODUCT_INFORMATION.WEIGHT_CLASS," +
                    "PRODUCT_INFORMATION.WARRANTY_PERIOD, + PRODUCT_INFORMATION.SUPPLIER_ID,PRODUCT_INFORMATION.PRODUCT_STATUS," +
                    "PRODUCT_INFORMATION.LIST_PRICE,PRODUCT_INFORMATION.CATALOG_URL,INVENTORIES.WAREHOUSE_ID,INVENTORIES.QUANTITY_ON_HAND " +
                    "FROM PRODUCT_INFORMATION " +
                    "INNER JOIN INVENTORIES ON PRODUCT_INFORMATION.PRODUCT_ID = INVENTORIES.PRODUCT_ID");

            rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductDescription(rs.getString(3));
                product.setCategoryId(rs.getInt(4));
                product.setWeightClass(rs.getInt(5));
                product.setWarrantyPeriod(rs.getString(6));
                product.setSupplierId(rs.getInt(7));
                product.setProductStatus(rs.getString(8));
                product.setListPrice(rs.getDouble(9));
                product.setCatalogUrl(rs.getString(10));
                product.setWarehouseId(rs.getInt(11));
                product.setQuantity(rs.getInt(12));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<Product> getProductsByWarehouse(int warehouseId) {

        List<Product> productList = new ArrayList<Product>();

        try {
            stm = myconnection.prepareStatement("SELECT PRODUCT_INFORMATION.PRODUCT_ID,PRODUCT_INFORMATION.PRODUCT_NAME," +
                    "PRODUCT_INFORMATION.PRODUCT_DESCRIPTION,PRODUCT_INFORMATION.CATEGORY_ID,PRODUCT_INFORMATION.WEIGHT_CLASS," +
                    "PRODUCT_INFORMATION.WARRANTY_PERIOD, + PRODUCT_INFORMATION.SUPPLIER_ID,PRODUCT_INFORMATION.PRODUCT_STATUS," +
                    "PRODUCT_INFORMATION.LIST_PRICE,PRODUCT_INFORMATION.CATALOG_URL,INVENTORIES.WAREHOUSE_ID,INVENTORIES.QUANTITY_ON_HAND " +
                    "FROM PRODUCT_INFORMATION " +
                    "INNER JOIN INVENTORIES ON PRODUCT_INFORMATION.PRODUCT_ID = INVENTORIES.PRODUCT_ID WHERE INVENTORIES.WAREHOUSE_ID=?");

            stm.setInt(1, warehouseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductDescription(rs.getString(3));
                product.setCategoryId(rs.getInt(4));
                product.setWeightClass(rs.getInt(5));
                product.setWarrantyPeriod(rs.getString(6));
                product.setSupplierId(rs.getInt(7));
                product.setProductStatus(rs.getString(8));
                product.setListPrice(rs.getDouble(9));
                product.setCatalogUrl(rs.getString(10));
                product.setWarehouseId(rs.getInt(11));
                product.setQuantity(rs.getInt(12));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<Product> getProductsUnder10() throws SQLException {

        List<Product> productList = new ArrayList<Product>();

        stm = myconnection.prepareStatement("SELECT PRODUCT_INFORMATION.PRODUCT_ID,PRODUCT_INFORMATION.PRODUCT_NAME," +
                "PRODUCT_INFORMATION.PRODUCT_DESCRIPTION,PRODUCT_INFORMATION.CATEGORY_ID,PRODUCT_INFORMATION.WEIGHT_CLASS," +
                "PRODUCT_INFORMATION.WARRANTY_PERIOD, + PRODUCT_INFORMATION.SUPPLIER_ID,PRODUCT_INFORMATION.PRODUCT_STATUS," +
                "PRODUCT_INFORMATION.LIST_PRICE,PRODUCT_INFORMATION.CATALOG_URL,INVENTORIES.WAREHOUSE_ID,INVENTORIES.QUANTITY_ON_HAND " +
                "FROM PRODUCT_INFORMATION " +
                "INNER JOIN INVENTORIES ON PRODUCT_INFORMATION.PRODUCT_ID = INVENTORIES.PRODUCT_ID WHERE INVENTORIES.QUANTITY_ON_HAND<10");

        rs = stm.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt(1));
            product.setProductName(rs.getString(2));
            product.setProductDescription(rs.getString(3));
            product.setCategoryId(rs.getInt(4));
            product.setWeightClass(rs.getInt(5));
            product.setWarrantyPeriod(rs.getString(6));
            product.setSupplierId(rs.getInt(7));
            product.setProductStatus(rs.getString(8));
            product.setListPrice(rs.getDouble(9));
            product.setCatalogUrl(rs.getString(10));
            product.setWarehouseId(rs.getInt(11));
            product.setQuantity(rs.getInt(12));

            productList.add(product);
        }

        return productList;

    }

}

