package com.cice.controller;

import com.cice.dao.ProductDaoImpl;
import com.cice.idao.IProductDao;
import com.cice.model.Product;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductContoller {

    private static final Logger log = LoggerFactory.getLogger(ProductContoller.class);

    public void getProductsByWarehouse(int warehouseId) throws SQLException {
        IProductDao iProductDao = new ProductDaoImpl();
        List<Product> products = iProductDao.getProductsByWarehouse(warehouseId);
        if (products.size() == 0) {
            log.warn("No existe ningún producto en este almacen");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void getProductsUnder10() throws SQLException {
        IProductDao iProductDao = new ProductDaoImpl();
        List<Product> products = iProductDao.getProductsUnder10();
        if (products.size() == 0) {
            log.warn("No existe ningún producto con menos de 10 existencias");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void getAllProducts(int page) throws SQLException {
        IProductDao iProductDao = new ProductDaoImpl();
        List<Product> products = iProductDao.getAllProducts();
        if (getPage(products,page,5).size()==0)  {
            log.warn("No existen más productos registrados");
        } else {
            List<Product> productsPage= getPage(products, page, 5 );
            for (Product product:productsPage) {
                System.out.println(product);
            }

        }
    }

    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() < fromIndex){
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }


}



