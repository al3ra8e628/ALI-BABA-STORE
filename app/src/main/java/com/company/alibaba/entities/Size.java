package com.company.alibaba.entities;

import android.widget.TextView;

/**
 * Created by al3ra8e on 4/30/2018.
 */

public class Size {

    private int sizeId ;
    private String sizeName ;
    private TextView sizeView ;

    public Size() {
    }


    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public TextView getSizeView() {
        return sizeView;
    }

    public void setSizeView(TextView sizeView) {
        this.sizeView = sizeView;
    }
}




