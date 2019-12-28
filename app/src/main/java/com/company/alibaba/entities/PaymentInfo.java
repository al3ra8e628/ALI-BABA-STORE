package com.company.alibaba.entities;

import java.io.Serializable;

/**
 * Created by al3ra8e on 5/29/2018.
 */

public class PaymentInfo implements Serializable{
    private String orderId ;
    private String amount ;
    private String productsNames  ;
    private String email ;
    private String currency ;


    public PaymentInfo() {
    }

    public PaymentInfo(String orderId, String amount, String productsNames, String email, String currency) {
        this.orderId = orderId;
        this.amount = amount;
        this.productsNames = productsNames;
        this.email = email;
        this.currency = currency;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductsNames() {
        return productsNames;
    }

    public void setProductsNames(String productsNames) {
        this.productsNames = productsNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
