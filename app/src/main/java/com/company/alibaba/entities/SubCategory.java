package com.company.alibaba.entities;

import java.util.ArrayList;

public class SubCategory {

    private int superCategoryId ;
    private int id ;
    private String name ;
    private ArrayList<Item> items ;

    public SubCategory() {
        items = new ArrayList<>() ;
    }
    public SubCategory(int superCategoryId, int id, String name) {
        this.superCategoryId = superCategoryId;
        this.id = id;
        this.name = name;
    }

    public void addToItems(Item item){
        items.add(item) ;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getSuperCategoryId() {
        return superCategoryId;
    }

    public void setSuperCategoryId(int superCategoryId) {
        this.superCategoryId = superCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
