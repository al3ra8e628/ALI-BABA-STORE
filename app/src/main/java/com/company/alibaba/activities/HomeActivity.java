package com.company.alibaba.activities;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.User;
import com.company.alibaba.homeFragments.CategoriesFragment;
import com.company.alibaba.homeFragments.HomeFragment;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.utils.UserLogInManager;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.json.JSONException;
import org.json.JSONObject;
public class HomeActivity extends AppCompatActivity implements Languages {
    public ViewPager viewPager;
    TextView loginRegisterTextView;
    NavigationView navBar;
    AppCompatActivity activity;

    public FragmentPagerItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_home);
        activity = this;
        initial();

        User user = UserLogInManager.isLoggedIn();
        if (user != null) {
            if(LanguageManager.isLangEn(this)) {
                loginRegisterTextView.setText("Welcome " + user.getUsername());
            }else{
                loginRegisterTextView.setText(user.getUsername()+" اهلا مجددا ");
            }

            loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            loadCartNumber();
        }
        ImageView cartIcon = (ImageView) findViewById(R.id.cartIconHome);
        cartIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (UserLogInManager.isLoggedIn() == null) {
                Intent intent1 = new Intent(activity, YouMustLogIn.class);
                intent1.putExtra("requestActivity", "1");
                startActivityForResult(intent1, 1110);
            } else {
                Intent intentCart = new Intent(activity, MyCartActivity.class);
                startActivityForResult(intentCart ,1210);
            }
        }
    });
}
    private void initial() {
        initialViews();
        initialNavigationBar();
    }

    private void initialViews() {
        navBar = (NavigationView) findViewById(R.id.navBar);

        viewPager = (ViewPager) findViewById(R.id.myViewPager);

        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.tab_text_1, HomeFragment.class)
                .add(R.string.tab_text_2, CategoriesFragment.class)
                .create());
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabLayout);
        viewPagerTab.setViewPager(viewPager);
    }

    private void initialNavigationBar() {
        navBar.bringToFront();
        navBar.requestLayout();
        navBar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.myCart:
                        if (UserLogInManager.isLoggedIn() == null) {
                            Intent intent1 = new Intent(activity, YouMustLogIn.class);
                            intent1.putExtra("requestActivity", "1");
                            startActivityForResult(intent1, 1110);
                        } else {
                            Intent intentCart = new Intent(activity, MyCartActivity.class);
                            startActivityForResult(intentCart , 1210);
                        }
                        break;
                    case R.id.myWishlist:
                        if (UserLogInManager.isLoggedIn() == null) {
                            Intent intent1 = new Intent(activity, YouMustLogIn.class);
                            intent1.putExtra("requestActivity", "2");
                            startActivityForResult(intent1, 1120);
                        } else {
                            intent = new Intent(activity, MyWishListActivity.class);
                        }
                        break;
                    case R.id.myOrders:
                        if (UserLogInManager.isLoggedIn() == null) {
                            Intent intent1 = new Intent(activity, YouMustLogIn.class);
                            intent1.putExtra("requestActivity", "3");
                            startActivityForResult(intent1, 1130);
                        } else {
                            intent = new Intent(activity, MyOrders.class);
                        }
                        break;


                    case R.id.lottery:
                        if (UserLogInManager.isLoggedIn() == null) {
                            Intent intent1 = new Intent(activity, YouMustLogIn.class);
                            intent1.putExtra("requestActivity", "7");
                            startActivityForResult(intent1, 3526);
                        } else {
                            intent = new Intent(activity,MyLotteries.class);
                        }
                        break;

                    case R.id.setCurrency:
                        Intent intent1 = new Intent(activity, SetCurrency.class);
                        startActivityForResult(intent1, 123);
                        break;
                    case R.id.setLanguage:
                        intent = new Intent(activity, SelectLangActivity.class);
                        startActivityForResult(intent , 2222);
                        intent = null ;
                        break;
                    case R.id.aboutUs:
                        intent = new Intent(activity, AboutUsActivity.class);
                        break;
                    case R.id.contactUs:
                        intent = new Intent(activity, ContactUs.class);
                        break;
                    case R.id.policy:
                        intent = new Intent(activity, Policy.class);
                        break;
                }
                if (intent != null)
                    startActivity(intent);
                return true;
            }
        });

        loginRegisterTextView = (TextView) navBar.getHeaderView(0).findViewById(R.id.loginRegisterTextView);
        loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivityForResult(intent, 1233);
            }
        });

    }

    public void changeNavState(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(Gravity.START);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1233 && resultCode == RESULT_OK) {
            User user = (User) data.getSerializableExtra("user");
            UserLogInManager.logIn(user);
            if(LanguageManager.isLangEn(this)) {
                loginRegisterTextView.setText("Welcome " + user.getUsername());
            }else{
                loginRegisterTextView.setText(user.getUsername()+" اهلا مجددا ");
            }
            loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if (requestCode == 1110 && resultCode == RESULT_OK) {
            User user = (User) data.getSerializableExtra("user");
            UserLogInManager.logIn(user);
            if(LanguageManager.isLangEn(this)) {
                loginRegisterTextView.setText("Welcome " + user.getUsername());
            }else{
                loginRegisterTextView.setText(user.getUsername()+" اهلا مجددا ");
            }
            loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            Intent intent = new Intent(this, MyCartActivity.class);
            startActivity(intent);
        }

        if (requestCode == 3526 && resultCode == RESULT_OK) {
            User user = (User) data.getSerializableExtra("user");
            UserLogInManager.logIn(user);
            if(LanguageManager.isLangEn(this)) {
                loginRegisterTextView.setText("Welcome " + user.getUsername());
            }else{
                loginRegisterTextView.setText(user.getUsername()+" اهلا مجددا ");
            }
            loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            Intent intent = new Intent(this, MyLotteries.class);
            startActivity(intent);
        }

        if (requestCode == 1120 && resultCode == RESULT_OK) {
            User user = (User) data.getSerializableExtra("user");
            UserLogInManager.logIn(user);
            if(LanguageManager.isLangEn(this)) {
                loginRegisterTextView.setText("Welcome " + user.getUsername());
            }else{
                loginRegisterTextView.setText(user.getUsername()+" اهلا مجددا ");
            }
            loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            Intent intent = new Intent(this, MyWishListActivity.class);
            startActivity(intent);
        }
        if (requestCode == 1130 && resultCode == RESULT_OK) {
            User user = (User) data.getSerializableExtra("user");
            UserLogInManager.logIn(user);
            if(LanguageManager.isLangEn(this)) {
                loginRegisterTextView.setText("Welcome " + user.getUsername());
            }else{
                loginRegisterTextView.setText(user.getUsername()+" اهلا مجددا ");
            }
            loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            Intent intent = new Intent(this, MyOrders.class);
            startActivity(intent);
        }
        if (requestCode == 1210 && resultCode == RESULT_OK) {
            loadCartNumber();
        }
        if (requestCode == 2222 && resultCode == RESULT_OK) {
            this.finish();
        }

        if (requestCode == 123 && resultCode == RESULT_OK) {
            Intent intent = new Intent(this , HomeActivity.class) ;
            startActivity(intent);
            this.finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //LanguageManager.setLanguage(this);
        User user = UserLogInManager.isLoggedIn();
        if (user != null) {
            if(LanguageManager.isLangEn(this)) {
                loginRegisterTextView.setText("Welcome " + user.getUsername());
            }else{
                loginRegisterTextView.setText(user.getUsername()+" اهلا مجددا ");
            }
            loginRegisterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            loadCartNumber() ;
        }


    }


    public void loadCartNumber() {
        final TextView numberOfCartItems = (TextView) findViewById(R.id.cartItemsNumberHome);
        User user = UserLogInManager.isLoggedIn();
        if (user != null) {
            JSONObject params = new JSONObject();
            try {
                params.put("userId", ""+user.getId());
                AppController.getInstance().addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.POST
                                , AccessLinks.GET_NUM_CART_ITEMS
                                , params
                        , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    numberOfCartItems.setText(response.getInt("CART_ITEM_NUMBER")+"");
                                    checkOnCartItemsNumber() ;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                            }
                        }
                        )
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkOnCartItemsNumber() {
        TextView numberOfCartItems = (TextView) findViewById(R.id.cartItemsNumberHome);
        if (Integer.parseInt(numberOfCartItems.getText().toString())>0){
            numberOfCartItems.setVisibility(View.VISIBLE);
        }else{
            numberOfCartItems.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(0);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public void setDirection(String lang) {

    }





}
