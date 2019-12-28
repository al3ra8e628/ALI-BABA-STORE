package com.company.alibaba.adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.company.alibaba.R;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.entities.Order;
import com.company.alibaba.utils.LanguageManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrdersItemsAdapter extends RecyclerView.Adapter<OrdersItemsAdapter.MyViewHolder> {
    private List<Order> orders;
    private AppCompatActivity activity;

    public OrdersItemsAdapter(ArrayList<Order> orders, AppCompatActivity activity) {
        this.activity = activity;
        this.orders = orders;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;

        public TextView orderDate;
        public TextView orderQuantity;
        public TextView orderStatus;
        public ImageView itemPicture;
        public ProgressBar progressBar ;
        public TextView colorSize;
        public TextView orderPrice;



        public MyViewHolder(View view) {
            super(view);
            itemTitle = (TextView) view.findViewById(R.id.order_view_item_title);
            orderDate = (TextView) view.findViewById(R.id.order_view_item_date);
            orderQuantity = (TextView) view.findViewById(R.id.order_view_item_times);
            orderStatus = (TextView) view.findViewById(R.id.order_view_item_status);
            itemPicture = (ImageView) view.findViewById(R.id.order_view_item_picture);
            progressBar = (ProgressBar) view.findViewById(R.id.order_view_progressBar);
            colorSize = (TextView) view.findViewById(R.id.order_view_item_color);
            orderPrice = (TextView) view.findViewById(R.id.order_view_item_size);
        }
    }

    @Override
    public OrdersItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_view_item, parent, false);
        return new OrdersItemsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Order order = orders.get(position);

        if(LanguageManager.isLangEn(activity)) {
        holder.itemTitle.setText(order.getProductTitle());
        holder.orderPrice.setText(order.getPrice(activity));
        holder.orderDate.setText("Date : "+order.getDate());
        holder.orderStatus.setText("Status : "+order.getState());
        holder.orderQuantity.setText("Quantity : "+order.getQuantity()+"");
        String sizeColor = "" ;

            if (!order.getColorName().equals("0")) {
                if (!order.getSizeName().equals("0")) {
                    sizeColor = order.getColorName() + "," + order.getSizeName();
                } else {
                    sizeColor = order.getColorName();
                }
            } else {
                if (!order.getSizeName().equals("0")) {
                    sizeColor = order.getSizeName();
                } else {
                    sizeColor = "";
                }
            }
            holder.colorSize.setText(sizeColor);
        }else{
            holder.itemTitle.setText(order.getProductTitle());
            holder.orderPrice.setText(order.getPrice(activity));
            holder.orderDate.setText("التاريخ : ؟".replace("؟",order.getDate()));
            holder.orderStatus.setText("الحالة : ؟".replace("؟" , ""+order.getState()));
            holder.orderQuantity.setText("الكمية : ؟".replace("؟" , ""+order.getQuantity()));
            String sizeColor = "" ;

            if (!order.getColorName().equals("0")) {
                if (!order.getSizeName().equals("0")) {
                    sizeColor = order.getColorName() + "," + order.getSizeName();
                } else {
                    sizeColor = order.getColorName();
                }
            } else {
                if (!order.getSizeName().equals("0")) {
                    sizeColor = order.getSizeName();
                } else {
                    sizeColor = "";
                }
            }
            holder.colorSize.setText(sizeColor);
        }


        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX+order.getItemPicture())
                .into(holder.itemPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

/*        holder.itemPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ItemActivity.class);
                Bundle data = new Bundle();

                data.putSerializable("item", item);
                intent.putExtras(data);
                activity.startActivity(intent);
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}


