package com.company.alibaba.entities;

/**
 * Created by al3ra8e on 4/30/2018.
 */

public class ItemPicture {
    private int picId ;
    private String picPath ;
    private int colorId ;


    public ItemPicture() {
    }


    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
