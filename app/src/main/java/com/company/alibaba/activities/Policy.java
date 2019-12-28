package com.company.alibaba.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.company.alibaba.R;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;

public class Policy extends AppCompatActivity implements Languages {

    private WebView mWebview ;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mWebview  = new WebView(this);



        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        LanguageManager.setLanguage(this);


        if(LanguageManager.isLangEn(this)) {
            mWebview .loadUrl("https://www.alibabastor.com/web_view/EN/policy.php");
        }else{
            mWebview .loadUrl("https://www.alibabastor.com/web_view/AR/policy.php");
        }


        setContentView(mWebview);

        if(LanguageManager.isLangEn(this)) {
          getSupportActionBar().setTitle("Privacy And Policy");
        }else{
          getSupportActionBar().setTitle("الشروط والاحكام");
        }
    }


    @Override
    public void setDirection(String lang) {

    }
}
