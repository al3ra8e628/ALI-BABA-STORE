package com.company.alibaba.entities;

import android.widget.ImageView;

/**
 * Created by al3ra8e on 4/15/2018.
 */

public class Currency {
    private int currencyId ;
    private String currecnyName ;
    private String currencyCode ;
    private String currencyRate ;
    private ImageView check ;
    private boolean selected ;
    private String currencySymbol   ;
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Currency() {
    }

    public Currency(int currencyId, String currecnyName) {
        this.currencyId = currencyId;
        this.currecnyName = currecnyName;
    }

    public Currency(int currencyId, String currecnyName, String currencyRate) {
        this.currencyId = currencyId;
        this.currecnyName = currecnyName;
        this.currencyRate = currencyRate;
    }

    public Currency(int currencyId, String currecnyName, String currencyRate, ImageView check) {
        this.currencyId = currencyId;
        this.currecnyName = currecnyName;
        this.currencyRate = currencyRate;
        this.check = check;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrecnyName() {
        return currecnyName;
    }

    public void setCurrecnyName(String currecnyName) {
        this.currecnyName = currecnyName;
    }

    public String getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(String currencyRate) {
        this.currencyRate = currencyRate;
    }

    public ImageView getCheck() {
        return check;
    }

    public void setCheck(ImageView check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object obj) {
        Currency currency = (Currency) obj ;
        return currency.getCurrencyCode().equals(this.getCurrencyCode());
    }
}
