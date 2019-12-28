package com.company.alibaba.activities;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.adapters.OrdersItemsAdapter;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.Currency;
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

public class MyOrders extends AppCompatActivity implements Languages {
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
            getSupportActionBar().setTitle("My Orders");
        }else{
            getSupportActionBar().setTitle("الطلبات");
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

    private ArrayList<Order> loadItems() {
        final ProgressDialog progressDialog = new ProgressDialog(this) ;
        progressDialog.setMessage(getResources().getString(R.string.loadingMessage));
        progressDialog.setCancelable(false);
        progressDialog.show();
        final ArrayList<Order>  orders = new ArrayList<>() ;
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
                        , AccessLinks.GET_ORDERS
                        , params
                        , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           JSONArray jsonOrders = response.getJSONArray("Orders") ;
                           for(int i = 0 ; i < jsonOrders.length() ; i++){
                                JSONObject jsonOrder = jsonOrders.getJSONObject(i) ;
                                Order order = new Order() ;
                                order.setDate(jsonOrder.getString("ORDER_TIME"));
                                order.setOrderId(jsonOrder.getInt("ORDER_ID"));

                                if(LanguageManager.isLangEn(activity)) {
                                    order.setProductTitle(jsonOrder.getString("ITEM_NAME"));
                                }else{
                                    order.setProductTitle(jsonOrder.getString("ITEM_AR_NAME"));//item in arabic
                                }

                                order.setPrice1(jsonOrder.getString("ORDER_PRICE"));
                                order.setItemPicture(jsonOrder.getString("ITEM_PICTURE"));
                                order.setSizeName(jsonOrder.getString("SIZE"));
                                order.setColorName(jsonOrder.getString("COLOR_NAME"));
                                order.setQuantity(jsonOrder.getInt("ORDER_QUANTITY"));
                                order.setState(jsonOrder.getInt("ORDER_STATUS"));
                                orders.add(order) ;

                           }

                            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL , false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(new OrdersItemsAdapter(orders , activity));
                            ordersRefresher.setRefreshing(false);
                        } catch (JSONException e) {
                            ordersRefresher.setRefreshing(false);
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                        progressDialog.dismiss();
                        if(LanguageManager.isLangEn(activity)) {
                            numberOfItemsOrders.setText("? Products".replace("?" , orders.size()+""));
                        }else{
                            numberOfItemsOrders.setText("عدد العناصر 0".replace("0" , orders.size()+""));
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

        return orders ;
    }


    @Override
    public void setDirection(String lang) {

    }
}
