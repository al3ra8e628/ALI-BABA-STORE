package com.company.alibaba.entities;

import android.app.Activity;
import android.graphics.Picture;
import android.support.v7.app.AppCompatActivity;

import com.company.alibaba.utils.CurrencyManager;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable{
    private int id ;
    private int subCatId ;
    private int superCatId ;
    private String title ;
    private String corporateName ;
    private int rating ;
    private String price ;
    private String oldPrice ;
    private String imagePath ;


    private ArrayList<Size> sizes ;
    private ArrayList<Color> colors ;
    private ArrayList<ItemPicture> pictures ;
    private int selectedSize ;
    private int selectedColor ;

    private String information  ;


    public Item() {
        sizes = new ArrayList<>() ;
        colors = new ArrayList<>() ;
        pictures = new ArrayList<>() ;
    }

    public Item(int id, int subCatId, int superCatId, String title, String corporateName, int rating) {
        this() ;
        this.id = id;
        this.subCatId = subCatId;
        this.superCatId = superCatId;
        this.title = title;
        this.corporateName = corporateName;
        this.rating = rating;
    }


    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public ArrayList<ItemPicture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<ItemPicture> pictures) {
        this.pictures = pictures;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPrice(AppCompatActivity activity) {
        if(activity == null){
            return price ;
        }

        Currency currency = CurrencyManager.getCurrency(activity);
        return currency.getCurrencySymbol()+" "+price;
    }

    public String getOldPrice(AppCompatActivity activity) {
        if(activity == null){
            return oldPrice ;
        }
        return oldPrice;
    }

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Size> sizes) {
        this.sizes = sizes;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }



    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

    public int getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(int selectedSize) {
        this.selectedSize = selectedSize;
    }

    public int getSelectedColor() {
        return selectedColor;
    }





    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public int getSuperCatId() {
        return superCatId;
    }

    public void setSuperCatId(int superCatId) {
        this.superCatId = superCatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
