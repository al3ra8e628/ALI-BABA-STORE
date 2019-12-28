package com.company.alibaba.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.alibaba.R;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.utils.Currencies;
import com.company.alibaba.utils.CurrencyManager;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;

import java.util.ArrayList;


public class SetCurrency extends AppCompatActivity implements Languages {
    ArrayList<Currency> currencies ;
    ListView LVCurrencies ;
    AppCompatActivity activity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_set_currency);


        if(LanguageManager.isLangEn(this)){
            getSupportActionBar().setTitle("Select Currency");
        }else{
            getSupportActionBar().setTitle("تحديد العملة");
        }

        activity = this ;
        initial() ;
    }

    private void initial() {
        LVCurrencies = (ListView) findViewById(R.id.LVCurrencies);

        currencies = new Currencies().getCurrencies() ;

        for(Currency currency : currencies){
            Log.e("error" , CurrencyManager.getCurrency(activity).getCurrecnyName()+"") ;
            if(CurrencyManager.getCurrency(this).getCurrencyCode().equals(currency.getCurrencyCode())){
                currency.setSelected(true);
            }
        }


        LVCurrencies.setAdapter(new CurrenciesAdapter());

        LVCurrencies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(Currency temp : currencies){
                    if(temp.getCheck() != null)
                    if(temp.getCurrencyId() == position){
                        temp.setSelected(true);
                        temp.getCheck().setVisibility(View.VISIBLE);
                        CurrencyManager.saveCurrency(activity , temp);
                        //save this into shared preferences
                    }else{
                        temp.setSelected(false);
                        temp.getCheck().setVisibility(View.INVISIBLE);
                    }
                }
                setResult(RESULT_OK);
                activity.finish();//finish it with result to update the previous activity
            }
        });
    }

    @Override
    public void setDirection(String lang) {

    }


    class CurrenciesAdapter extends BaseAdapter{

        public CurrenciesAdapter(){


        }
        @Override
        public int getCount() {
            return currencies.size();
        }

        @Override
        public Object getItem(int position) {
            return currencies.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.currency_item_view , null) ;
            TextView textView = (TextView) view.findViewById(R.id.currency_name);

            textView.setText(currencies.get(position).getCurrencyCode()+" "+currencies.get(position).getCurrecnyName());

            ImageView imageView = (ImageView) view.findViewById(R.id.currency_check);

            currencies.get(position).setCheck(imageView);

           for(Currency temp : currencies){
                if(temp.getCheck() != null)
                    if(temp.isSelected()){
                        temp.getCheck().setVisibility(View.VISIBLE);
                    }else{
                        temp.getCheck().setVisibility(View.INVISIBLE);
                    }
            }
            return view;
        }
    }



}
