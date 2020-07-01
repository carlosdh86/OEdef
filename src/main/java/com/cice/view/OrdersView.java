package com.cice.view;

import com.cice.controller.CustomerController;
import com.cice.dao.CustomerDaoImpl;
import com.cice.dao.OrderDaoImpl;
import com.cice.idao.ICustomerDao;
import com.cice.idao.IOrderDao;
import com.cice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class OrdersView {

    private static final Logger log = LoggerFactory.getLogger(OrdersView.class);

    public static void chooseAction () throws SQLException {
        log.info("Elige la acción que quieres realizar sobre pedidos");
        log.info("-1:Realizar pedido");
        log.info("-2:Consultar pedido");
        log.info("-3:Modificar pedido");
        log.info("-4:Borrar pedido");
        log.info("-5:Volver al menú principal");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {

            case 1:
                OrdersView ordersView = new OrdersView();
                try {
                    ordersView.createOrder();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                chooseAction();

            case 2:
                CustomerController customerController = new CustomerController();
                log.info("¿Qué pedidos quieres consultar?");
                log.info("1-:Todos");
                log.info("2-:Filtrar por apellido");
                Scanner sc2 = new Scanner(System.in);
                int option2 = sc2.nextInt();
                switch (option2) {
                    case 1:
                        customerController.getCustomers();
                        chooseAction();
                    case 2:
                        log.info("Introduce el apellido");
                        Scanner sc3 = new Scanner(System.in);
                        String lastName = sc3.next();
                        customerController.getCustomersByLastName(lastName);
                        chooseAction();
                }

            case 3:
                CustomerView customerView2 = new CustomerView();
                customerView2.updateCustomer();
                chooseAction();

            case 4:
                CustomerView customerView3 = new CustomerView();
                customerView3.deleteCustomer();
                chooseAction();

            case 5:
                InitialView.appStart();
        }

    }

    public void createOrder() throws SQLException {

        Order order = new Order();

        log.info("Introduce modo del pedido (En tienda, por teléfono o por email)");
        Scanner sc2 = new Scanner(System.in);
        String order_mode= sc2.next();
        order.setOrder_mode(order_mode);

        log.info("Introduce ID del cliente");
        Scanner sc3 = new Scanner(System.in);
        int customer_id = sc3.nextInt();
        order.setCustomer_id(customer_id);

        log.info("Introduce tu ID de empleado");
        Scanner sc4 = new Scanner(System.in);
        int sales_rep_id = sc4.nextInt();
        order.setSales_rep_id(sales_rep_id);

        boolean isStarted=false;
        boolean isFinished=false;

        while(!isFinished) {
            log.info("Introduce ID del producto");
            Scanner sc5 = new Scanner(System.in);
            order.setProduct_id(sc5.nextInt());
            log.info("Introduce la cantidad de producto");
            Scanner sc6 = new Scanner(System.in);
            order.setQuantity(sc6.nextInt());

            IOrderDao iOrderDao = new OrderDaoImpl();
            ICustomerDao iCustomerDao = new CustomerDaoImpl();
            boolean isCreated = iOrderDao.createOrder(order,isStarted);
            isStarted=true;
            if (isCreated) {
                log.info("Producto añadido al pedido con éxito");

                log.info("¿Has acabado ya este pedido?");
                log.info("1- No, quiero añadir más productos");
                log.info("2- Sí, quiero finalizar este pedido");

            Scanner sc7 = new Scanner(System.in);
            int option = sc7.nextInt();
            if(option==2){
                isFinished=true;
            }

            }
        }

        log.info("Pedido finalizado con éxito");
    }
}
