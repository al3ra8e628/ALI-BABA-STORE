package com.company.alibaba.loginFragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.activities.LoginActivity;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.User;
import com.company.alibaba.utils.TextValidator;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginFrag extends Fragment {
    TextView username;
    TextView password;
    Button loginBtn;

    public LoginFrag() {
    }

    LoginFrag me;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_in_frag, container, false);
        me = this;
        initial(view);
        return view;
    }

    private void initial(View view) {
        username = (TextView) view.findViewById(R.id.emailOrUsername);
        password = (TextView) view.findViewById(R.id.passwordLog);
        loginBtn = (Button) view.findViewById(R.id.login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isFieldOkay()) {
                        final ProgressDialog progressDialog = new ProgressDialog(getActivity()) ;
                        progressDialog.setMessage(getResources().getString(R.string.loggingIn));
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        JSONObject params = new JSONObject();
                        if (isItEmail(username.getText().toString())) {
                            params.put("USERNAME", "");
                            params.put("EMAIL", "" + username.getText().toString());
                        } else {
                            params.put("USERNAME", "" + username.getText().toString());
                            params.put("EMAIL", "");
                        }
                        params.put("PASSWORD", "" + password.getText().toString());

                        AppController.getInstance().addToRequestQueue(
                            new JsonObjectRequest(
                            Request.Method.POST
                            , AccessLinks.LOG_IN
                            , params
                            , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                    if(!response.has("response")){
                                        User user = new User() ;
                                        try {
                                            user.setId(response.getInt("ID")+"");
                                            user.setFirstName(response.getString("FIRST_NAME"));
                                            user.setLastName(response.getString("LAST_NAME"));
                                            user.setEmail(response.getString("EMAIL"));
                                            user.setUsername(response.getString("USERNAME"));
                                            user.setGender(response.getInt("GENDER"));

                                            Intent intent = new Intent() ;
                                            Bundle bundle = new Bundle() ;
                                            bundle.putSerializable("user" , user);
                                            intent.putExtras(bundle) ;
                                            getActivity().setResult(Activity.RESULT_OK , intent);
                                            getActivity().finish();
                                            progressDialog.dismiss();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            progressDialog.dismiss();
                                        }
                                    }else{
                                        username.setError("invalid info");
                                        password.setError("invalid info");
                                        progressDialog.dismiss();
                                    }
                                }
                            }

                            , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                progressDialog.dismiss();
                                Snackbar.make(getView() , getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                }
                            }
                            )
                        );
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });


        TextView goToRegister = (TextView) view.findViewById(R.id.goToRegister);
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.viewPager.setCurrentItem(1);
            }
        });


    }

    private boolean isItEmail(String s) {
        return TextValidator.validateEmail(s) ;
    }

    public boolean isFieldOkay() {
        boolean flag = true ;
        if(username.getText().toString().equals("")){
            username.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
        }
        if(password.getText().toString().equals("")){
            password.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
        }
        return flag;
    }
/*
https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6ImE3NDhlOWY3NjcxNTlmNjY3YTAyMjMzMThkZTBiMjMyOWU1NDQzNjIifQ.eyJhenAiOiI5NjE0NDgwNDk5NzYtaTM4MXY1NzhkaDVqcWk2aTU2aWR2aWFscmM0cjgwb24uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI5NjE0NDgwNDk5NzYtaDFrbnNoam9xbGthMWNkdTZlZzNyaDlnYXFoMnZjYmguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDg4Mzk3MTIwMjQ2ODAyODY2ODEiLCJlbWFpbCI6InFhc2ltLmFsYmF5YXRpLjYyOEBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZXhwIjoxNTI2MzMxNTk0LCJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJpYXQiOjE1MjYzMjc5OTQsIm5hbWUiOiJRYXNpbSBBbGJheWF0aSIsInBpY3R1cmUiOiJodHRwczovL2xoNi5nb29nbGV1c2VyY29udGVudC5jb20vLXVfS0Z4SERWT0tJL0FBQUFBQUFBQUFJL0FBQUFBQUFBQUFBL0FJY2ZkWEFzc0FiVzJmZzNFUWJ5NVE4WVB1SmlrN01rNGcvczk2LWMvcGhvdG8uanBnIiwiZ2l2ZW5fbmFtZSI6IlFhc2ltIiwiZmFtaWx5X25hbWUiOiJBbGJheWF0aSIsImxvY2FsZSI6ImFyIn0.L1jsfUkMo63XR3oAyAt7lRde-CdJtlDPjRx471Zeer7vG74vnl-3dpqHu2BNW6hkg3U0EwKflWvH5MaiIDCcPuaVl7CeO-GHkG9k0zRYMfJgrNHpoHSVn858yj2srDALWAlFSRuLOUxCUxgHbqRzVJSqQbyzYqIu_CbPmR9mr0GZ2xUDZZW-Nav42ZAmgdVPQKjxUwV9yuWkZ9IGCnB3bHkY5iMHRgVSbt4UgQ4k3FwSRXX9UC3IQeXBUjP2ITKFUq2DK7iAhMa9BdXcACToqFT7Z0ublSZyMuJIyHHlV14jfYLVJUsUSo0aY-hTDGKmwp_jbIViynu5loSNrYMtQA


*/


}
