package com.company.alibaba.entities;


import android.support.v7.app.AppCompatActivity;

import com.company.alibaba.utils.CurrencyManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

    private int orderId ;
    private int userId ;
    private int productId ;
    private String productTitle ;
    private int quantity ;
    private String date ;
    private int state ;
    private String price1 ;
    private String price2 ;
    String itemPicture ;
    String sizeName ;
    String ColorName ;

  /*
            "ORDER_STATUS": "-1",
            "ITEM_PICTURE": null,
            "COLOR_NAME": "0",
            "SIZE": "0"
            */


    public Order() {
    }

    public Order(int orderId, int userId, int productId, String productTitle, int quantity, String date, int state, String price1, String price2) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.date = date;
        this.state = state;
        this.price1 = price1;
        this.price2 = price2;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getColorName() {
        return ColorName;
    }

    public void setColorName(String colorName) {
        ColorName = colorName;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getPrice(AppCompatActivity activity) {
        if(activity == null){
            return price1 ;
        }

        Currency currency = CurrencyManager.getCurrency(activity);
        return currency.getCurrencySymbol()+" "+price1;
    }


    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
          /*
            "ORDER_TIME": "1521025047",
            */
        SimpleDateFormat f = new SimpleDateFormat("Y/M/d");
        String dateFormatted = f.format(new Date(Long.parseLong(date)));
        this.date = dateFormatted;
    }

    public String getState() {
        if(state == 0){
            return "in the way" ;
        }else if(state == 1){
            return "delivered" ;
        }
        return "";
    }

    public void setState(int state) {
        this.state = state;
    }
}
