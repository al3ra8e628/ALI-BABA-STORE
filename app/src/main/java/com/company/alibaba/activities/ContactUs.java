package com.company.alibaba.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.company.alibaba.R;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;

public class ContactUs extends AppCompatActivity implements Languages {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        LanguageManager.setLanguage(this);

        if(!LanguageManager.isLangEn(this)) {

            ImageView imageView = (ImageView) findViewById(R.id.imageView5);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.contact_us_ar));

        }

    }

    @Override
    public void setDirection(String lang) {








    }

    public void contact(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"alibabastorcoustomer@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"customer subject");
        intent.putExtra(Intent.EXTRA_TEXT,"mail body");
        startActivity(Intent.createChooser(intent,""));
    }
}
