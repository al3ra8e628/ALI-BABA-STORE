package com.company.alibaba.entities;

import android.support.v7.app.AppCompatActivity;

import com.company.alibaba.utils.CurrencyManager;

public class Lottery {

    private String transactionId  ;
    private int id ;
    private String date ;
    private String lotteryName ;
    private String amount ;

    public String getAmount(AppCompatActivity activity) {
        if(activity == null){
            return amount ;
        }
        Currency currency = CurrencyManager.getCurrency(activity);
        return currency.getCurrencySymbol()+" "+amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Lottery(String transactionId, int id, String date, String lotteryName) {
        this.transactionId = transactionId;
        this.id = id;
        this.date = date;
        this.lotteryName = lotteryName;
    }

    public Lottery() {
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }



}
