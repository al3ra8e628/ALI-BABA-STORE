package com.company.alibaba.entities;

import android.view.View;
import android.widget.TextView;

/**
 * Created by al3ra8e on 4/30/2018.
 */

public class Color {

    private int colorId ;
    private String colorName ;
    private String color ;
    private View colorView ;

    public Color() {
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public View getColorView() {
        return colorView;
    }

    public void setColorView(View colorView) {
        this.colorView = colorView;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
