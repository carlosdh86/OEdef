package com.cice.idao;

import com.cice.model.Order;
import com.cice.model.Product;
import java.sql.SQLException;
import java.util.List;

public interface IProductDao {

    public List<Product> getAllProducts () throws SQLException;

    public List<Product> getProductsByWarehouse (int warehouseId) throws SQLException;

    public List<Product> getProductsUnder10 () throws SQLException;

}
