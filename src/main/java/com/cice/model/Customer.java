package com.cice.model;

import java.sql.Date;

public class Customer {

    int customer_id;
    String cust_first_name;
    String cust_last_name;
    String nls_language;
    String nls_territory;
    int credit_limit;
    String cust_email;
    int account_mgr_id;
    Date date_of_bird;
    String marital_status;
    String gender;
    String income_level;

    public Customer() {
    }

    public Customer(int customer_id, String cust_first_name, String cust_last_name, String nsl_language, String nls_territory, int credit_limit, String cust_email, int account_mgr_id, Date date_of_bird, String marital_status, String gender, String income_level) {
        this.customer_id = customer_id;
        this.cust_first_name = cust_first_name;
        this.cust_last_name = cust_last_name;
        this.nls_language = nls_language;
        this.nls_territory = nls_territory;
        this.credit_limit = credit_limit;
        this.cust_email = cust_email;
        this.account_mgr_id = account_mgr_id;
        this.date_of_bird = date_of_bird;
        this.marital_status = marital_status;
        this.gender = gender;
        this.income_level = income_level;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCust_first_name() {
        return cust_first_name;
    }

    public void setCust_first_name(String cust_first_name) {
        this.cust_first_name = cust_first_name;
    }

    public String getCust_last_name() {
        return cust_last_name;
    }

    public void setCust_last_name(String cust_last_name) {
        this.cust_last_name = cust_last_name;
    }

    public String getNls_language() {
        return nls_language;
    }

    public void setNls_language(String nls_language) {
        this.nls_language = nls_language;
    }

    public String getNls_territory() {
        return nls_territory;
    }

    public void setNls_territory(String nls_territory) {
        this.nls_territory = nls_territory;
    }

    public int getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(int credit_limit) {
        this.credit_limit = credit_limit;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public int getAccount_mgr_id() {
        return account_mgr_id;
    }

    public void setAccount_mgr_id(int account_mgr_id) {
        this.account_mgr_id = account_mgr_id;
    }

    public Date getDate_of_bird() {
        return date_of_bird;
    }

    public void setDate_of_bird(Date date_of_bird) {
        this.date_of_bird = date_of_bird;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIncome_level() {
        return income_level;
    }

    public void setIncome_level(String income_level) {
        this.income_level = income_level;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", cust_first_name='" + cust_first_name + '\'' +
                ", cust_last_name='" + cust_last_name + '\'' +
                ", nls_language='" + nls_language + '\'' +
                ", nls_territory='" + nls_territory + '\'' +
                ", credit_limit=" + credit_limit +
                ", cust_email='" + cust_email + '\'' +
                ", account_mgr_id=" + account_mgr_id +
                ", date_of_bird=" + date_of_bird +
                ", marital_status='" + marital_status + '\'' +
                ", gender='" + gender + '\'' +
                ", income_level='" + income_level + '\'' +
                '}';
    }
}



