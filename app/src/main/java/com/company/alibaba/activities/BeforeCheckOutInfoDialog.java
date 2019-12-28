package com.company.alibaba.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.PaymentInfo;
import com.company.alibaba.entities.User;
import com.company.alibaba.utils.Countries;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.utils.UserLogInManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeforeCheckOutInfoDialog extends AppCompatActivity implements Languages {

    final ArrayList<String> countriesTitle = new Countries().getCountries();
    final ArrayList<String> countriesIso3Code = new Countries().getCountryISO3AlphaCode();

    User user;

    TextView phoneNumber;
    Spinner  country;
    TextView city;
    TextView state;
    TextView address;
    TextView postalCode;

    AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_check_out_info_dialog);
        LanguageManager.setLanguage(this);
        activity = this;
        user = UserLogInManager.isLoggedIn();
        initial();
        //loadData();
    }

    private void initial() {
        phoneNumber = (TextView) findViewById(R.id.beforePhoneNumber);
        country = (Spinner) findViewById(R.id.beforeCountryName);
        city = (TextView) findViewById(R.id.beforeCityName);
        state = (TextView) findViewById(R.id.beforeState);
        address = (TextView) findViewById(R.id.beforeAddress);
        postalCode = (TextView) findViewById(R.id.beforePostalCode);

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                countriesTitle);
        country.setAdapter(spinnerArrayAdapter);

    }

    private void saveData(final User user) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loadingMessage));
        progressDialog.setCancelable(false);
        progressDialog.show();
        final PaymentInfo paymentInfo = new PaymentInfo();
        if (userInfoIsOkay()) {
            JSONObject params = new JSONObject();
            try {
                User temp = UserLogInManager.isLoggedIn();
                params.put("userId", "" + temp.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AppController.getInstance().addToRequestQueue(
                    new JsonObjectRequest(Request.Method.POST
                            , AccessLinks.GET_PAYMENT_INFO
                            , params
                            , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                paymentInfo.setAmount(response.getString("AMOUNT"));
                                paymentInfo.setCurrency(response.getString("CURRENCY"));
                                paymentInfo.setEmail(response.getString("EMAIL"));
                                paymentInfo.setOrderId(response.getString("ORDER_ID"));
                                paymentInfo.setProductsNames(response.getString("PRODUCTS_NAMES"));
                            } catch (JSONException e) {
                                progressDialog.dismiss();
                                e.printStackTrace();
                            }

                            Intent result = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("paymentInfo", paymentInfo);
                            bundle.putSerializable("checkoutInfo", user);
                            result.putExtras(bundle);
                            activity.setResult(RESULT_OK, result);
                            activity.finish();
                            progressDialog.dismiss();
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    progressDialog.dismiss();
                                    Snackbar.make(address, getResources().getString(R.string.ConnectionErrorText) + "", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                    )
            );

        }
        progressDialog.dismiss();
    }

    private boolean userInfoIsOkay() {
        boolean flag = true;
        if (phoneNumber.getText().toString().trim().isEmpty()) {
            phoneNumber.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false;
        }

        if (city.getText().toString().trim().isEmpty()) {
            city.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false;
        }

        if (state.getText().toString().trim().isEmpty()) {
            state.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false;
        }

        if (address.getText().toString().trim().isEmpty()) {
            address.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false;
        }

        if (postalCode.getText().toString().trim().isEmpty()) {
            postalCode.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false;
        }
        return flag;
    }


    /*private void fillViewWithData(User temp) {
        phoneNumber.setText((temp.getPhoneNumber().equals("null")?"":temp.getPhoneNumber()));
        country.setText((temp.getCountry().equals("null")?"":temp.getCountry()));
        zipCode.setText((temp.getZipCode().equals("null")?"":temp.getZipCode()));
        houseNum.setText((temp.getHouseNumber().equals("null")?"":temp.getHouseNumber()));
        streetNum.setText((temp.getStreetNumber().equals("null")?"":temp.getStreetNumber()));
        city.setText((temp.getCity().equals("null")?"":temp.getCity()));
    }*/


    @Override
    public void setDirection(String lang) {

    }

    public void saveInfoBeforeCheckOut(View view) {
        User user = UserLogInManager.isLoggedIn();
        user.setCity(city.getText().toString());
        user.setPhoneNumber(phoneNumber.getText().toString());
        user.setZipCode(postalCode.getText().toString());
        user.setStreetNumber(state.getText().toString());
        user.setHouseNumber(address.getText().toString());
        user.setCountry(countriesIso3Code.get(this.country.getSelectedItemPosition()));
        saveData(user);
    }


}
