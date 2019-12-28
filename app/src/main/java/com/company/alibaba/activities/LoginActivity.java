package com.company.alibaba.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.company.alibaba.R;
import com.company.alibaba.entities.User;
import com.company.alibaba.loginFragments.LoginFrag;
import com.company.alibaba.loginFragments.RegisterFrag;
import com.company.alibaba.utils.Keys;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.views.NonSwipeableViewPager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.* ;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements Languages {
    RegisterFrag registerFrag ;
    public NonSwipeableViewPager viewPager ;

    CallbackManager callbackManager ;
    com.facebook.login.widget.LoginButton logInBtn ;


    void initializeFaceBookLogin(){
        callbackManager   = CallbackManager.Factory.create();
        logInBtn = new LoginButton(this) ;
        String EMAIL = "email";
        logInBtn.setReadPermissions(Arrays.asList(EMAIL));
    }


    public void FBCallBack(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                User user = new User();
                                user.setFirstName(object.getString("first_name"));
                                user.setLastName(object.getString("last_name"));
                                user.setEmail(object.getString("email"));
                                user.setGender(object.getString("gender"));
                                registerFrag.fillDataIntoViewFromFB(user , 1);

                                LoginManager.getInstance().logOut();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

//                        Toast.makeText(getApplicationContext() , object.toString() , Toast.LENGTH_SHORT).show();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields","email,first_name,last_name,gender");
                request.setParameters(parameters);
                request.executeAsync();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }
    public void logInWithFacBook(){
        logInBtn.callOnClick() ;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Keys.GOOGLE_LOGIN_CODE && resultCode == RESULT_OK) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data) ;


            Plus.PeopleApi.load(mGoogleApiClient, result.getSignInAccount().getId()).setResultCallback(new ResultCallback<People.LoadPeopleResult>() {
                @Override
                public void onResult(@NonNull People.LoadPeopleResult loadPeopleResult) {
                  int gender =  loadPeopleResult.getPersonBuffer().get(0).getGender() ;
                    user.setGender(gender);
                }
            });



            //Log.e("google token" , ""+result.getSignInAccount().getIdToken()) ;

//            person.getGender() ;

            String name[] = result.getSignInAccount().getDisplayName().split(" ") ;

            user.setFirstName(name[0]) ;
            user.setLastName(name[name.length-1]);
            user.setEmail(result.getSignInAccount().getEmail());
            registerFrag.fillDataIntoViewFromFB(user , 1);
            LoginManager.getInstance().logOut();
        }else{
            callbackManager.onActivityResult(requestCode , resultCode , data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void logInGoogle(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, Keys.GOOGLE_LOGIN_CODE);
    }

    User user ;
    GoogleApiClient mGoogleApiClient ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        LanguageManager.setLanguage(this);

        user = new User() ;
        viewPager = (NonSwipeableViewPager) findViewById(R.id.loginPagePager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        initializeFaceBookLogin();
        FBCallBack();



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN) , new Scope(Scopes.PLUS_ME))
                .requestEmail()
                .requestIdToken(getResources().getString(R.string.client_id_google_plus))
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext() , "fail" , Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .addScope(new Scope(Scopes.PLUS_ME))

                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .build();






        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        //updateUI(account);

        //com.google.android.gms.common.SignInButton googleLogIn = new SignInButton(getActivity()) ;


    /*  to generate the key @_@
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.company.alibaba", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

*/
    }

    @Override
    public void setDirection(String lang) {

    }


    class ViewPagerAdapter extends FragmentPagerAdapter{
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return  new LoginFrag();
                case 1 : return registerFrag =  new RegisterFrag();
                default: return  new LoginFrag() ;
            }

        }
        @Override
        public int getCount() {
            return 2;
        }
    }


    @Override
    public void onBackPressed() {
        setResult(0000);
        super.onBackPressed();
    }
}
