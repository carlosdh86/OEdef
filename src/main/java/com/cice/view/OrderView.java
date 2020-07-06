package com.cice.view;

import com.cice.controller.OrderController;
import com.cice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class OrderView {

    private static final Logger log = LoggerFactory.getLogger(OrderView.class);

    public static void chooseAction () throws SQLException {
        log.info("Elige la acción que quieres realizar sobre pedidos");
        log.info("-1:Realizar pedido");
        log.info("-2:Consultar pedido");
        log.info("-3:Modificar pedido");
        log.info("-4:Eliminar pedido");
        log.info("-5:Pedidos totales con facturación");
        log.info("-6:Volver al menú principal");
        Scanner sc1 = new Scanner(System.in);
        int option = sc1.nextInt();

        switch (option) {

            case 1:
                Order order = new Order();
                log.info("Introduce modo del pedido (En tienda, por teléfono o por email)");
                Scanner sc2 = new Scanner(System.in);
                order.setOrder_mode(sc2.next());

                log.info("Introduce ID del cliente");
                Scanner sc3 = new Scanner(System.in);
                order.setCustomer_id(sc3.nextInt());

                log.info("Introduce tu ID de empleado");
                Scanner sc4 = new Scanner(System.in);
                order.setSales_rep_id(sc4.nextInt());

                boolean isCreated = false;
                boolean isFinished=false;

                while(!isFinished) {
                    log.info("Introduce ID del producto");
                    Scanner sc5 = new Scanner(System.in);
                    order.setProduct_id(sc5.nextInt());

                    log.info("Introduce la cantidad de producto");
                    Scanner sc6 = new Scanner(System.in);
                    order.setQuantity(sc6.nextInt());

                    OrderController orderController = new OrderController();
                    orderController.createOrder(order, isCreated);

                    if (order.getOrder_id() != 0) {
                        isCreated = true;
                    }

                    if (isCreated) {
                        log.info("Producto añadido con éxito al pedido nº: " + order.getOrder_id());
                        log.info("¿Has acabado ya este pedido?");
                        log.info("1- No, quiero añadir más productos");
                        log.info("2- Sí, quiero finalizar este pedido");

                        Scanner sc7 = new Scanner(System.in);
                        int option2 = sc7.nextInt();
                        if (option2 == 2) {
                            isFinished = true;
                            orderController.finishOrder(order);
                            log.info("Pedido " + order.getOrder_id() + " finalizado con éxito");
                        }
                    }
                }
                chooseAction();

            case 2:
                OrderController orderController1 = new OrderController();
                log.info("¿Qué pedidos quieres consultar?");
                log.info("1-:Todos");
                log.info("2-:Filtrar por ID");
                Scanner sc8 = new Scanner(System.in);
                int option2 = sc8.nextInt();

                switch (option2) {
                    case 1:
                        for (Order order2 : orderController1.getOrders()) {
                            System.out.println(order2);
                        }
                        chooseAction();
                    case 2:
                        log.info("Introduce el ID de pedido");
                        Scanner sc9 = new Scanner(System.in);
                        int orderId = sc9.nextInt();
                        System.out.println(orderController1.getOrderById(orderId));
                        chooseAction(); }

            case 3:
                Order order3 = new Order();
                log.info("Introduce ID del pedido que quieres modificar");
                Scanner sc10 = new Scanner(System.in);
                order3.setOrder_id(sc10.nextInt());

                log.info("Elige el dato que quieres modificar sobre el pedido nº " + order3.getOrder_id());
                log.info("-1: Modo del pedido (En tienda, por teléfono o por email)");
                log.info("-2: ID del cliente");
                log.info("-3: ID de empleado");
                log.info("-4: ID de promoción");
                log.info("-5: Añadir producto al pedido");
                log.info("-6: Quitar producto del pedido");
                Scanner sc11 = new Scanner(System.in);
                int option3 = sc11.nextInt();

                switch (option3) {
                    case 1:
                        log.info("Introduce el nuevo modo de pedido");
                        Scanner sc12 = new Scanner(System.in);
                        order3.setOrder_mode(sc12.next());
                        break;
                    case 2:
                        log.info("Introduce el nuevo ID de cliente");
                        Scanner sc13 = new Scanner(System.in);
                        order3.setCustomer_id(sc13.nextInt());
                        break;
                    case 3:
                        log.info("Introduce el nuevo ID de empleado");
                        Scanner sc14 = new Scanner(System.in);
                        order3.setSales_rep_id(sc14.nextInt());
                        break;
                    case 4:
                        log.info("Introduce el nuevo ID de promoción");
                        Scanner sc15 = new Scanner(System.in);
                        order3.setPromotion_id(sc15.nextInt());
                        break;
                    case 5:
                        log.info("Introduce ID del producto");
                        Scanner sc16 = new Scanner(System.in);
                        order3.setProduct_id(sc16.nextInt());
                        log.info("Introduce cantidad del producto");
                        Scanner sc17 = new Scanner(System.in);
                        order3.setQuantity(sc17.nextInt());
                        break;
                    case 6:
                        log.info("Introduce ID del producto");
                        Scanner sc18 = new Scanner(System.in);
                        order3.setProduct_id(sc18.nextInt());
                        break;
                    default:
                        log.warn("Opción incorrecta");
                }

                OrderController orderController2 = new OrderController();
                boolean isUpdated = orderController2.updateOrder(order3,option3);

                if(isUpdated) {
                      log.info("Pedido " + order3.getOrder_id() + " modificado con éxito");
                }
                chooseAction();

            case 4:
                log.info("Introduce ID del pedido que quiere eliminar");
                Scanner sc19 = new Scanner(System.in);
                Order order2 = new Order();
                order2.setOrder_id(sc19.nextInt());
                OrderController orderController3 = new OrderController();
                boolean isDeleted = orderController3.deleteOrder(order2);

                if (!isDeleted) {
                    log.info("No existe el pedido que quiere eliminar");
                }else{
                    log.info("Pedido "+ order2.getOrder_id() + " eliminado con éxito");
                }

                chooseAction();

            case 5:
                OrderController orderController5 = new OrderController();
                log.info("El número total de pedidos es: " + new DecimalFormat("#").format(orderController5.getOrderTotal().get(0)));
                log.info("El importe total de facturación es: " + orderController5.getOrderTotal().get(1) + " €");


            case 6:
                InitialView.appStart();
                break;

            default:
                log.warn("Opción incorrecta. Marque una opción correcta ");
                chooseAction();
        }
    }

   }


