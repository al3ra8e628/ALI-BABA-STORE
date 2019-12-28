package com.company.alibaba.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.company.alibaba.R;
import com.company.alibaba.activities.ItemActivity;
import com.company.alibaba.dataSource.AccessLinks;
import com.company.alibaba.entities.Item;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    private List<Item> items;
    private AppCompatActivity activity ;
    public ItemsAdapter(ArrayList<Item> items , AppCompatActivity activity) {
        this.activity = activity ;
        this.items = items;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public ImageView itemPicture ;
        public RatingBar itemRating ;
        public ProgressBar progressBar ;
        public MyViewHolder(View view) {
            super(view);
            itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            itemRating = (RatingBar) view.findViewById(R.id.ratingBar);
            itemPicture = (ImageView) view.findViewById(R.id.itemPic);
            progressBar = (ProgressBar) view.findViewById(R.id.sub_cat_progressBar);
        }
    }


    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_cat_item_view, parent, false);
        return new ItemsAdapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Item item = items.get(position);
        holder.itemTitle.setText(item.getTitle());
        holder.itemRating.setRating(Float.valueOf(item.getRating()));

        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX+item.getImagePath())
                //.resize(250 , 250)
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


                Intent intent = new Intent(activity , ItemActivity.class) ;
                Bundle data = new Bundle() ;

                data.putSerializable("item" , item);
                intent.putExtras(data) ;
                activity.startActivity(intent);


            }
        });



            /*
            String url = new UrlBuilder(AccessLinks.PHOTOS_DIRECTORY).setUrlPath(event.getCoverPicture()).getUrl() ;

            new ImageParser(AppController.getInstance())
                    .setUrl(url).setImage(holder.eventPicture);

            holder.smallEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//code to open event activity on click
                    Intent i = new Intent(context , EventActivity.class) ;
                    i.putExtra("person_id" ,personId+"");
                    i.putExtra(Keys.LOG_IN_STATE_KEY,logInState+"");
                    i.putExtra("eventId" , events.get(position).getId()+"") ;
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


