package com.company.alibaba.entities;


import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SuperCategory {

    private int categoryId ;
    private String categoryName ;
    TextView categoryTextView ;
    ImageView bg ;
    boolean isSelected ;
    private String ImagePath ;


    ArrayList<SubCategory> subCategories ;

    public SuperCategory() {
        subCategories = new ArrayList<>() ;
    }

    public SuperCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public SuperCategory(int id , String title , String imagePath) {
        this.categoryId = id ;
        this.categoryName = title ;
        this.setImagePath(imagePath);
    }


    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public void addToSubCategories(SubCategory subCategory){
        subCategories.add(subCategory) ;
    }

    public ArrayList<SubCategory> getSubCategories() {
        return subCategories;
    }

    public TextView getCategoryTextView() {
        return categoryTextView;
    }

    public void setCategoryTextView(TextView categoryTextView) {
        this.categoryTextView = categoryTextView;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public ImageView getBg() {
        return bg;
    }

    public void setBg(ImageView bg) {
        this.bg = bg;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public boolean equals(Object obj) {
        SuperCategory superCategory = (SuperCategory) obj ;
        return this.categoryId == superCategory.categoryId;
    }
}
