package com.company.alibaba.activities;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.entities.Item;
import com.company.alibaba.entities.User;
import com.company.alibaba.utils.CurrencyManager;
import com.company.alibaba.utils.Keys;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.utils.UserLogInManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FullSubCateActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener ,Languages {
    String subCateName, subCateId;
    TextView numberOfItems, cateName, cartItemNumberFullActivity;
    GridView GVItems;
    ArrayList<Item> items ;
    AppCompatActivity activity ;
    ItemsAdapter itemsAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_sub_cate);
        LanguageManager.setLanguage(this);

        activity = this  ;
        initial();
        loadCartNumber();

        ImageView cartIcon = (ImageView) findViewById(R.id.cartIconFullActivity);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1110 && resultCode == RESULT_OK) {
            User user = (User) data.getSerializableExtra("user");
            UserLogInManager.logIn(user);
            Intent intent = new Intent(this, MyCartActivity.class);
            startActivity(intent);
        }

        if (requestCode == 1210 && resultCode == RESULT_OK) {
            loadCartNumber();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initial() {
        Bundle bundle = getIntent().getExtras();
        subCateName = bundle.getString(Keys.SUB_CATE_NAME + "");
        subCateId = bundle.getString(Keys.SUB_CATE_ID + "");
        numberOfItems = (TextView) findViewById(R.id.numberOfItems);
        cateName = (TextView) findViewById(R.id.cateName);

        loadItems(subCateId) ;
        cartItemNumberFullActivity = (TextView) findViewById(R.id.cartItemsNumberFullActivity);

        findViewById(R.id.fullActivityErrorPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCartNumber();
                loadItems(subCateId);
            }
        });





        if(Integer.parseInt(cartItemNumberFullActivity.getText().toString()) <= 0){
            cartItemNumberFullActivity.setVisibility(View.INVISIBLE);
        }
        cateName.setText(subCateName);
        GVItems = (GridView) findViewById(R.id.itemsGrid);
        GVItems.setAdapter(new ItemsAdapter());

        itemsAdapter = new ItemsAdapter() ;
        GVItems.setAdapter(itemsAdapter);


    }


    private void loadItems(String subCateId) {
        items = new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage(getResources().getString(R.string.loadingMessage));
        pd.show();
        JSONObject params = new JSONObject();
        try {
            params.put("subCategoryId", subCateId + "");
            Currency currency = CurrencyManager.getCurrency(this) ;
            params.put("currency" , currency.getCurrencyCode()+"") ;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppController.getInstance().addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST , AccessLinks.GET_ITEMS_BY_SUB_CATE, params
                        , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("Items") ;
                            for (int i = 0; i < jsonItems.length(); i++) {
                            JSONObject jsonItem = jsonItems.getJSONObject(i) ;
                            Item item = new Item() ;
                            item.setId(jsonItem.getInt("ITEM_ID"));


                            if(LanguageManager.isLangEn(activity)) {
                                item.setTitle(jsonItem.getString("ITEM_TITLE"));
                            }else{
                                item.setTitle(jsonItem.getString("ITEM_AR_TITLE"));/////title in arabic
                            }


                             item.setImagePath(jsonItem.getString("PRODUCT_IMAGE"));
                             item.setPrice(jsonItem.getString("ITEM_PRICE"));
                             item.setOldPrice(jsonItem.getString("ITEM_OLD_PRICE"));
                             item.setRating(jsonItem.getInt("ITEM_RATE"));
                             items.add(item) ;
                            }
                            itemsAdapter.notifyDataSetChanged();
                            numberOfItems.setText(items.size() + " " + getResources().getString(R.string.products));
                            pd.dismiss();
                            findViewById(R.id.fullActivityErrorPage).setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pd.dismiss();
                        }
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(findViewById(R.id.fullActivityErrorPage) , getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                        error.printStackTrace();
                        pd.dismiss();
                        findViewById(R.id.fullActivityErrorPage).setVisibility(View.VISIBLE);
                    }
                }

                )
        );
    }




    public void loadCartNumber() {
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
                                    cartItemNumberFullActivity.setText(response.getInt("CART_ITEM_NUMBER")+"");
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
        if (Integer.parseInt(cartItemNumberFullActivity.getText().toString())>0){
            cartItemNumberFullActivity.setVisibility(View.VISIBLE);
        }else{
            cartItemNumberFullActivity.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    protected void onResume() {
        loadCartNumber() ;
        super.onResume();
    }

    @Override
    public void setDirection(String lang) {

    }

    class ItemsAdapter extends BaseAdapter {
        public ItemsAdapter() {
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        private void favourAnimation(View view) {
            Animation animation;
            ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationY", -100f, 0f);
            animY.setDuration(1000);//1.5sec
            animY.setInterpolator(new BounceInterpolator());
            animY.setRepeatCount(0);
            animY.start();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.sub_cat_item_view_big, null);
            TextView title = (TextView) view.findViewById(R.id.fullItemTitle);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.fullRatingBar);
            Button addToFaFavorite = (Button) view.findViewById(R.id.addToFavorite);
            TextView price = (TextView) view.findViewById(R.id.itemPriceLeft);

            TextView oldPrice = (TextView) view.findViewById(R.id.itemPriceRight);
            oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            price.setText(items.get(position).getPrice(activity));
            oldPrice.setText(items.get(position).getOldPrice(activity));

            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.subCateItemViewBigProgressBar);

            ImageView fullItemPic = (ImageView) view.findViewById(R.id.fullItemPic);
            fullItemPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity , ItemActivity.class) ;
                    Bundle data = new Bundle() ;
                    data.putSerializable("item" , items.get(position));
                    intent.putExtras(data) ;
                    activity.startActivity(intent);
                }
            });

            Picasso.get()
                    .load(AccessLinks.PICTURES_PREFIX+items.get(position).getImagePath())
                    .resize(250 , 310)
                    .into(fullItemPic, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }
                        @Override
                        public void onError(Exception e) {

                        }
                    });
            favourAnimation(addToFaFavorite);


            title.setText(items.get(position).getTitle());
            ratingBar.setRating(Float.valueOf(items.get(position).getRating()));

            return view;
        }

    }




    public void sortItems(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);// to implement on click event on items of menu
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.sorting_menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort1:

                Collections.sort(items, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return  Integer.valueOf(o2.getRating()).compareTo(Integer.valueOf(o1.getRating())) ;
                    }
                });
                itemsAdapter.notifyDataSetChanged();


                break;
            case R.id.sort2:
                Collections.sort(items, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return  Integer.valueOf(o2.getId()).compareTo(Integer.valueOf(o1.getId())) ;
                    }
                });
                itemsAdapter.notifyDataSetChanged();


                break;
            case R.id.sort3:

                Collections.sort(items, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return  String.valueOf(o1.getPrice(null)).compareTo(String.valueOf(o2.getPrice(null))) ;
                    }
                });
                itemsAdapter.notifyDataSetChanged();

                break;
            case R.id.sort4:

                Collections.sort(items, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return  String.valueOf(o2.getPrice(null)).compareTo(String.valueOf(o1.getPrice(null))) ;
                    }
                });
                itemsAdapter.notifyDataSetChanged();

                break;
        }
        return true;
    }
}
