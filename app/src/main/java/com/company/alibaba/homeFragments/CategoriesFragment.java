package com.company.alibaba.homeFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.activities.FullSubCateActivity;
import com.company.alibaba.activities.HomeActivity;
import com.company.alibaba.adapters.ItemsAdapter;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.Item;
import com.company.alibaba.entities.SubCategory;
import com.company.alibaba.entities.SuperCategory;
import com.company.alibaba.utils.Keys;
import com.company.alibaba.utils.LanguageManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;

public class CategoriesFragment extends Fragment {

    public CategoriesFragment(){
    }

    CategoriesFragment me ;
    ListView LVSuperCategories ;
    LinearLayout LVSubCategories ;
    ArrayList<SubCategory> subCategories ;
    ArrayList<Item>  items = new ArrayList<>() ;
    ArrayList<SuperCategory> storeList ;
    SuperCategoryAdapter superCategoryAdapter ;

    ImageView errorPage ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_fragment, container, false) ;
        me = this ;
        initial(view) ;
        errorPage = (ImageView) view.findViewById(R.id.categoryFragmentErrorPage);
        errorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFullData();
                HomeFragment homeFragment = (HomeFragment) ((HomeActivity)getActivity()).adapter.getPage(0);
                homeFragment.loadHomeFragmentData();
            }
        });
        return view  ;
    }

    public void getFullData() {
        storeList = new ArrayList<>() ;
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage(getResources().getString(R.string.loadingMessage));
        pd.setCancelable(false);
        pd.show() ;
            AppController.getInstance().addToRequestQueue(new JsonObjectRequest(
                            Request.Method.GET
                            , AccessLinks.GET_HOME_CATEGORIES_DATE
                            , new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONArray store = response.getJSONArray("STORE") ;
                                        for (int i = 0 ; i < store.length() ; i++){
                                            JSONObject superCate = store.getJSONObject(i) ;
                                            SuperCategory superCategory = new SuperCategory() ;
                                            superCategory.setCategoryId(superCate.getInt("CATEGORY_ID"));

                                            if(LanguageManager.isLangEn((AppCompatActivity) getActivity())){
                                                superCategory.setCategoryName(superCate.getString("CATEGORY_TITLE"));
                                            }else{
                                                superCategory.setCategoryName(superCate.getString("CATEGORY_AR_TITLE"));//super cate AR
                                            }

                                            JSONArray jsonSubCategories = superCate.getJSONArray("SUB_CATEGORIES") ;
                                            for (int j = 0; j < jsonSubCategories.length() ; j++) {
                                                JSONObject subCate = jsonSubCategories.getJSONObject(j) ;
                                                SubCategory subCategory = new SubCategory() ;
                                                subCategory.setId(subCate.getInt("SUB_CATEGORY_ID"));

                                                if(LanguageManager.isLangEn((AppCompatActivity) getActivity())){
                                                    subCategory.setName(subCate.getString("SUB_CATEGORY_TITLE"));
                                                }else{
                                                    subCategory.setName(subCate.getString("SUB_CATEGORY_AR_TITLE"));//sub cate AR
                                                }

                                                subCategory.setSuperCategoryId(superCategory.getCategoryId());
                                                JSONArray jsonItems = subCate.getJSONArray("ITEMS");
                                                for (int k = 0; k < jsonItems.length() ; k++) {
                                                    JSONObject jsonItem = jsonItems.getJSONObject(k);
                                                    Item item = new Item();
                                                    item.setId(jsonItem.getInt("ITEM_ID"));

                                                    if(LanguageManager.isLangEn((AppCompatActivity) getActivity())){
                                                        item.setTitle(jsonItem.getString("ITEM_TITLE"));
                                                    }else{
                                                        item.setTitle(jsonItem.getString("ITEM_AR_TITLE"));//item title AR
                                                    }

                                                    item.setSubCatId(subCategory.getId());
                                                    item.setSuperCatId(superCategory.getCategoryId());
                                                    item.setRating(jsonItem.getInt("ITEM_RATE"));
                                                    item.setImagePath(jsonItem.getString("ITEM_PICTURE"));
                                                    subCategory.addToItems(item);
                                                }
                                            if(subCategory.getItems().size() > 0)
                                                superCategory.addToSubCategories(subCategory);
                                            }
                                            if(superCategory.getSubCategories().size() > 0)
                                                storeList.add(superCategory);
                                        }
                                        errorPage.setVisibility(View.GONE);
                                        if(store.length() > 0) {
                                            fillDataIntoView(pd);
                                        }else{
                                            pd.dismiss();
                                        }
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
                                    Snackbar.make(getView() , getResources().getString(R.string.ConnectionErrorText) , LENGTH_SHORT).show();
                                    errorPage.setVisibility(View.VISIBLE);
                                }
                            }
                    )) ;
    }
    private void fillDataIntoView(ProgressDialog pd) {
     superCategoryAdapter = new SuperCategoryAdapter() ;
        LVSuperCategories.setAdapter(superCategoryAdapter);
        if(storeList.size() > 0) {
            storeList.get(0).setSelected(true);
            subCategories = storeList.get(0).getSubCategories();
            fillSubCategory();
        }
        pd.dismiss();

    }

    class SuperCategoryAdapter extends BaseAdapter {
        public SuperCategoryAdapter(){}
        @Override
        public int getCount() {
            return storeList.size();
        }
        @Override
        public Object getItem(int position) {
            return storeList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.super_category_view , null) ;
            final TextView superCategoryName = (TextView) view.findViewById(R.id.superCategoryName);
            superCategoryName.setText(storeList.get(position).getCategoryName());
            final ImageView bg = (ImageView) view.findViewById(R.id.superCategoryContainer);
            storeList.get(position).setBg(bg);
            storeList.get(position).setCategoryTextView(superCategoryName);

            if(storeList.get(position).isSelected()){
                superCategoryName.setTextColor(getResources().getColor(R.color.selectedSuperCategoryTextColor));
                superCategoryName.setTypeface(superCategoryName.getTypeface() , Typeface.BOLD);

                if(LanguageManager.isLangEn((AppCompatActivity)getActivity())) {
                    bg.setImageResource(R.drawable.selected_category_bg);
                }else{
                    bg.setImageResource(R.drawable.ar_selected_category_bg);
                }

                bg.setScaleType(ImageView.ScaleType.FIT_XY);
            }

            bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < storeList.size() ; i++) {
                        SuperCategory category = storeList.get(i);
                        category.setSelected(false);
                        if (category.getBg() != null && category.getCategoryTextView() != null) {
                            category.getCategoryTextView().setTextColor(getResources().getColor(R.color.superCategoryTextColor));
                            category.getCategoryTextView().setTypeface(category.getCategoryTextView().getTypeface(), Typeface.NORMAL);
                            category.getBg().setImageResource(R.drawable.un_selected_category_bg);
                            bg.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                    }

                    SuperCategory category = storeList.get(position);
                    category.setSelected(true);
                    superCategoryName.setTypeface(superCategoryName.getTypeface() , Typeface.BOLD);
                    superCategoryName.setTextColor(getResources().getColor(R.color.selectedSuperCategoryTextColor));


                    if(LanguageManager.isLangEn((AppCompatActivity)getActivity())) {
                        bg.setImageResource(R.drawable.selected_category_bg);
                    }else{
                        bg.setImageResource(R.drawable.ar_selected_category_bg);
                    }


                    bg.setScaleType(ImageView.ScaleType.FIT_XY);
                    subCategories = storeList.get(position).getSubCategories() ;
                    fillSubCategory();

                    superCategoryAdapter.notifyDataSetChanged();
                }
            });
            return view;
        }
    }

    public void fillSubCategory(){
        if(LVSubCategories.getChildCount()>0){
            LVSubCategories.removeAllViews();
        }
        for (final SubCategory category : subCategories){
            View view = getActivity().getLayoutInflater().inflate(R.layout.sub_category_view , null) ;
            TextView subCatName = (TextView) view.findViewById(R.id.subCategoryName);
            TextView showAll = (TextView) view.findViewById(R.id.showAllBtn);

            subCatName.setText(category.getName()) ;
            RecyclerView RVItems = (RecyclerView) view.findViewById(R.id.itemsRecycler);
            LinearLayoutManager layoutManager = null  ;

            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

            RVItems.setLayoutManager(layoutManager);
            RVItems.setItemAnimator(new DefaultItemAnimator());
            RVItems.setAdapter(new ItemsAdapter(category.getItems() ,((AppCompatActivity)getActivity())));
            showAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity() , FullSubCateActivity.class) ;
                    Bundle bundle = new Bundle() ;
                    bundle.putString(Keys.SUB_CATE_NAME+"" , category.getName()+"");
                    bundle.putString(Keys.SUB_CATE_ID+"" , category.getId()+"");
                    //bundle.putString(Keys.SUB_NUMBER_OF_ITEMS+"" , items.size()+"");
                    intent.putExtras(bundle) ;
                    startActivity(intent);
                }
            });
            LVSubCategories.addView(view);
            if(LanguageManager.isLangEn((AppCompatActivity)getActivity())) {

            }else{
                layoutManager.scrollToPosition(0);
            }
        }
    }

    private void initial(View view) {
        storeList = new ArrayList<>() ;
        subCategories = new ArrayList<>() ;
        LVSuperCategories = (ListView) view.findViewById(R.id.superCategories);
        LVSuperCategories.setVerticalScrollbarPosition(1);
        LVSubCategories = (LinearLayout) view.findViewById(R.id.subCategories);
        getFullData() ;
    }

}
