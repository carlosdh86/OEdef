package com.cice.view;
import com.cice.controller.OrderController;
import com.cice.dao.CustomerDaoImpl;
import com.cice.dao.OrderDaoImpl;
import com.cice.idao.IOrderDao;
import com.cice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OrderView {

    private static final Logger log = LoggerFactory.getLogger(OrderView.class);

    public static void chooseAction () throws SQLException {
        log.info("Elige la acción que quieres realizar sobre pedidos");
        log.info("-1:Realizar pedido");
        log.info("-2:Consultar pedido");
        log.info("-3:Modificar pedido");
        log.info("-4:Borrar pedido");
        log.info("-5:Pedidos totales con facturación");
        log.info("-6:Volver al menú principal");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {

            case 1:
                OrderView orderView = new OrderView();
                try {
                    orderView.createOrder();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                chooseAction();

            case 2:
                OrderController orderController = new OrderController();
                log.info("¿Qué pedidos quieres consultar?");
                log.info("1-:Todos");
                log.info("2-:Filtrar por ID");
                Scanner sc2 = new Scanner(System.in);
                int option2 = sc2.nextInt();
                switch (option2) {
                    case 1:
                        orderController.getOrders();
                        chooseAction();
                    case 2:
                        log.info("Introduce el ID de pedido");
                        Scanner sc3 = new Scanner(System.in);
                        int orderId = sc3.nextInt();
                        orderController.getOrderById(orderId);
                        chooseAction();
                }

            case 3:

                Order order = new Order();
                log.info("Introduce ID del pedido que quieres modificar");
                Scanner sc9 = new Scanner(System.in);
                order.setOrder_id(sc9.nextInt());

                log.info("Elige el dato que quieres modificar sobre el pedido nº " + order.getOrder_id());
                log.info("-1: Modo del pedido (En tienda, por teléfono o por email)");
                log.info("-2: ID del cliente");
                log.info("-3: ID de empleado");
                log.info("-4: ID de promoción");
                log.info("-5: Añadir producto al pedido");
                log.info("-6: Quitar producto del pedido");

                Scanner sc10 = new Scanner(System.in);
                int option3 = sc10.nextInt();

                switch (option3) {

                    case 1:
                        log.info("Introduce el nuevo modo de pedido");
                        Scanner sc11 = new Scanner(System.in);
                        order.setOrder_mode(sc11.next());
                        break;

                    case 2:
                        log.info("Introduce el nuevo ID de cliente");
                        Scanner sc12 = new Scanner(System.in);
                        order.setCustomer_id(sc12.nextInt());
                        break;

                    case 3:
                        log.info("Introduce el nuevo ID de empleado");
                        Scanner sc13 = new Scanner(System.in);
                        order.setSales_rep_id(sc13.nextInt());
                        break;

                    case 4:
                        log.info("Introduce el nuevo ID de promoción");
                        Scanner sc14 = new Scanner(System.in);
                        order.setPromotion_id(sc14.nextInt());
                        break;

                    case 5:
                        log.info("Introduce ID del producto");
                        Scanner sc15 = new Scanner(System.in);
                        order.setProduct_id(sc15.nextInt());
                        log.info("Introduce cantidad del producto");
                        Scanner sc16 = new Scanner(System.in);
                        order.setQuantity(sc16.nextInt());
                        break;

                    case 6:
                        log.info("Introduce ID del producto");
                        Scanner sc17 = new Scanner(System.in);
                        order.setProduct_id(sc17.nextInt());
                        break;

                }

                OrderDaoImpl orderDao = new OrderDaoImpl();
                boolean isUpdated = orderDao.updateOrder (order,option3);
                System.out.println(isUpdated);

                    if(isUpdated) {
                      log.info("Pedido " + order.getOrder_id() + " modificado con éxito");
                    }
                    chooseAction();

            case 4:
                OrderView orderView4 = new OrderView();
                orderView4.deleteOrder();
                chooseAction();

            case 5:
                OrderController orderController5 = new OrderController();
                log.info("El número total de pedidos es: " + new DecimalFormat("#").format(orderController5.getOrderTotal().get(0)));
                log.info("El importe total de facturación es: " + orderController5.getOrderTotal().get(1) + " €");


            case 6:
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

        boolean isCreated = false;
        boolean isFinished=false;

        while(!isFinished) {
            log.info("Introduce ID del producto");
            Scanner sc5 = new Scanner(System.in);
            order.setProduct_id(sc5.nextInt());
            log.info("Introduce la cantidad de producto");
            Scanner sc6 = new Scanner(System.in);
            order.setQuantity(sc6.nextInt());

            IOrderDao iOrderDao = new OrderDaoImpl();
            iOrderDao.createOrder(order,isCreated);

            if(order.getOrder_id()!=0){
                isCreated=true;
            }

            if (isCreated) {
                log.info("Producto añadido con éxito al pedido nº: "+order.getOrder_id());

                log.info("¿Has acabado ya este pedido?");
                log.info("1- No, quiero añadir más productos");
                log.info("2- Sí, quiero finalizar este pedido");

            Scanner sc7 = new Scanner(System.in);
            int option = sc7.nextInt();
            if(option==2){
                isFinished=true;
                iOrderDao.finishOrder(order);
                log.info("Pedido " + order.getOrder_id() + " finalizado con éxito");
            }

            }
        }
    }

    public void deleteOrder() throws SQLException {

        Order order = new Order();
        boolean isDeleted = false;

        log.info("Introduce ID del pedido que quiere borrar");
        Scanner sc8 = new Scanner(System.in);
        order.setOrder_id(sc8.nextInt());

        IOrderDao iOrderDao = new OrderDaoImpl();
        isDeleted = iOrderDao.deleteOrder(order);
        System.out.println(isDeleted);

        if (!isDeleted) {
            log.info("No existe el pedido que quiere borrar");
        }else{
            log.info("Pedido "+ order.getOrder_id() + " borrado con éxito");
        }

        chooseAction();
    }

}
