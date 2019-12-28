package com.company.alibaba.entities;


import android.support.v7.app.AppCompatActivity;

import com.company.alibaba.utils.CurrencyManager;

public class CartItem {
    private static String totalPrice ;
    private int cartId ;
    private int quantity ;
    private String itemTitle ;
    private String itemPrice ;
    private String colorName ;
    private String sizeName ;
    private String productImage ;

    public CartItem() {
    }

    public CartItem(int cartId, int quantity, String itemTitle, String itemPrice, String colorName, String sizeName, String productImage) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.colorName = colorName;
        this.sizeName = sizeName;
        this.productImage = productImage;
    }

    public static void incrementTotal(String value) {
        totalPrice = (Double.parseDouble(totalPrice)+Double.parseDouble(value))+"";
    }

    public static void decrementTotal(String value) {
        totalPrice = (Double.parseDouble(totalPrice)-Double.parseDouble(value))+"";
    }


    public static String getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(String totalPrice) {
        CartItem.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }


    public void setItemPrice(String itemPrice) {

        this.itemPrice = itemPrice;
    }
    public String getItemPrice(AppCompatActivity activity) {
        if(activity == null){
            return itemPrice;
        }
        Currency currency = CurrencyManager.getCurrency(activity);
        return currency.getCurrencySymbol()+" "+itemPrice;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return cartId == cartItem.cartId;
    }



}
