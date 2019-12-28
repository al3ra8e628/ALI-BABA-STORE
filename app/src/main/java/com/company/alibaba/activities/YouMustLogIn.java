package com.company.alibaba.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.company.alibaba.R;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;

import org.w3c.dom.Text;

public class YouMustLogIn extends AppCompatActivity implements Languages {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_you_must_log_in);

        TextView message = (TextView) findViewById(R.id.messageDialog) ;

        String requestActivity = getIntent().getStringExtra("requestActivity") ;
        switch (Integer.parseInt(requestActivity)){
            case 1 :
                message.setText(getResources().getString(R.string.logInToPassCart));
                break;
            case 2 :
                message.setText(getResources().getString(R.string.logInToPassWish));
                break;
            case 3 :
                message.setText(getResources().getString(R.string.logInToPassOrders));
                break;

            case 4 :
                message.setText(getResources().getString(R.string.logInToAddToCart));
                break;
            case 5 :
                message.setText(getResources().getString(R.string.logInToAddToWish));
                break;
            case 6 :
                message.setText(getResources().getString(R.string.logInToAddReview));
                break;
            case 7 :
                message.setText(getResources().getString(R.string.logInToAddShowLottery));
                break;
        }

    }
    public void cancel(View view) {
        this.finish();
    }
    public void loginDialog(View view) {
        startActivityForResult(new Intent(this , LoginActivity.class) , 1234);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 0000){
            this.finish();
            return;
        }
        if(RESULT_OK == resultCode && requestCode == 1234){
            this.setResult(RESULT_OK , data);
            this.finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setDirection(String lang) {

    }
}
