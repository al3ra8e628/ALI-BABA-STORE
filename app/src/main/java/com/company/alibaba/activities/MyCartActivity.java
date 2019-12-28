package com.company.alibaba.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.adapters.CartItemsAdapter;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.CartItem;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.entities.PaymentInfo;
import com.company.alibaba.entities.User;
import com.company.alibaba.utils.CurrencyManager;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.utils.UserLogInManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import paytabs.project.PayTabActivity;

//import paytabs.project.PayTabActivity;

public class MyCartActivity extends AppCompatActivity implements Languages {
    RecyclerView recyclerView ;
    MyCartActivity activity ;
    TextView totalPrice ;
    TextView numberOfItemsCart ;
    Double dTotalPrice ;
    SwipeRefreshLayout swipeRefreshLayout  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.cartSwipeRefresher);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
                upDateTotalPrice();
            }
        });


        if(LanguageManager.isLangEn(this)) {
            getSupportActionBar().setTitle("My Cart");
        }else{
            getSupportActionBar().setTitle("سلة المشتريات");
        }



        dTotalPrice = 0.0 ;
        activity = this ;
        initial() ;
        LanguageManager.setLanguage(this);


    }

    private void initial() {
        recyclerView = (RecyclerView) findViewById(R.id.cart_view_items_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        totalPrice = (TextView) findViewById(R.id.TotalPrice);
        numberOfItemsCart = (TextView) findViewById(R.id.numberOfItemsCart);
        loadItems() ;
    }

    ArrayList<CartItem>  items ;
    private void loadItems() {
        items = new ArrayList<>() ;
        CartItem.setTotalPrice("0");
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
                , AccessLinks.GET_ITEMS_FOR_CART
                , params
        , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("Items") ;
                    for (int i = 0  ; i< jsonItems.length() ; i++){
                        JSONObject jsonItem = jsonItems.getJSONObject(i) ;
                        CartItem cartItem = new CartItem() ;
                        cartItem.setCartId(jsonItem.getInt("CART_ID"));
                        cartItem.setColorName(jsonItem.getString("COLOR_NAME"));
                        cartItem.setItemPrice(jsonItem.getString("ITEM_PRICE"));
                        if(LanguageManager.isLangEn(activity)) {
                            cartItem.setItemTitle(jsonItem.getString("ITEM_TITLE"));
                        }else{
                            cartItem.setItemTitle(jsonItem.getString("ITEM_AR_TITLE"));////// title in arabic
                        }
                        cartItem.setProductImage(jsonItem.getString("PRODUCT_IMAGE"));
                        cartItem.setQuantity(jsonItem.getInt("QUANTITY"));
                        cartItem.setSizeName(jsonItem.getString("SIZE_NAME"));
                        CartItem.incrementTotal((Double.parseDouble(cartItem.getItemPrice(null))*cartItem.getQuantity())+"");
                        items.add(cartItem) ;
                    }
                    progressDialog.dismiss();
                    upDateTotalPrice() ;
                    recyclerView.setAdapter(new CartItemsAdapter(items , activity));
                    swipeRefreshLayout.setRefreshing(false);
                    } catch (JSONException e) {
                    e.printStackTrace();
                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.dismiss();
                }
            }
        }
        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
                Snackbar.make(getCurrentFocus()  , getResources().getString(R.string.ConnectionErrorText), Snackbar.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
        ));
    }

    public void checkOutAction(View view) {
        if(items.size()>0){
            Intent intent = new Intent(this , BeforeCheckOutInfoDialog.class) ;
            startActivityForResult(intent , 12);
        }else{
            Snackbar.make(getCurrentFocus() , getResources().getString(R.string.cartEmpty)  , Snackbar.LENGTH_SHORT).show();
            return;
        }
    }

    public void upDateTotalPrice() {
        DecimalFormat df = new DecimalFormat("#####.###");
        String total = df.format(Double.parseDouble(CartItem.getTotalPrice().toString())) ;

        Currency currency = CurrencyManager.getCurrency(activity) ;
        total = currency.getCurrencySymbol()+total ;


        if(LanguageManager.isLangEn(this)) {
            totalPrice.setText("Total Price : " + total);
        }else{
            total =  LanguageManager.NumberArToEn(total) ;
            totalPrice.setText("سعر الكل : "+total);
        }

        int number = 0 ;
        for(CartItem temp : items){
            number+=(temp.getQuantity()) ;
        }
        if(LanguageManager.isLangEn(this)){
            numberOfItemsCart.setText(number+" Products");
        }else{
            numberOfItemsCart.setText(" عدد العناصر "+number);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    @Override
    public void setDirection(String lang) {
        Button checkout = (Button) findViewById(R.id.button5);
        if(lang.equals("en")){
            checkout.setText("check out");
        }else{
            checkout.setText("شراء");
            totalPrice.setText("سعر الكل : 0");
            numberOfItemsCart.setText("عدد العناصر 0");
        }

    }

    String orderId = "" ;
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 12 && resultCode == RESULT_OK){
            User user = (User) data.getSerializableExtra("checkoutInfo");
            PaymentInfo paymentInfo = (PaymentInfo) data.getSerializableExtra("paymentInfo");
            orderId = paymentInfo.getOrderId() ;
            startPaymentActivity(user ,paymentInfo) ;
        }
        if(requestCode == 11){
            this.finish();
            /*


            SharedPreferences shared_prefs = getSharedPreferences("myapp_shared", MODE_PRIVATE);
            String pt_response_code = shared_prefs.getString("pt_response_code", "");
            String pt_transaction_id = shared_prefs.getString("pt_transaction_id", "");

            JSONObject params = new JSONObject() ;

            try {

                User user = UserLogInManager.isLoggedIn() ;
                params.put("USER_ID" , ""+user.getId()) ;
                params.put("RESPONSE_CODE" , ""+pt_response_code) ;
                params.put("TRANSACTION_ID" , ""+pt_transaction_id) ;
                params.put("ORDER_ID" , ""+orderId) ;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog progressDialog = new ProgressDialog(this) ;
            progressDialog.setMessage(getResources().getString(R.string.loadingMessage));
            progressDialog.setCancelable(false);
            progressDialog.show();
            AppController.getInstance().addToRequestQueue(
            new JsonObjectRequest(
                    Request.Method.POST,
                    AccessLinks.VERIFY_PAYMENT,
                    params,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int result = response.getInt("response") ;
                                switch (result){
                                    case 1 :
                                        Toast.makeText(getApplicationContext() , "Thank You, Your payment has been processed successfully." , Toast.LENGTH_LONG).show();
                                        break ;
                                    case -1 :
                                        Toast.makeText(getApplicationContext() , "Oops... Your Payment Not Complete!" , Toast.LENGTH_LONG).show();
                                        break ;
                                }
                                progressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
            )
            );

            */
        }
    }



    private void startPaymentActivity(User user , PaymentInfo paymentInfo) {
        Intent in = new Intent(this, PayTabActivity.class) ; // PayTabActivity.class);


        DecimalFormat df = new DecimalFormat("######.###");
        String amount = df.format(Double.parseDouble(paymentInfo.getAmount())) ;

        if(!LanguageManager.isLangEn(this)){
            amount = LanguageManager.NumberArToEn(amount) ;
        }

        //STATIC DATA
        in.putExtra("pt_merchant_email", "fm1.sabbah@gmail.com");
        in.putExtra("pt_secret_key", "U7YFcqj8CHvUgDI2R2wkJ02rXo41EuMzCKfPazXMgtHx7pzZs6jb7mD4KH6pcSZOCeDNNBA45hCdGbc9iwkpSXtCFA2Rs1G5fkS3");
        in.putExtra("pt_transaction_title", "transaction");
        in.putExtra("pt_timeout_in_seconds", "300"); //Optional
        in.putExtra("pt_shared_prefs_name", "myapp_shared");


        //API DATA
        in.putExtra("pt_amount", ""+amount);
        in.putExtra("pt_currency_code", ""+paymentInfo.getCurrency());
        in.putExtra("pt_customer_email", ""+paymentInfo.getEmail());
        in.putExtra("pt_order_id", ""+paymentInfo.getOrderId());
        in.putExtra("pt_product_name", ""+paymentInfo.getProductsNames());

        //CHECKOUT SCREEN DATA
        in.putExtra("pt_customer_phone_number", ""+user.getPhoneNumber());
        in.putExtra("pt_address_billing", ""+user.getHouseNumber());
        in.putExtra("pt_city_billing", ""+user.getCity());
        in.putExtra("pt_state_billing", ""+user.getStreetNumber());
        in.putExtra("pt_country_billing", ""+user.getCountry());
        in.putExtra("pt_postal_code_billing", ""+user.getZipCode());

        //CHECKOUT SCREEN DATA
        in.putExtra("pt_address_shipping", ""+user.getHouseNumber());
        in.putExtra("pt_city_shipping", ""+user.getCity());
        in.putExtra("pt_state_shipping", ""+user.getStreetNumber());
        in.putExtra("pt_country_shipping", ""+user.getCountry());
        in.putExtra("pt_postal_code_shipping", ""+user.getZipCode());

        int requestCode = 11;

        startActivityForResult(in, requestCode);
    }

}
