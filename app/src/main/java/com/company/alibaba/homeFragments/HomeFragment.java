package com.company.alibaba.homeFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.activities.HomeActivity;
import com.company.alibaba.adapters.HomeItemsAdapter;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.Currency;
import com.company.alibaba.entities.Item;
import com.company.alibaba.entities.SuperCategory;
import com.company.alibaba.utils.CurrencyManager;
import com.company.alibaba.utils.LanguageManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class HomeFragment extends Fragment {
    View me;
    ViewPager carousel;
    ImageView banner3;
    ImageView banner1;
    ImageView banner2;
    String[] carouselarr;
    ImageView errorPage;
    SuperCategoriesAdapterHome adapterSuperCategories ;


    public HomeFragment() {

    }

    Timer timer;

    GridView gridView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        me = view;

        errorPage = (ImageView) view.findViewById(R.id.errorPageHomeFragment);


        errorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHomeFragmentData();
                CategoriesFragment categoriesFragment = (CategoriesFragment) ((HomeActivity) getActivity()).adapter.getPage(1);
                categoriesFragment.getFullData();

            }
        });



        initial(view);
        gridView = (GridView) view.findViewById(R.id.superCategoriesGrid);
        gridView.setNumColumns(2);

        loadHomeFragmentData();


        return view;
    }


    private View.OnClickListener createClickLister(final String url){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        } ;
    }



    public void loadHomeFragmentData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.loadingMessage));
        progressDialog.show();

        AppController.getInstance().addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET
                        , AccessLinks.GET_HOME_ADS_INFO
                        , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonAds = response.getJSONArray("adversitements");
                            ArrayList<View> views = new ArrayList<>();
                            getSuperCatgegories();
                            views.add(HandleAdvertisements_1$2$3(jsonAds.getJSONObject(0), jsonAds.getJSONObject(1), jsonAds.getJSONObject(2)));
                            views.add(HandleAdvertisements_4(jsonAds.getJSONObject(3)));
                            views.add(HandleAdvertisements_6(jsonAds.getJSONObject(5)));
                            views.add(HandleAdvertisements_7(jsonAds.getJSONObject(6)));
                            views.add(HandleAdvertisements_8(jsonAds.getJSONObject(7)));
                            views.add(HandleAdvertisementsLottery(jsonAds.getJSONObject(10)));
                            views.add(HandleAdvertisementsAuctions(jsonAds.getJSONObject(11)));


                            progressDialog.dismiss();

                            for (View view : views) {
                                view.setFocusable(false);
                                view.setVisibility(View.VISIBLE);
                            }

                            carousel.setVisibility(View.INVISIBLE);
                            carousel.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        error.printStackTrace();
                        getView().findViewById(R.id.errorPageHomeFragment).setVisibility(View.VISIBLE);

                    }
                }
                )
        );
    }


    private View HandleAdvertisements_1$2$3(JSONObject ads1, JSONObject ads2, JSONObject ads3) throws JSONException {
        carouselarr = new String[]{
                AccessLinks.PICTURES_PREFIX + ads1.getString("SRC")
                , AccessLinks.PICTURES_PREFIX + ads2.getString("SRC")
                , AccessLinks.PICTURES_PREFIX + ads3.getString("SRC")

        };

        carousel = (ViewPager) getView().findViewById(R.id.carousel);
        carousel.setAdapter(new CustomPagerAdapter(getActivity().getApplicationContext(), carouselarr));
        timer = new Timer();

        final boolean[] lock = {true};
        final int[] count = {0};

        carousel.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                count[0] = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        carousel.setCurrentItem(0);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            carousel.requestFocus() ;
                            try {
                                if (lock[0]) {
                                    if (carousel.getCurrentItem() == carouselarr.length - 2)
                                        lock[0] = false;


                                    count[0]++;
                                    carousel.setCurrentItem(count[0]);
                                } else {
                                    if (carousel.getCurrentItem() == 1)
                                        lock[0] = true;


                                    count[0]--;
                                    carousel.setCurrentItem(count[0]);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    });

            }
        }, 500, 4000);
        return carousel;
    }


    private View HandleAdvertisements_4(final JSONObject jsonObject) throws JSONException {
        TextView line1 = (TextView) getView().findViewById(R.id.ads_4_line_1);
        TextView line2 = (TextView) getView().findViewById(R.id.ads_4_line_2);
        TextView line3 = (TextView) getView().findViewById(R.id.ads_4_line_3);
        TextView line4 = (TextView) getView().findViewById(R.id.ads_4_line_4);
        TextView line5 = (TextView) getView().findViewById(R.id.ads_4_line_5);
        TextView line6 = (TextView) getView().findViewById(R.id.ads_4_line_6);

        line1.setText("" + jsonObject.getString("TOP"));
        line2.setText("" + jsonObject.getString("MIDDLE"));
        line3.setText("" + jsonObject.getString("BOTTOM"));
        line4.setText("" + jsonObject.getString("TITLE"));
        line5.setText("" + jsonObject.getString("LARGE"));
        line6.setText("" + jsonObject.getString("TEXT"));

        ConstraintLayout banneLink = (ConstraintLayout) getView().findViewById(R.id.ads_4_link);

        String url = "" + jsonObject.getString("URL");
        if(!url.equals("#")){
        banneLink.setOnClickListener(createClickLister(url));
        }
        return banneLink;
    }

    private View HandleAdvertisements_6(JSONObject jsonObject) throws JSONException {
        ConstraintLayout constraintLayout = (ConstraintLayout) getView().findViewById(R.id.banner3Container);

        TextView line1 = (TextView) getView().findViewById(R.id.ads_3_title);
        TextView line2 = (TextView) getView().findViewById(R.id.ads_3_desc);
        line1.setText("" + jsonObject.getString("TITLE"));
        line2.setText("" + jsonObject.getString("TEXT"));

        banner3 = (ImageView) getView().findViewById(R.id.banner3);
        final ProgressBar banner3ProgressBar = (ProgressBar) getView().findViewById(R.id.banner3_ProgressBar);

        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX + jsonObject.getString("SRC"))
                //.resize(250 , 250)
                .into(banner3, new Callback() {
                    @Override
                    public void onSuccess() {
                        banner3ProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        String url = "" + jsonObject.getString("URL");
        if(!url.equals("#")) {
            constraintLayout.setOnClickListener(createClickLister(url));
        }
            return constraintLayout;
    }

    private View HandleAdvertisements_7(JSONObject jsonObject) throws JSONException {

        ConstraintLayout constraintLayout = (ConstraintLayout) getView().findViewById(R.id.banner1Container);

        TextView line1 = (TextView) getView().findViewById(R.id.ads_1_line_1);
        TextView line2 = (TextView) getView().findViewById(R.id.ads_1_line_2);
        line1.setText("" + jsonObject.getString("TITLE"));
        line2.setText("" + jsonObject.getString("LARGE"));

        banner1 = (ImageView) getView().findViewById(R.id.banner1);

        final ProgressBar banner1_progressBar = (ProgressBar) getView().findViewById(R.id.banner1_progressBar);
        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX + jsonObject.getString("SRC"))
                //.resize(175 , 100)
                .into(banner1, new Callback() {
                    @Override
                    public void onSuccess() {
                        banner1_progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        String url = "" + jsonObject.getString("URL");
        if(!url.equals("#")) {
            constraintLayout.setOnClickListener(createClickLister(url));
        }
        return constraintLayout;

    }

    private View HandleAdvertisements_8(JSONObject jsonObject) throws JSONException {
        ConstraintLayout constraintLayout = (ConstraintLayout) getView().findViewById(R.id.banner2Container);

        TextView line1 = (TextView) getView().findViewById(R.id.ads_2_line_1);
        TextView line2 = (TextView) getView().findViewById(R.id.ads_2_line_2);
        line1.setText("" + jsonObject.getString("TITLE"));
        line2.setText("" + jsonObject.getString("TEXT"));

        banner2 = (ImageView) getView().findViewById(R.id.banner2);
        final ProgressBar banner2_progressBar = (ProgressBar) getView().findViewById(R.id.banner2ProgressBar);
        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX + jsonObject.getString("SRC"))
                //.resize(175 , 100)
                .into(banner2, new Callback() {
                    @Override
                    public void onSuccess() {
                        banner2_progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        String url = "" + jsonObject.getString("URL");
        if(!url.equals("#")) {
            constraintLayout.setOnClickListener(createClickLister(url));
        }
        return constraintLayout;
    }


    private View HandleAdvertisementsLottery(final JSONObject jsonObject) throws JSONException {

        final ImageView lottery = (ImageView) getView().findViewById(R.id.lottery);



        String url = "" + jsonObject.getString("URL");
        if(!url.equals("#")) {
            lottery.setOnClickListener(createClickLister(url));
        }

        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX + jsonObject.getString("SRC"))
                //.resize(175 , 100)
                .into(lottery, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                      //  lottery.setImageResource(R.drawable.lottery);
                    }
                });



        return lottery;
    }


    private View HandleAdvertisementsAuctions(final JSONObject jsonObject) throws JSONException {
        final ImageView lottery = (ImageView) getView().findViewById(R.id.auctions);

        String url = "" + jsonObject.getString("URL");
        if(!url.equals("#")) {
            lottery.setOnClickListener(createClickLister(url));
        }

        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX + jsonObject.getString("SRC"))
                //.resize(175 , 100)
                .into(lottery, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        lottery.setImageResource(R.drawable.auction);
                    }
                });

        return lottery;
    }



    private void initial(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        ArrayList<Item> items = laodItemsForSunCategory();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new HomeItemsAdapter(items, ((AppCompatActivity) getActivity())));
    }

    class CustomPagerAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;
        String[] mResources;

        public CustomPagerAdapter(Context context, String[] mResources) {
            mContext = context;
            this.mResources = mResources;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ConstraintLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            final ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.progress_home_pager);

            Picasso.get()
                    .load(mResources[position])
                    .resize(600, 300)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ConstraintLayout) object);
        }
    }


    public ArrayList<Item> laodItemsForSunCategory() {
        //final ProgressDialog progressDialog = new ProgressDialog(getActivity()) ;
        //progressDialog.setMessage("loading...");
        //progressDialog.setCancelable(false);
        //progressDialog.show();
        final ArrayList<Item> items = new ArrayList<>();
        final JSONObject params = new JSONObject() ;
        try {
            Currency currency = CurrencyManager.getCurrency((AppCompatActivity) getActivity()) ;
            params.put("currency" , currency.getCurrencyCode()+"") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppController.getInstance().addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST
                        , AccessLinks.GET_LAST_ADDED_ITEMS
                        , params
                        , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray itemsJson = response.getJSONArray("Items");
                            for (int i = 0; i < itemsJson.length(); i++) {
                                JSONObject jsonItem = itemsJson.getJSONObject(i);
                                Item item = new Item();


                                if(LanguageManager.isLangEn((AppCompatActivity) getActivity())) {
                                    item.setTitle(jsonItem.getString("ITEM_TITLE"));
                                }else{
                                    item.setTitle(jsonItem.getString("ITEM_AR_TITLE"));////title in english
                                }


                                item.setPrice(jsonItem.getString("ITEM_PRICE"));
                                item.setOldPrice(jsonItem.getString("ITEM_OLD_PRICE"));
                                item.setRating(jsonItem.getInt("ITEM_RATE"));
                                item.setId(jsonItem.getInt("ITEM_ID"));
                                item.setImagePath(jsonItem.getString("PRODUCT_IMAGE"));
                                items.add(item);
                                //                progressDialog.dismiss();
                            }

                            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.homeRecyclerView);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(new HomeItemsAdapter(items, ((AppCompatActivity) getActivity())));
                            getView().findViewById(R.id.errorPageHomeFragment).setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
         //                 progressDialog.dismiss();

                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                               // progressDialog.dismiss();
                                getView().findViewById(R.id.errorPageHomeFragment).setVisibility(View.VISIBLE);
                            }
                        }
                )
        );


        return items;
    }

    ArrayList<SuperCategory> superCategories;

    public void getSuperCatgegories() {
        superCategories = new ArrayList<>() ;

        AppController.getInstance().addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET
                        , AccessLinks.GET_6_SUPER_CATEGORIES
                        , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {//CATEGORY_ID CATEGORY_SRC CATEGORY_TITLE
                            JSONArray jsonSuperCategories = response.getJSONArray("Categories") ;

                            for(int i = 0 ; i < jsonSuperCategories.length() ; i++){
                                JSONObject jsonTemp = jsonSuperCategories.getJSONObject(i) ;
                                SuperCategory superCategory = new SuperCategory() ;
                                superCategory.setCategoryId(jsonTemp.getInt("CATEGORY_ID"));
                                superCategory.setImagePath(jsonTemp.getString("CATEGORY_SRC"));

                                if(LanguageManager.isLangEn((AppCompatActivity) getActivity())) {
                                    superCategory.setCategoryName(jsonTemp.getString("CATEGORY_TITLE"));
                                }else{
                                    superCategory.setCategoryName(jsonTemp.getString("CATEGORY_AR_TITLE"));////super cate in AR
                                }
                                superCategories.add(superCategory) ;
                            }
                            gridView.setAdapter(new SuperCategoriesAdapterHome());

                            ArrayList<Item> items = laodItemsForSunCategory();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        getView().findViewById(R.id.errorPageHomeFragment).setVisibility(View.VISIBLE);
                    }
                }
                )
        );

    }


    class SuperCategoriesAdapterHome extends BaseAdapter {

        @Override
        public int getCount() {
            return superCategories.size();
        }

        @Override
        public Object getItem(int position) {
            return superCategories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.super_category_grid_item, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.superCategoryImage);
            final TextView title = (TextView) view.findViewById(R.id.superCategoryTitle);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.superCategoryImageProgressBar);


            title.setText(superCategories.get(position).getCategoryName());

            Picasso.get()
                    .load(AccessLinks.PICTURES_PREFIX+superCategories.get(position).getImagePath())
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoriesFragment categoriesFragment = (CategoriesFragment) ((HomeActivity) getActivity()).adapter.getPage(1);

                    ArrayList<SuperCategory> storeList = categoriesFragment.storeList ;

                    if(storeList.contains(superCategories.get(position))){

                        for (int i = 0; i < storeList.size() ; i++) {
                            SuperCategory category = storeList.get(i);
                            category.setSelected(false);
                            if (category.getBg() != null && category.getCategoryTextView() != null) {
                                category.getCategoryTextView().setTextColor(getResources().getColor(R.color.superCategoryTextColor));
                                category.getCategoryTextView().setTypeface(category.getCategoryTextView().getTypeface(), Typeface.NORMAL);
                                category.getBg().setImageResource(R.drawable.un_selected_category_bg);
                                category.getBg().setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                        }
                        SuperCategory category = storeList.get(storeList.indexOf(superCategories.get(position)));
                        category.setSelected(true);
                        category.getCategoryTextView().setTypeface(category.getCategoryTextView().getTypeface() , Typeface.BOLD);
                        category.getCategoryTextView().setTextColor(getResources().getColor(R.color.selectedSuperCategoryTextColor));
                        category.getBg().setImageResource(R.drawable.selected_category_bg);
                        category.getBg().setScaleType(ImageView.ScaleType.FIT_XY);


                        if(storeList.size() >= position+1){
                            categoriesFragment.subCategories = storeList.get(position).getSubCategories() ;
                            categoriesFragment.fillSubCategory();
                            categoriesFragment.superCategoryAdapter.notifyDataSetChanged();
                        }

                    }
                        HomeActivity homeActivity =(HomeActivity) getActivity() ;
                        homeActivity.viewPager.setCurrentItem(1);

                }
            });


            view.setFocusable(false);
            imageView.setFocusable(false);
            title.setFocusable(false);
            progressBar.setFocusable(false);


            return view;
        }
    }


}
