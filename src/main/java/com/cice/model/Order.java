package com.cice.model;

import java.sql.Date;

public class Order {

    int order_id;
    Date order_date;
    String order_mode;
    int customer_id;
    int order_status;
    float order_total;
    int sales_rep_id;
    int promotion_id;
    int line_item_id;
    int product_id;
    float unit_price;
    int quantity;

    public Order() {
    }

    public Order(int order_id, Date order_date, String order_mode, int customer_id, int order_status, float order_total, int sales_rep_id, int promotion_id, int line_item_id, int product_id, float unit_price, int quantity) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_mode = order_mode;
        this.customer_id = customer_id;
        this.order_status = order_status;
        this.order_total = order_total;
        this.sales_rep_id = sales_rep_id;
        this.promotion_id = promotion_id;
        this.line_item_id = line_item_id;
        this.product_id = product_id;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_mode() {
        return order_mode;
    }

    public void setOrder_mode(String order_mode) {
        this.order_mode = order_mode;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public float getOrder_total() {
        return order_total;
    }

    public void setOrder_total(float order_total) {
        this.order_total = order_total;
    }

    public int getSales_rep_id() {
        return sales_rep_id;
    }

    public void setSales_rep_id(int sales_rep_id) {
        this.sales_rep_id = sales_rep_id;
    }

    public int getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(int promotion_id) {
        this.promotion_id = promotion_id;
    }

    public int getLine_item_id() {
        return line_item_id;
    }

    public void setLine_item_id(int line_item_id) {
        this.line_item_id = line_item_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_date=" + order_date +
                ", order_mode='" + order_mode + '\'' +
                ", customer_id=" + customer_id +
                ", order_status=" + order_status +
                ", order_total=" + order_total +
                ", sales_rep_id=" + sales_rep_id +
                ", promotion_id=" + promotion_id +
                ", line_item_id=" + line_item_id +
                ", product_id=" + product_id +
                ", unit_price=" + unit_price +
                ", quantity=" + quantity +
                '}';
    }
}
