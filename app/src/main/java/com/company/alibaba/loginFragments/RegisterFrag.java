package com.company.alibaba.loginFragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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


public class RegisterFrag extends Fragment {
    Spinner genders ;
    Button facebookBtn ;
    Button googleBtn ;

    EditText firstName , lastName , email , username , password , cfmPassword  ;




    public RegisterFrag(){
    }

    RegisterFrag me ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_frag, container, false) ;
        me = this ;
        initial(view) ;

        Button signUp = (Button) view.findViewById(R.id.signUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFieldsOkay()){
                    if(isPasswordMatches()){
                    User user = new User() ;
                    user.setUsername(username.getText().toString());
                    user.setFirstName(firstName.getText().toString());
                    user.setLastName(lastName.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setGender(genders.getSelectedItemPosition());
                    user.setPassword(password.getText().toString());
                    signUp(user) ;
                    }else{
                        //some action indicate that password are not matched
                    }
                }
            }
        });

        return view  ;
    }

    private void signUp(final User user) {
        Log.e("user" , user.toString()) ;
        JSONObject params = new JSONObject() ;
        try {
            params.put("FIRST_NAME" , ""+user.getFirstName()) ;
            params.put("LAST_NAME" , ""+user.getLastName()) ;
            params.put("GENDER" , ""+user.getGender()) ;
            params.put("USERNAME" , ""+user.getUsername()) ;
            params.put("EMAIL" , ""+user.getEmail()) ;
            params.put("PASSWORD" , ""+user.getPassword()) ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ProgressDialog progressDialog = new ProgressDialog(getActivity()) ;
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.signingIn));
        progressDialog.show();

        AppController.getInstance().addToRequestQueue(
        new JsonObjectRequest(
        Request.Method.POST
        , AccessLinks.SIGN_UP
        , params
        , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("USER_ID")){
                    //user sign up successfully
                    try {
                        Intent intent = new Intent() ;
                        Bundle bundle = new Bundle() ;
                        user.setId(response.getInt("USER_ID")+"");
                        bundle.putSerializable("user" , user);
                        intent.putExtras(bundle) ;
                        getActivity().setResult(Activity.RESULT_OK , intent);
                        getActivity().finish();
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }else{
                    progressDialog.dismiss();
                    try {
                        switch (response.getInt("response")){
                            case -1 :
                                Snackbar.make(getView() , getResources().getString(R.string.userAlreadyRegister) , Snackbar.LENGTH_SHORT).show();
                                break;
                            case -2 :
                                username.setError(getResources().getString(R.string.userNameIsAlreadyEc));
                                break;
                            case -3 :
                                email.setError(getResources().getString(R.string.emailIsAlreadyWEx));
                                break;
                            case -4 :
                                Snackbar.make(getView() , getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Snackbar.make(getView() , getResources().getString(R.string.ConnectionErrorText), Snackbar.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }
        ));
    }

    private void initial(View view) {
        firstName = (EditText) view.findViewById(R.id.register_firstName);
        lastName = (EditText) view.findViewById(R.id.register_lastName);
        email = (EditText) view.findViewById(R.id.register_email);
        username = (EditText) view.findViewById(R.id.register_username);
        password = (EditText) view.findViewById(R.id.register_password);
        cfmPassword = (EditText) view.findViewById(R.id.register_cfm_password);
        googleBtn = (Button) view.findViewById(R.id.googleBtn);
        genders = (Spinner) view.findViewById(R.id.gender_spinner);
        TextView goToRegister = (TextView) view.findViewById(R.id.goToLogIn);
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.viewPager.setCurrentItem(0);
            }
        });

        genders.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext() , android.R.layout.simple_spinner_item , getResources().getStringArray(R.array.genders)));

        facebookBtn = (Button) view.findViewById(R.id.facebookBtn);

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity me = (LoginActivity) getActivity();
                me.logInWithFacBook();
            }
        });

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).logInGoogle();
            }
        });


    }


    public void fillDataIntoViewFromFB(User user , int state){
        if(state == 1){
            email.setText(user.getEmail());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            genders.setSelection(user.getGender());
        }else if(state == 2){



        }

    }

    public boolean isFieldsOkay() {
        boolean flag = true ;
        boolean returnFlag = false ;
        //EditText firstName , lastName , email , username , password , cfmPassword  ;
        if(firstName.getText().toString().equals("")){
            firstName.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
        }

        if(lastName.getText().toString().equals("")){
            lastName.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
        }

        if(email.getText().toString().equals("")){
            email.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
            returnFlag = true ;
        }

        if(username.getText().toString().equals("")){
            username.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
            returnFlag = true ;
        }

        if(password.getText().toString().equals("")){
            password.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
            returnFlag = true ;
        }

        if(cfmPassword.getText().toString().equals("")){
            cfmPassword.setError(getResources().getString(R.string.cannotBeEmpty));
            flag = false ;
            returnFlag = true ;
        }

        if(returnFlag){
            return false ;
        }

        if(!TextValidator.validateEmail(email.getText().toString())){
           flag = false ;
           email.setError(getResources().getString(R.string.incorrectEmail));
        }

        if(!TextValidator.validateUsername(username.getText().toString())){
            flag = false ;
            username.setError(getResources().getString(R.string.inccorrectUsername));
        }

        if(!TextValidator.validatePassword(password.getText().toString())){
            flag = false ;
            password.setError(getResources().getString(R.string.incorrectPassword));
        }

        return flag;
    }

    public boolean isPasswordMatches() {
        boolean flag = true ;
        if(!password.getText().toString().equals(cfmPassword.getText().toString())){
            flag = false ;
            password.setError(getResources().getString(R.string.notConfirmed));
            cfmPassword.setError(getResources().getString(R.string.notConfirmed));
        }
        return flag;
    }
}
