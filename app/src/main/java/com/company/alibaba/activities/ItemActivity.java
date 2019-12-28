package com.company.alibaba.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.company.alibaba.entities.Color;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.entities.FullScreenPicture;
import com.company.alibaba.entities.Item;
import com.company.alibaba.entities.ItemPicture;
import com.company.alibaba.entities.Size;
import com.company.alibaba.entities.User;
import com.company.alibaba.utils.CurrencyManager;
import com.company.alibaba.utils.LanguageManager;
import com.company.alibaba.utils.Languages;
import com.company.alibaba.utils.UserLogInManager;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity implements Languages {
    AppCompatActivity activity ;
    ExpandableRelativeLayout expandableLayout ;
    ExpandableRelativeLayout expandableLayoutReview ;
    ImageView errorPage ;
    WebView desc ;
    LinearLayout itemPicturesContainer ;
    CollapsingToolbarLayout toolbarLayout ;
    RatingBar ratingBar ;
    TextView price2 ;
    Item item ;
    TextView price1 ;

    LinearLayout sizesPanel ;
    LinearLayout colorsPanel ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if(!LanguageManager.isLangEn(this)){
            toolbarLayout.setExpandedTitleGravity(Gravity.BOTTOM+Gravity.RIGHT);
            toolbarLayout.setCollapsedTitleGravity(Gravity.RIGHT);
        }

        errorPage = (ImageView) findViewById(R.id.itemPageError);
        initial() ;
        LanguageManager.setLanguage(this);
        TextView text = (TextView) findViewById(R.id.text);
        toggle(null);
        toggle(null);
        errorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = getIntent().getExtras() ;
                Item item = (Item) data.getSerializable("item");
                loadItemFullInfo(item.getId());
            }
        });



    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }

    public void goBack(View view) {
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void toggle(View view) {
        expandableLayout.toggle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void toggleReview(View view) {
        expandableLayoutReview.toggle();
    }


    private void fillPictures(int colorId) {
        if(itemPicturesContainer.getChildCount()> 0 ){
            itemPicturesContainer.removeAllViews();
        }

        final ArrayList<String> pictures = new ArrayList<>() ;

        for(int i = 0 ; i < item.getPictures().size() ; i++){
            if(item.getPictures().get(i).getColorId() == colorId){
                pictures.add(item.getPictures().get(i).getPicPath()) ;
            }

        }

        for(int i = 1 ; i < pictures.size() ; i++){
            View view = getLayoutInflater().inflate(R.layout.item_activity_item_image_view ,  null) ;
            final ImageView itemPic = (ImageView) view.findViewById(R.id.itemImagesInstance);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.item_activity_progressBar);

            final int finalI = i;
            itemPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FullScreenPicture picture = new FullScreenPicture() ;
                    picture.setHeight(itemPic.getHeight()+"");
                    picture.setWidth(itemPic.getWidth()+"");
                    picture.setUrl(AccessLinks.PICTURES_PREFIX+pictures.get(finalI));
                    Bundle bundle = new Bundle() ;
                    bundle.putSerializable("picture" , picture);
                    Intent intent = new Intent(activity , PictureFullScreen.class) ;
                    intent.putExtras(bundle) ;
                    startActivity(intent);
                }
            });

            if(i == pictures.size()-1){
                itemPic.setPaddingRelative(itemPic.getPaddingStart() , itemPic.getPaddingTop() , itemPic.getPaddingStart() , itemPic.getBottom());
            }
            itemPicturesContainer.addView(view);



            Picasso.get()
                    .load(AccessLinks.PICTURES_PREFIX+pictures.get(i))
                    //.resize(250 , 250)
                    //.centerCrop()
                    .into(itemPic, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }
                        @Override
                        public void onError(Exception e) {

                        }
                    });
        }
    }

    private void initial() {
        activity = this ;
        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        desc = (WebView) findViewById(R.id.descriptionWebView);
        ratingBar.setFocusable(false);
        desc.setFocusable(false);

        sizesPanel = (LinearLayout) findViewById(R.id.sizesPanel);
        colorsPanel = (LinearLayout) findViewById(R.id.colorsPanel) ;

        Bundle data = getIntent().getExtras() ;
        Item item = (Item) data.getSerializable("item");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(item.getTitle());
        toolbar.setTitleTextColor(getResources().getColor(R.color.tabs_text_color));
        setSupportActionBar(toolbar);

        price1 = (TextView) findViewById(R.id.item_activity_price_1);
        price2 = (TextView) findViewById(R.id.item_activity_price_2);
        price2.setPaintFlags(price2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        price2.setFocusable(false);

        desc.getSettings().setJavaScriptEnabled(true);

        itemPicturesContainer = (LinearLayout) findViewById(R.id.itemPicturesContainer);
        itemPicturesContainer.setFocusable(false);

        expandableLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        expandableLayout.setFocusable(false);

        expandableLayoutReview = (ExpandableRelativeLayout) findViewById(R.id.expandableLayoutReview);
        expandableLayoutReview.setFocusable(false);

        expandableLayout.setListener(new ExpandableLayoutListener() {
            ImageView icExpand = (ImageView) findViewById(R.id.iconExpand1);

            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
            }

            // You can get notification that your expandable layout is going to open or close.
            // So, you can set the animation synchronized with expanding animation.
            @Override
            public void onPreOpen() {
            }

            @Override
            public void onPreClose() {
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onOpened() {
                icExpand.setBackground(activity.getDrawable(R.drawable.ic_collapse));
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClosed() {
                icExpand.setBackground(activity.getDrawable(R.drawable.ic_expand));
            }
        });
        expandableLayoutReview.setListener(new ExpandableLayoutListener() {
            ImageView icExpand2 = (ImageView) findViewById(R.id.iconExpand2);

            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
            }

            @Override
            public void onPreOpen() {
            }

            @Override
            public void onPreClose() {
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onOpened() {
                icExpand2.setBackground(activity.getDrawable(R.drawable.ic_collapse));
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClosed() {
                icExpand2.setBackground(activity.getDrawable(R.drawable.ic_expand));
            }
        });

        loadItemFullInfo(item.getId());
    }

    private void loadItemFullInfo(final int id) {
        final ProgressDialog pd = new ProgressDialog(this) ;

        pd.setMessage(getResources().getString(R.string.loadingMessage));
        pd.show();

        final JSONObject params = new JSONObject() ;
        try {

            params.put("itemId" , id+"") ;
            Currency currency = CurrencyManager.getCurrency(this) ;
            params.put("currency" , currency.getCurrencyCode()+"") ;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppController.getInstance().addToRequestQueue(
        new JsonObjectRequest(Request.Method.POST, AccessLinks.GET_ITEM_INFO_BY_ID
        , params
        , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonItem) {
                try {
                item = new Item() ;
                item.setId(id);
                item.setOldPrice(jsonItem.getString("ITEM_OLD_PRICE"));
                item.setPrice(jsonItem.getString("ITEM_PRICE"));
                item.setRating(jsonItem.getInt("ITEM_RATE"));
                item.setInformation(jsonItem.getString("ITEM_DESC"));
                    JSONArray jsonItemSizes = jsonItem.getJSONArray("ITEM_SIZES") ;
                    for (int i = 0; i < jsonItemSizes.length() ; i++) {
                        JSONObject jsonSize = jsonItemSizes.getJSONObject(i) ;
                        Size size = new Size() ;
                        size.setSizeId(jsonSize.getInt("ID"));
                        size.setSizeName(jsonSize.getString("size"));
                        item.getSizes().add(size) ;
                    }
                    JSONArray jsonItemColors = jsonItem.getJSONArray("ITEM_COLORS") ;
                    for (int i = 0; i < jsonItemColors.length() ; i++) {
                        JSONObject jsonColor = jsonItemColors.getJSONObject(i) ;
                        Color color = new Color() ;
                        color.setColorId(jsonColor.getInt("ID"));
                        color.setColorName(jsonColor.getString("COLOR_NAME"));
                        color.setColor(jsonColor.getString("COLOR"));
                        item.getColors().add(color) ;
                    }
                    JSONArray jsonItemPictures = jsonItem.getJSONArray("ITEM_PICTURES") ;
                    for (int i = 0; i < jsonItemPictures.length() ; i++) {
                        JSONObject jsonPicture = jsonItemPictures.getJSONObject(i) ;
                        ItemPicture picture = new ItemPicture() ;
                        picture.setPicId(jsonPicture.getInt("PICTURE_ID"));
                        picture.setPicPath(jsonPicture.getString("PICTURE_FILE_NAME"));
                        picture.setColorId(jsonPicture.getInt("COLOR_ID"));
                        item.getPictures().add(picture) ;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
                }

                fillDataIntoView(item) ;
                if(item.getColors().size()> 0)
                fillPictures(item.getColors().get(0).getColorId());
                else{
                fillPictures(0);
                }
                pd.dismiss();
                errorPage.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorPage.setVisibility(View.VISIBLE);
                error.printStackTrace();
                pd.dismiss();
            }
        }
        )
        );
    }

    private void fillDataIntoView(final Item item) {
        ratingBar.setRating(item.getRating());
        price2.setText(item.getOldPrice(activity));
        price1.setText(item.getPrice(activity));

        desc.loadDataWithBaseURL("", item.getInformation()+"", "text/html", "UTF-8", "");

        if(item.getColors().size() > 0){
            item.setSelectedColor(item.getColors().get(0).getColorId());
            LinearLayout colorsContainer = (LinearLayout) findViewById(R.id.colorsContainer);
            if(colorsContainer.getChildCount() > 0){
                colorsContainer.removeAllViews();
            }

            for (int i = 0  ; i < item.getColors().size() ; i++){
                final View view = getLayoutInflater().inflate(R.layout.single_color_view , null) ;
                final TextView colorView = (TextView) view.findViewById(R.id.singleColorView);
                if(i == 0){
                    view.setBackground(getResources().getDrawable(R.color.selected_item_color_bg));
                }
                final int finalI = i;
                colorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fillPictures(item.getColors().get(finalI).getColorId());
                        item.setSelectedColor(item.getColors().get(finalI).getColorId());
                        for(Color color : item.getColors()){
                            color.getColorView().setBackground(getResources().getDrawable(R.color.activity_background));
                        }
                        view.setBackground(getResources().getDrawable(R.color.selected_item_color_bg));

                    }
                });
                colorView.setBackgroundColor(android.graphics.Color.parseColor(item.getColors().get(i).getColor()+""));
                item.getColors().get(i).setColorView(view);
                colorsContainer.addView(view);
            colorsPanel.setVisibility(View.VISIBLE);
            }
        }else{
            colorsPanel.setVisibility(View.GONE);
            item.setSelectedColor(0);
        }

        if(item.getSizes().size() > 0){
            item.setSelectedSize(item.getSizes().get(0).getSizeId());
            LinearLayout sizesContainer = (LinearLayout) findViewById(R.id.sizesContainer);
            if(sizesContainer.getChildCount() > 0){
                sizesContainer.removeAllViews();
            }
            for (int i = 0  ; i < item.getSizes().size() ; i++){
                View view = getLayoutInflater().inflate(R.layout.single_size_view , null) ;
                final TextView sizeView = (TextView) view.findViewById(R.id.singleSizeView);
                if(i == 0){
                    sizeView.setBackground(getResources().getDrawable(R.drawable.selected_size_bg));
                }
                final int finalI = i;
                sizeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            item.setSelectedSize(item.getSizes().get(finalI).getSizeId());
                            for(Size size : item.getSizes()){
                                size.getSizeView().setBackground(getResources().getDrawable(R.drawable.cart_view_bg));
                            }
                            sizeView.setBackground(getResources().getDrawable(R.drawable.selected_size_bg));
                        }
                    });
                sizeView.setText(item.getSizes().get(i).getSizeName());
                item.getSizes().get(i).setSizeView(sizeView);
                sizesContainer.addView(view);
            }
            sizesPanel.setVisibility(View.VISIBLE);
        }else{
            sizesPanel.setVisibility(View.GONE);
            item.setSelectedSize(0);
        }
    }


    public void addToCartItemActivity(View view) {
        //required user log in
        User user = userIsLoggedIn(4) ;
        if(user != null){
            JSONObject params = new JSONObject();
            try {
                params.put("PRODUCT_ID" , ""+item.getId()) ;
                params.put("SIZE_ID" , ""+item.getSelectedSize()) ;
                params.put("USER_ID" , ""+user.getId()) ;
                params.put("COLOR_ID" , ""+item.getSelectedColor()) ;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pd = new ProgressDialog(this) ;
            pd.setMessage(getResources().getString(R.string.addingToCart));
            pd.setCancelable(false);
            pd.show();

            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinateLayout);

            AppController.getInstance().addToRequestQueue(
                new JsonObjectRequest(Request.Method.POST, AccessLinks.ADD_TO_CART, params
                , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int res = response.getInt("response") ;
                            switch (res){
                                case 0 :
                                Snackbar snackbar = Snackbar.make(coordinatorLayout , "item already in cart", Snackbar.LENGTH_LONG);
                                snackbar.show();
                                    break;
                                case 1 :
                                Snackbar snackbar2 = Snackbar.make(coordinatorLayout , getResources().getString(R.string.AddedSuccessfully), Snackbar.LENGTH_LONG);
                                snackbar2.show();
                                    break;
                                case -1 :
                                Snackbar snackbar3 = Snackbar.make(coordinatorLayout ,getResources().getString(R.string.notAdded), Snackbar.LENGTH_LONG);
                                snackbar3.show();
                                    break;
                            }
                            pd.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pd.dismiss();
                        }


                    }
                }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        pd.dismiss();
                    }
                }
                )
            );
        }
    }


    public void addToWishListItemActivity(View view) {
        User user = userIsLoggedIn(5) ;
        if(user != null){
            JSONObject params = new JSONObject();
            try {
                params.put("PRODUCT_ID" , ""+item.getId()) ;
                params.put("SIZE_ID" , ""+item.getSelectedSize()) ;
                params.put("USER_ID" , ""+user.getId()) ;
                params.put("COLOR_ID" , ""+item.getSelectedColor()) ;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pd = new ProgressDialog(this) ;
            pd.setMessage(getResources().getString(R.string.addingToWish));
            pd.setCancelable(false);
            pd.show();

            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinateLayout);

            AppController.getInstance().addToRequestQueue(
                    new JsonObjectRequest(Request.Method.POST, AccessLinks.ADD_TO_WISHLIST, params
                            , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int res = response.getInt("response") ;
                                switch (res){
                                    case 0 :
                                        Snackbar snackbar = Snackbar.make(coordinatorLayout ,getResources().getString(R.string.ItemAlreadyInWishList), Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                        break;
                                    case 1 :
                                        Snackbar snackbar2 = Snackbar.make(coordinatorLayout , getResources().getString(R.string.AddedSuccessfully), Snackbar.LENGTH_LONG);
                                        snackbar2.show();
                                        break;
                                    case -1 :
                                        Snackbar snackbar3 = Snackbar.make(coordinatorLayout , getResources().getString(R.string.notAdded), Snackbar.LENGTH_LONG);
                                        snackbar3.show();
                                        break;
                                }
                                pd.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pd.dismiss();
                            }
                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            pd.dismiss();
                        }
                    }
                    )
            );
        }
    }

    public void reviewItem(View view) {
        //required user log in and at least one order (checking on the previous orders will be by the api)
         User user = userIsLoggedIn(6) ;
        if(user != null){
            RatingBar quality = (RatingBar) findViewById(R.id.qualityItemActivity);
            RatingBar price = (RatingBar) findViewById(R.id.priceItemActivity);
            RatingBar value = (RatingBar) findViewById(R.id.valueItemActivity);

            JSONObject params = new JSONObject();
            try {
                params.put("PRODUCT_ID" , ""+item.getId()) ;
                params.put("USER_ID" , ""+user.getId()) ;
                params.put("QUALITY" , ""+quality.getRating()) ;
                params.put("PRICE" , ""+price.getRating()) ;
                params.put("VALUE" , ""+value.getRating()) ;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog pd = new ProgressDialog(this) ;
            pd.setMessage(getResources().getString(R.string.addingReview));
            pd.setCancelable(false);
            pd.show();


            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinateLayout);
            AppController.getInstance().addToRequestQueue(
                    new JsonObjectRequest(Request.Method.POST, AccessLinks.ADD_REVIEW, params
                            , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int res = response.getInt("response") ;
                                switch (res){
                                    case -2 :
                                        Snackbar snackbar2 = Snackbar.make(coordinatorLayout ,getResources().getString(R.string.youMustHaveOrders), Snackbar.LENGTH_LONG);
                                        snackbar2.show();
                                        break;
                                    case -1 :
                                        Snackbar snackbar3 = Snackbar.make(coordinatorLayout , "you already reviewed this item", Snackbar.LENGTH_LONG);
                                        snackbar3.show();
                                        break;
                                    default:
                                        Snackbar snackbar = Snackbar.make(coordinatorLayout , getResources().getString(R.string.AddedSuccessfully), Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                        ratingBar.setRating(response.getInt("response"));
                                        break;
                                }
                                pd.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pd.dismiss();
                            }
                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            pd.dismiss();
                        }
                    }
                    )
            );
        }
    }

    private User userIsLoggedIn(int state) {
        User user = UserLogInManager.isLoggedIn() ;
        if(user != null){
            return  user ;
        }else{
            Intent intent = new Intent(this , YouMustLogIn.class) ;
            intent.putExtra("requestActivity" , ""+state) ;
            startActivityForResult(intent , state);
            return  null ;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 4){
                User user = (User) data.getSerializableExtra("user");
                UserLogInManager.logIn(user);
                addToCartItemActivity(null);
            }else if (requestCode == 5){
                User user = (User) data.getSerializableExtra("user");
                UserLogInManager.logIn(user);
                addToWishListItemActivity(null);
            }else if(requestCode == 6){
                User user = (User) data.getSerializableExtra("user");
                UserLogInManager.logIn(user);
                reviewItem(null);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setDirection(String lang) {
        TextView priceEn = (TextView) findViewById(R.id.itemActivityPriceEN);
        TextView priceAR = (TextView) findViewById(R.id.item_activity_price_text);

        if(LanguageManager.isLangEn(this)){
            priceEn.setVisibility(View.VISIBLE);
            priceAR.setVisibility(View.GONE);
        }else{
            priceEn.setVisibility(View.GONE);
            priceAR.setVisibility(View.VISIBLE);
        }

        if(lang.equals("ar")){
            TextView review = (TextView) findViewById(R.id.reviewTextView);
            TextView price = (TextView) findViewById(R.id.priceTextView);
            TextView quality = (TextView) findViewById(R.id.qualityTextView);
            TextView value = (TextView) findViewById(R.id.valueTextView);
            Button addReview = (Button) findViewById(R.id.addReviewBtn);
            review.setText("التقييم :");
            price.setText("السعر :");
            quality.setText("الجودة :");
            value.setText("القيمة :");
            addReview.setText("حفظ التقييم");
        }

    }
}

