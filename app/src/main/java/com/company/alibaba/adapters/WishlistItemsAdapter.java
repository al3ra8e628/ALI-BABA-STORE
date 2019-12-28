package com.company.alibaba.adapters;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company.alibaba.R;
import com.company.alibaba.activities.MyWishListActivity;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.WishListItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WishlistItemsAdapter extends RecyclerView.Adapter<WishlistItemsAdapter.MyViewHolder> {
    private List<WishListItem> items;
    private MyWishListActivity activity;
    private WishlistItemsAdapter adapter ;
    public WishlistItemsAdapter(ArrayList<WishListItem> items, MyWishListActivity activity) {
        this.activity = activity;
        this.items = items;
        adapter = this;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTitle;
        public TextView itemPrice1;
        public ImageView itemPicture;
        public ImageView deleteButton;
        public ImageView addToCartButton;
        public TextView times;
        public TextView colorAndSize;
        public ImageView addMoreOne;
        public ImageView removeOne;
        public ProgressBar progressBar ;

        public MyViewHolder(View view) {
            super(view);
            itemTitle = (TextView) view.findViewById(R.id.cart_view_item_title);
            itemPrice1 = (TextView) view.findViewById(R.id.cart_view_item_price1);
            itemPicture = (ImageView) view.findViewById(R.id.cart_view_item_picture);
            deleteButton = (ImageView) view.findViewById(R.id.cart_view_item_remove);
            addToCartButton = (ImageView) view.findViewById(R.id.cart_view_item_add_to_cart);
            colorAndSize = (TextView) view.findViewById(R.id.cart_view_item_colorAndSize);

            times = (TextView) view.findViewById(R.id.times);
            addMoreOne = (ImageView) view.findViewById(R.id.addMoreOne);
            removeOne = (ImageView) view.findViewById(R.id.removeOne);
            progressBar = (ProgressBar) view.findViewById(R.id.cartViewProgressBar);
        }
    }

    @Override
    public WishlistItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_view_item, parent, false);
        return new WishlistItemsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final WishListItem item = items.get(position);
        holder.itemTitle.setText(item.getItemName());
        //holder.addToCartButton.setVisibility(View.INVISIBLE);
        holder.itemPrice1.setText(item.getItemPrice(activity));

        holder.addMoreOne.setVisibility(View.INVISIBLE);
        holder.removeOne.setVisibility(View.INVISIBLE);
        holder.times.setVisibility(View.INVISIBLE);

        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX+item.getImage())
                .resize(175, 175)
                .into(holder.itemPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        String sizeColor ;

        if(!item.getColorName().equals("none")){
            if(!item.getItemSize().equals("none")){
                sizeColor = item.getColorName()+","+item.getItemSize() ;
            }else{
                sizeColor = item.getColorName() ;
            }
        }else{
            if(!item.getItemSize().equals("none")){
                sizeColor = item.getItemSize() ;
            }else{
                sizeColor = "" ;
            }
        }

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject params = new JSONObject();

                try {
                    params.put("WISH_LIST_ID" , item.getWishListId()) ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final ProgressDialog progressDialog = new ProgressDialog(activity) ;
                progressDialog.setCancelable(false);
                progressDialog.setMessage(activity.getResources().getString(R.string.addingProgressMsg));
                progressDialog.show();

                AppController.getInstance().addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.POST
                                , AccessLinks.ADD_TO_CART_FROM_WISHLISTS
                                , params
                                , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    switch (response.getInt("response")){
                                        case 1 :
                                            items.remove(items.indexOf(item)) ;
                                            adapter.notifyDataSetChanged();
                                            activity.updateNumberOfProducts(items.size());
                                            break;
                                        case -1 :
                                            Snackbar.make(activity.getCurrentFocus() ,  activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                            break;
                                    }
                                    progressDialog.dismiss();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    Snackbar.make(activity.getCurrentFocus() ,  activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                        progressDialog.dismiss();
                                        Snackbar.make(activity.getCurrentFocus() ,  activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                        )
                );


            }
        });


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject params = new JSONObject();
                try {
                    params.put("WISHLIST_ID" , item.getWishListId()) ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final ProgressDialog progressDialog = new ProgressDialog(activity) ;
                progressDialog.setCancelable(false);
                progressDialog.setMessage(activity.getResources().getString(R.string.deleteProgressMsg));
                progressDialog.show();

                AppController.getInstance().addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.POST
                                , AccessLinks.DELETE_ITEM_FROM_WISHLIST
                                , params
                                , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    switch (response.getInt("response")){
                                        case 1 :
                                            items.remove(items.indexOf(item)) ;
                                            adapter.notifyDataSetChanged();
                                            activity.updateNumberOfProducts(items.size());
                                            break;
                                        case -1 :
                                            Snackbar.make(activity.getCurrentFocus() ,  activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                            break;
                                    }

                                    progressDialog.dismiss();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    Snackbar.make(activity.getCurrentFocus() ,  activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                        progressDialog.dismiss();
                                        Snackbar.make(activity.getCurrentFocus() ,  activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                        )
                );
            }
        });

        holder.colorAndSize.setText(sizeColor);
        holder.itemPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


