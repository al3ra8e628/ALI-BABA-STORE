package com.company.alibaba.adapters;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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
import com.company.alibaba.activities.MyCartActivity;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.dataSource.AppController;
import com.company.alibaba.entities.CartItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.MyViewHolder> {
    private List<CartItem> items;
    private MyCartActivity activity;

    CartItemsAdapter adapter ;

    public CartItemsAdapter(ArrayList<CartItem> items, MyCartActivity activity) {
        this.activity = activity;
        this.items = items;
        adapter = this ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public TextView itemPrice1;
        public ImageView itemPicture;
        public ImageView deleteButton;
        public ImageView addToCartButton;
        public TextView colorAndSize;
        public TextView times;
        public ImageView addMoreOne;
        public ImageView removeOne;
        public ProgressBar progressBar;

        public MyViewHolder(View view) {
            super(view);
            itemTitle = (TextView) view.findViewById(R.id.cart_view_item_title);
            itemPrice1 = (TextView) view.findViewById(R.id.cart_view_item_price1);
            colorAndSize = (TextView) view.findViewById(R.id.cart_view_item_colorAndSize);

            itemPicture = (ImageView) view.findViewById(R.id.cart_view_item_picture);
            deleteButton = (ImageView) view.findViewById(R.id.cart_view_item_remove);
            addToCartButton = (ImageView) view.findViewById(R.id.cart_view_item_add_to_cart);

            times = (TextView) view.findViewById(R.id.times);
            addMoreOne = (ImageView) view.findViewById(R.id.addMoreOne);
            removeOne = (ImageView) view.findViewById(R.id.removeOne);

            progressBar = (ProgressBar) view.findViewById(R.id.cartViewProgressBar);
        }
    }

    @Override
    public CartItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_view_item, parent, false);
        return new CartItemsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CartItem item = items.get(position);
        holder.itemTitle.setText(item.getItemTitle());
        holder.addToCartButton.setVisibility(View.INVISIBLE);
        holder.itemPrice1.setText(item.getItemPrice(activity));
        holder.times.setText(item.getQuantity() + "");
        String sizeColor;


        if (!item.getColorName().equals("none")) {
            if (!item.getSizeName().equals("none")) {
                sizeColor = item.getColorName() + "," + item.getSizeName();
            } else {
                sizeColor = item.getColorName();
            }
        } else {
            if (!item.getSizeName().equals("none")) {
                sizeColor = item.getSizeName();
            } else {
                sizeColor = "";
            }
        }


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject params = new JSONObject();
                try {
                    params.put("CART_ID" , item.getCartId()) ;
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
                    , AccessLinks.DELETE_ITEM_FROM_CART
                    , params
                    , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                switch (response.getInt("response")){
                                    case 1 :
                                        String totalItemPrice = ""+(item.getQuantity()*Double.parseDouble(item.getItemPrice(null))) ;
                                        CartItem.decrementTotal(totalItemPrice);
                                        items.remove(items.indexOf(item)) ;
                                        adapter.notifyDataSetChanged();
                                        activity.upDateTotalPrice();
                                        break;
                                    case -1 :
                                        Snackbar.make(activity.getCurrentFocus()  , activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                        break;
                                }

                                progressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Snackbar.make(activity.getCurrentFocus(), activity.getResources().getString(R.string.ConnectionErrorText), Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            progressDialog.dismiss();
                            Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                        }
                    }
                )
                );
            }
        });

        holder.colorAndSize.setText(sizeColor);

        holder.addMoreOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final ProgressDialog progressDialog = new ProgressDialog(activity) ;
              progressDialog.setMessage(activity.getResources().getString(R.string.addingProgressMsg)) ;
              progressDialog.setCancelable(false);
              progressDialog.show();

              JSONObject params = new JSONObject() ;
                try {
                    params.put("cartId" , item.getCartId()+"") ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AppController.getInstance().addToRequestQueue(
                      new JsonObjectRequest(
                      Request.Method.POST
                      , AccessLinks.ADD_MORE_ONE
                      , params
                      , new Response.Listener<JSONObject>() {
                         @Override
                         public void onResponse(JSONObject response) {
                             try {
                                 switch (response.getInt("response")){
                                     case 1 :
                                         CartItem.incrementTotal(item.getItemPrice(null));
                                         holder.times.setText((Integer.parseInt(holder.times.getText().toString()) + 1) + "");
                                         item.setQuantity(Integer.parseInt(holder.times.getText().toString()));
                                         activity.upDateTotalPrice();
                                         progressDialog.dismiss();
                                         break;
                                     case -1 :
                                         Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                         progressDialog.dismiss();
                                     break;
                                 }
                             } catch (JSONException e) {
                                 e.printStackTrace();
                                 Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                 progressDialog.dismiss();
                             }
                         }
                      }
                      , new Response.ErrorListener() {
                          @Override
                          public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.ConnectionErrorText), Snackbar.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                          }
                      }
                      )

              );
            }
        });


        holder.removeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity) ;
                progressDialog.setMessage(activity.getResources().getString(R.string.deleteProgressMsg)) ;
                progressDialog.setCancelable(false);

                if(item.getQuantity() < 2){
                    Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.itIsTheMinimum) , Snackbar.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();

                JSONObject params = new JSONObject() ;
                try {
                    params.put("cartId" , item.getCartId()+"") ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AppController.getInstance().addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.POST
                                , AccessLinks.REMOVE_ONE
                                , params
                                , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    switch (response.getInt("response")){
                                        case 1 :
                                            CartItem.decrementTotal(item.getItemPrice(null));
                                            holder.times.setText((Integer.parseInt(holder.times.getText().toString()) - 1) + "");
                                            item.setQuantity(Integer.parseInt(holder.times.getText().toString()));
                                            activity.upDateTotalPrice();
                                            progressDialog.dismiss();
                                            break;
                                        case -1 :
                                            Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.ConnectionErrorText), Snackbar.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                            break;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.ConnectionErrorText), Snackbar.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        }
                                , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Snackbar.make(activity.getCurrentFocus() , activity.getResources().getString(R.string.ConnectionErrorText) , Snackbar.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                        )

                );
            }
        });


        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX + item.getProductImage())
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





    public void deleteFromCart(MyViewHolder holder) {


    }


}


