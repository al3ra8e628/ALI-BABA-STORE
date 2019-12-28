package com.company.alibaba.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.company.alibaba.R;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;

public class SelectLangActivity extends AppCompatActivity  implements Languages{
    String selectedLang;
    ImageView arSelection ;
    ImageView enSelection ;
    Button apply  ;
    TextView title ;
    String lang ;
    public ConstraintLayout mainLayout ;
    AppCompatActivity activity ;
    LinearLayout linearLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_lang);
        initial() ;
        LanguageManager.setLanguage(this);

        lang = LanguageManager.getLanguage(this);


        if(lang.equals("en")){
            arSelection.setVisibility(View.INVISIBLE);
            enSelection.setVisibility(View.VISIBLE);
        }else{
            enSelection.setVisibility(View.INVISIBLE);
            arSelection.setVisibility(View.VISIBLE);
        }
        setDirection(lang);
    }

    public void setLangEn(View view) {
        arSelection.setVisibility(View.INVISIBLE);
        enSelection.setVisibility(View.VISIBLE);
        lang = "en" ;
        setDirection(lang);
    }

    public void setLangAR(View view) {
        enSelection.setVisibility(View.INVISIBLE);
        arSelection.setVisibility(View.VISIBLE);
        lang = "ar" ;
        setDirection(lang);
    }

    private void initial() {
        activity = this ;
        selectedLang = null ;
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        enSelection = (ImageView) findViewById(R.id.enSelection);
        arSelection = (ImageView) findViewById(R.id.arSelection);
        title    = (TextView) findViewById(R.id.select_lang_title_EN);
        mainLayout = (ConstraintLayout) findViewById(R.id.mainSelectLanguage);
        apply = (Button) findViewById(R.id.button);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageManager.saveLanguage(activity , lang);
                LanguageManager.setLanguage(activity);

                startActivity(new Intent(activity , HomeActivity.class));
                setResult(RESULT_OK);
                activity.finish();
            }
        });

    }

    @Override
    public void setDirection(String lang) {
        if(lang.equals("en")){
            title.setText("Select language");
            apply.setText("apply");
            title.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        }else{
            title.setText("اختيار اللغة");
            apply.setText("تطبيق");
            title.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        linearLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    }
}
