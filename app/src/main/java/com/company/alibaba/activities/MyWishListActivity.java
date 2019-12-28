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
import com.company.alibaba.adapters.WishlistItemsAdapter;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.entities.User;
import com.company.alibaba.entities.WishListItem;
import com.company.alibaba.utils.CurrencyManager;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.utils.UserLogInManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyWishListActivity extends AppCompatActivity implements Languages {
    RecyclerView recyclerView ;
    MyWishListActivity activity ;
    public TextView numberOfProducts ;
    SwipeRefreshLayout swipeRefreshLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_my_wish_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.wishListRefresher);



        if(LanguageManager.isLangEn(this)) {
            getSupportActionBar().setTitle("My Wishlist");
        }else{
            getSupportActionBar().setTitle("قائمة المفضلة");
        }

        activity = this ;
        numberOfProducts = (TextView) findViewById(R.id.numberOfItemsWishlist);
        initial() ;


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
            }
        });

    }


    private void initial() {
        recyclerView = (RecyclerView) findViewById(R.id.wishlist_view_items_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadItems();
    }


    ArrayList<WishListItem>  items ;

    private void loadItems() {
        items = new ArrayList<>() ;
        JSONObject params = new JSONObject() ;

        final ProgressDialog progressDialog = new ProgressDialog(this) ;
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.loadingMessage));
        progressDialog.show();
        try {
            User user = UserLogInManager.isLoggedIn() ;
            params.put("userId" , ""+user.getId()) ;
            Currency currency = CurrencyManager.getCurrency(this) ;
            params.put("currency" , currency.getCurrencyCode()+"") ;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(
                Request.Method.POST
                , AccessLinks.GET_ITEMS_FOR_WISHLIST
                , params
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("Items") ;
                    for (int i = 0  ; i< jsonItems.length() ; i++){
                        JSONObject jsonItem = jsonItems.getJSONObject(i) ;
                        WishListItem wishListItem = new WishListItem() ;
                        wishListItem.setColorId(jsonItem.getString("ITEM_COLOR_ID"));
                        wishListItem.setColorName(jsonItem.getString("ITEM_COLOR_NAME"));
                        wishListItem.setSizeId(jsonItem.getString("ITEM_SIZE_ID"));
                        wishListItem.setItemSize(jsonItem.getString("ITEM_SIZE"));
                        wishListItem.setImage(jsonItem.getString("PRODUCT_IMAGE"));

                        if(LanguageManager.isLangEn(activity)) {
                            wishListItem.setItemName(jsonItem.getString("ITEM_NAME"));
                        }else{
                            wishListItem.setItemName(jsonItem.getString("ITEM_AR_NAME"));//item name in english
                        }


                        wishListItem.setItemPrice(jsonItem.getString("PRICE"));
                        wishListItem.setItemId(jsonItem.getInt("ITEM_ID"));
                        wishListItem.setWishListId(jsonItem.getInt("WISHLIST_ID"));
                        items.add(wishListItem) ;
                    }
                    if(LanguageManager.isLangEn(activity)) {
                        numberOfProducts.setText(items.size() + " Products");
                    }else{
                        numberOfProducts.setText(" عدد العناصر "+items.size());
                    }

                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.dismiss();
                    recyclerView.setAdapter(new WishlistItemsAdapter(items , activity));
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    swipeRefreshLayout.setRefreshing(false);

                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
                Snackbar.make(getCurrentFocus()   , getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);

            }
        }
        ));

    }


    public void updateNumberOfProducts(int size){
        if(LanguageManager.isLangEn(this)) {
            numberOfProducts.setText(size + " Products");
        }else{
            numberOfProducts.setText(" عدد العناصر "+size);
        }
    }


    @Override
    public void setDirection(String lang) {

    }


}
