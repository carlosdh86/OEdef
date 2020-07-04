package com.cice.view;
import com.cice.controller.ProductContoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductView {

    private static final Logger log = LoggerFactory.getLogger(ProductView.class);

    public static void chooseAction () throws SQLException {
        log.info("Elige la acción que quieres realizar sobre Productos");
        log.info("-1:Ver todos los productos");
        log.info("-2:Ver los productos a partir de la selección de almacenes");
        log.info("-3:Productos con stock inferior a 10 unidades en cualquiera de los almacenes");
        log.info("-4:Volver al menú principal");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {

            case 1:
                ProductContoller productContoller = new ProductContoller();
                int page = 1;
                productContoller.getAllProducts(page);

                while (true) {

                    log.info("-1:Página siguiente");
                    log.info("-2:Página anterior");
                    log.info("-3:Volver a menú de productos");
                    Scanner sc2 = new Scanner(System.in);
                    int action = sc2.nextInt();
                    switch (action) {

                        case 1:
                            page++;
                            productContoller.getAllProducts(page);
                            break;

                        case 2:
                            page--;
                            productContoller.getAllProducts(page);
                            break;

                        case 3:
                            chooseAction();
                    }
                }


            case 2:
                log.info("Introduce el ID del almacén");
                Scanner sc3 = new Scanner(System.in);
                int warehouseId = sc3.nextInt();
                ProductContoller productContoller2 = new ProductContoller();
                productContoller2.getProductsByWarehouse(warehouseId);
                chooseAction();

            case 3:
                ProductContoller productContoller3 = new ProductContoller();
                productContoller3.getProductsUnder10();
                chooseAction();

            case 4:
                InitialView.appStart();


        }

    }
}
