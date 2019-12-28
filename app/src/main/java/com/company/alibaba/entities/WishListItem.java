package com.company.alibaba.entities;

import android.support.v7.app.AppCompatActivity;

import com.company.alibaba.utils.CurrencyManager;

public class WishListItem {

    private int wishListId ;
    private int itemId ;
    private String image ;
    private String itemName ;
    private String itemPrice ;
    private String colorId;
    private String colorName ;
    private String sizeId ;
    private String itemSize ;


    public WishListItem() {
    }

    public WishListItem(int wishListId, int itemId,String image , String itemName, String itemPrice, String colorId, String colorName, String sizeId, String itemSize) {
        this.wishListId = wishListId;
        this.image = image ;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.colorId = colorId;
        this.colorName = colorName;
        this.sizeId = sizeId;
        this.itemSize = itemSize;
    }


    public int getWishListId() {
        return wishListId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getItemPrice(AppCompatActivity activity) {
        if(activity == null){
            return itemPrice;
        }
        Currency currency = CurrencyManager.getCurrency(activity);
        return currency.getCurrencySymbol()+" "+itemPrice;
    }


    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListItem wishListItem = (WishListItem) o;
        return wishListId == wishListItem.wishListId ;
    }



}
