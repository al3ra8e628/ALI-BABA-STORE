package com.company.alibaba.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.adapters.LotteryItemsAdapter;
import com.company.alibaba.adapters.OrdersItemsAdapter;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.entities.Lottery;
import com.company.alibaba.entities.Order;
import com.company.alibaba.entities.User;
import com.company.alibaba.utils.CurrencyManager;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.utils.UserLogInManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyLotteries extends AppCompatActivity implements Languages {
    RecyclerView recyclerView ;
    TextView numberOfItemsOrders ;
    SwipeRefreshLayout ordersRefresher ;
    AppCompatActivity activity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_my_orders);
        ordersRefresher = (SwipeRefreshLayout) findViewById(R.id.ordersSwipeRefresher);
        activity = this ;
        numberOfItemsOrders = (TextView) findViewById(R.id.numberOfItemsOrders);


        if(LanguageManager.isLangEn(this)) {
            getSupportActionBar().setTitle("My Lotteries");
        }else{
            getSupportActionBar().setTitle("السحوبات");
        }

        ordersRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems() ;
            }
        });


        initial() ;
    }

    private void initial() {
        recyclerView = (RecyclerView) findViewById(R.id.orders_view_items_recycler);
        loadItems() ;
    }

    private ArrayList<Lottery> loadItems() {
        final ProgressDialog progressDialog = new ProgressDialog(this) ;
        progressDialog.setMessage(getResources().getString(R.string.loadingMessage));
        progressDialog.setCancelable(false);
        progressDialog.show();
        final ArrayList<Lottery>  lotteries = new ArrayList<>() ;
        JSONObject params = new JSONObject() ;
        User user = UserLogInManager.isLoggedIn() ;
        try {
            params.put("userId" , ""+user.getId()) ;
            Currency currency = CurrencyManager.getCurrency(this) ;
            params.put("currency" , currency.getCurrencyCode()+"") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppController.getInstance().addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST
                        , AccessLinks.GET_LOTTERIES
                        , params
                        , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                           JSONArray jsonOrders = response.getJSONArray("Lotteries") ;
                           for(int i = 0 ; i < jsonOrders.length() ; i++){
                                JSONObject jsonOrder = jsonOrders.getJSONObject(i) ;
                                Lottery lottery = new Lottery() ;
                                lottery.setDate(jsonOrder.getString("LOTTERY_DATE"));
                                lottery.setId(jsonOrder.getInt("LOTTERY_ID"));
                                lottery.setAmount(jsonOrder.getString("AMOUNT"));
                                lottery.setLotteryName(jsonOrder.getString("NAME"));
                                lottery.setTransactionId(jsonOrder.getString("LOTTERY_ID"));
                                lotteries.add(lottery) ;
                           }

                            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL , false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                            recyclerView.setAdapter(new LotteryItemsAdapter(lotteries , activity));

                            ordersRefresher.setRefreshing(false);
                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            ordersRefresher.setRefreshing(false);
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                        if(LanguageManager.isLangEn(activity)) {
                            numberOfItemsOrders.setText("? Lotteries".replace("?" , lotteries.size()+""));
                        }else{
                            numberOfItemsOrders.setText("عدد السحوبات 0".replace("0" , lotteries.size()+""));
                        }
                    }
                }
                , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    ordersRefresher.setRefreshing(false);
                    Snackbar.make(recyclerView , getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
            )



        );

        return lotteries ;
    }


    @Override
    public void setDirection(String lang) {

    }
}
