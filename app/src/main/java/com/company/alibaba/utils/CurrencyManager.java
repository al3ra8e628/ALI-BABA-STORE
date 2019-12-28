package com.company.alibaba.utils;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.company.alibaba.entities.Currency;

import static android.content.Context.MODE_PRIVATE;

public class CurrencyManager {

    public static void saveCurrency(AppCompatActivity activity , Currency currency){
        SharedPreferences languagepref = activity.getSharedPreferences("currency",MODE_PRIVATE);
        SharedPreferences.Editor editor = languagepref.edit();
        editor.putString("currency", currency.getCurrecnyName());
        editor.putString("currencyId", currency.getCurrencyId()+"");
        editor.putString("symbol" , currency.getCurrencySymbol());
        editor.putString("Code" , currency.getCurrencyCode()) ;
        editor.commit();

    }

    public static Currency getCurrency(AppCompatActivity activity){
        SharedPreferences languagepref = activity.getSharedPreferences("currency",MODE_PRIVATE);
        Currency currency = new Currency();
        currency.setCurrencyId(Integer.parseInt(languagepref.getString("currencyId" , "0")));
        currency.setSelected(true);
        currency.setCurrecnyName(""+languagepref.getString("currency" , "USD"));
        currency.setCurrencySymbol(""+languagepref.getString("symbol" , "$"));
        currency.setCurrencyCode(""+languagepref.getString("Code" , "USD"));
        return currency ;
    }

}
