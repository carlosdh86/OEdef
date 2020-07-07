package com.cice.view;

import com.cice.controller.CustomerController;
import com.cice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CustomerView {

    private static final Logger log = LoggerFactory.getLogger(CustomerView.class);


    public void chooseAction () throws SQLException {

        log.info("Elige la acción que quieres realizar sobre clientes");
        log.info("-1:Crear cliente");
        log.info("-2:Consultar cliente");
        log.info("-3:Modificar cliente");
        log.info("-4:Borrar cliente");
        log.info("-5:Volver al menú principal");

        Scanner sc1 = new Scanner(System.in);
        int option = sc1.nextInt();

        switch (option) {

            case 1:
                Customer customer = new Customer();
                CustomerController customerController = new CustomerController();

                log.info("Introduce nombre del cliente");
                Scanner sc2 = new Scanner(System.in);
                customer.setCust_first_name(sc2.next());

                log.info("Introduce apellido del cliente");
                Scanner sc3 = new Scanner(System.in);
                customer.setCust_last_name(sc3.next());

                log.info("Introduce email del cliente");
                Scanner sc4 = new Scanner(System.in);
                customer.setCust_email(sc4.next());

                boolean isCreated = customerController.createCustomer(customer);
                if (isCreated) {
                    log.info("Usuario " + customer.getCust_first_name() + " " + customer.getCust_last_name() + " registrado con éxito");
                }
                chooseAction();

            case 2:
                CustomerController customerController2 = new CustomerController();

                log.info("¿Qué clientes quieres consultar?");
                log.info("1-:Todos");
                log.info("2-:Filtrar por apellido");
                Scanner sc5 = new Scanner(System.in);
                int option2 = sc5.nextInt();

                switch (option2) {
                    
                    case 1:
                        customerController2.getCustomers();
                        chooseAction();

                    case 2:
                        log.info("Introduce el apellido");
                        Scanner sc6 = new Scanner(System.in);
                        String lastName = sc6.next();
                        customerController2.getCustomersByLastName(lastName);
                        chooseAction();

                    default:
                        log.warn("Opción incorrecta. Marque una opción correcta ");
                        chooseAction();
                }

            case 3:
                log.info("Introduce ID del cliente que quieres modificar");
                Scanner sc7 = new Scanner(System.in);
                Customer customer2 = new Customer();
                customer2.setCustomer_id(sc7.nextInt());

                log.info("Elige el dato que quieres modificar sobre el cliente nº " + customer2.getCustomer_id());
                log.info("-1: Nombre");
                log.info("-2: Primer apellido");
                log.info("-3: Límite de crédito");
                log.info("-4: Email");
                log.info("-5: ID de su asesor comercial");
                log.info("-6: Fecha de nacimiento");
                log.info("-7: Estado civil");
                log.info("-8: Género");
                log.info("-9: Nivel de ingresos");

                Scanner sc8 = new Scanner(System.in);
                int option3 = sc8.nextInt();

                switch (option3) {
                    case 1:
                        log.info("Introduce el nombre");
                        Scanner sc9 = new Scanner(System.in);
                        customer2.setCust_first_name(sc9.next());
                        break;

                    case 2:
                        log.info("Introduce el apellido");
                        Scanner sc10 = new Scanner(System.in);
                        customer2.setCust_last_name(sc10.next());
                        break;

                    case 3:
                        log.info("Introduce el límite de crédito ");
                        Scanner sc11 = new Scanner(System.in);
                        customer2.setCredit_limit(sc11.nextInt());
                        break;

                    case 4:
                        log.info("Introduce el email");
                        Scanner sc12 = new Scanner(System.in);
                        customer2.setCust_email(sc12.next());
                        break;

                    case 5:
                        log.info("Introduce ID del manager");
                        Scanner sc13 = new Scanner(System.in);
                        customer2.setAccount_mgr_id(sc13.nextInt());
                        break;

                    case 6:
                        Date birthdayDate = null;
                        while (birthdayDate==null){
                            log.info("Introduce fecha de nacimiento en formato dd/mm/aaaa");
                            Scanner sc14 = new Scanner(System.in);
                            String birthDayString = sc14.next();
                            birthdayDate = convertStringToDate(birthDayString);
                            customer2.setDate_of_birth(birthdayDate);
                        }
                        break;

                    case 7:
                        log.info("Introduce estado civil");
                        Scanner sc15 = new Scanner(System.in);
                        customer2.setMarital_status(sc15.next());
                        break;

                    case 8:
                        log.info("Introduce género (M para Masculino, F para Femenino, O para Otro)");
                        Scanner sc16 = new Scanner(System.in);
                        customer2.setGender(sc16.next());
                        break;

                    case 9:
                        log.info("Introduce nivel de ingresos");
                        Scanner sc17 = new Scanner(System.in);
                        customer2.setIncome_level(sc17.next());
                        break;

                    default:
                        log.warn("Opción incorrecta. Marque una opción correcta ");
                        chooseAction();
                }

                CustomerController customerController3 = new CustomerController();
                boolean isUpdated = customerController3.updateCustomer(customer2,option3);

                if(isUpdated) {
                    log.info("Usuario " + customer2.getCustomer_id() + " modificado con éxito");
                }
                chooseAction();

            case 4:
                log.info("Introduce ID del cliente que quieres eliminar");
                Scanner sc18 = new Scanner(System.in);
                int customerId= sc18.nextInt();

                CustomerController customerController4 = new CustomerController();
                boolean isDeleted = customerController4.deleteCustomer(customerId);

                if(!isDeleted) {
                    log.info("No existe el cliente que quiere eliminar");
                }else
                    log.info("Cliente " + customerId + " eliminado con éxito");

                chooseAction();

            case 5:
                InitialView.appStart();
                break;

            default:
               log.warn("Opción incorrecta. Marque una opción correcta ");
               chooseAction();
        }
    }

    public Date convertStringToDate (String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
