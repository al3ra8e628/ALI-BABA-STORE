package com.company.alibaba.utils;


import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class LanguageManager {


    public static void setLanguage(AppCompatActivity activity){
        String language  = getLanguage(activity) ;
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config,
                activity.getBaseContext().getResources().getDisplayMetrics());
        Languages as = (Languages) activity ;
        as.setDirection(language);
    }

    public static void saveLanguage(AppCompatActivity activity , String language){
        SharedPreferences languagepref = activity.getSharedPreferences("language",MODE_PRIVATE);
        SharedPreferences.Editor editor = languagepref.edit();
        editor.putString("language", language);
        editor.putString("languageSelected", language);
        editor.commit();
    }

    public static String getLanguage(AppCompatActivity activity){
        SharedPreferences languagepref = activity.getSharedPreferences("language",MODE_PRIVATE);
        return languagepref.getString("language" , "en") ;
    }


    public static boolean isLangEn(AppCompatActivity appCompatActivity){
        return getLanguage(appCompatActivity).equals("en") ;
    }


    public static Boolean isLanguageSelected(AppCompatActivity activity){
        SharedPreferences languagepref = activity.getSharedPreferences("language",MODE_PRIVATE);
        return languagepref.contains("languageSelected") ;
    }

    public static String NumberArToEn(String number){
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<number.length();i++) {

            if(Character.isDigit(number.charAt(i))) {
                switch (number.charAt(i)){
                    case '٠' : builder.append('0');break;
                    case '١' : builder.append('1');break;
                    case '٢' : builder.append('2');break;
                    case '٣' : builder.append('3');break;
                    case '٤' : builder.append('4');break;
                    case '٥' : builder.append('5');break;
                    case '٦' : builder.append('6');break;
                    case '٧' : builder.append('7');break;
                    case '٨' : builder.append('8');break;
                    case '٩' : builder.append('9');break;
                }
            } else {
                builder.append(".");
            }
        }
        return builder.toString() ;
    }



}
