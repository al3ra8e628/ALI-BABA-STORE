package com.company.alibaba.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.company.alibaba.R;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.utils.Countries;
import com.company.alibaba.utils.Currencies;
import com.company.alibaba.utils.CurrencyManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WelcomeActivity extends Activity {
    Activity activity  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        activity = this ;

      new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isLanguageSelected(activity)){
                   startActivity(new Intent(activity , HomeActivity.class));
                }else{
                    startActivity(new Intent(activity , SelectLangActivity.class));
                }
                activity.finish();
            }
        }).start() ;

    }

    public static Boolean isLanguageSelected(Activity activity){
        SharedPreferences languagepref = activity.getSharedPreferences("language",MODE_PRIVATE);
        return languagepref.contains("languageSelected") ;
    }


}
