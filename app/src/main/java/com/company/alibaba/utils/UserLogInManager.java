package com.company.alibaba.utils;


import android.content.SharedPreferences;

import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.User;

public class UserLogInManager {

    public static void logIn(User user){
        SharedPreferences sp = AppController.getInstance().getApplicationContext().getSharedPreferences("LOGGED_IN" , AppController.getInstance().getApplicationContext().MODE_PRIVATE) ;
        SharedPreferences.Editor spe = sp.edit() ;
        spe.putString("userId"  , user.getId()+"") ;
        spe.putString("email"  , user.getEmail()+"") ;
        spe.putString("username"  , user.getUsername()+"") ;
        spe.commit() ;
    }

    public static User isLoggedIn(){
        SharedPreferences prefs = AppController.getInstance().getApplicationContext().getSharedPreferences("LOGGED_IN" , AppController.getInstance().getApplicationContext().MODE_PRIVATE);
        if(prefs.getString("userId" , null) != null) {
            User user = new User() ;
            user.setId(prefs.getString("userId" , null));
            user.setUsername(prefs.getString("username" , null));
            user.setEmail(prefs.getString("email" , null));
            return user ;
        }
        return null ;
    }







}
