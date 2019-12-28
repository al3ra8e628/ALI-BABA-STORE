package com.company.alibaba.adapters;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
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

public class HomeItemsAdapter extends RecyclerView.Adapter<HomeItemsAdapter.MyViewHolder> {
    private List<Item> items;
    private AppCompatActivity activity ;
    public HomeItemsAdapter(ArrayList<Item> items , AppCompatActivity activity) {
        this.activity = activity ;
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public ImageView itemPicture ;
        public RatingBar itemRating ;
        public Button favoriteImage ;
        public ProgressBar progressBar ;
        TextView price ;
        TextView oldPrice ;


        public MyViewHolder(View view) {
            super(view);
            itemTitle     = (TextView) view.findViewById(R.id.homeFullItemTitle);
            itemRating    = (RatingBar) view.findViewById(R.id.homeFullRatingBar);
            itemPicture   = (ImageView) view.findViewById(R.id.homeFullItemPic);
            favoriteImage = (Button) view.findViewById(R.id.homeAddToFavorite);
            progressBar   = (ProgressBar) view.findViewById(R.id.homeItemViewBigProgressBar);

            price         = (TextView) view.findViewById(R.id.homeItemPriceLeft);
            oldPrice      = (TextView) view.findViewById(R.id.homeItemPriceRight);
        }
    }

    @Override
    public HomeItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_view_big, parent, false);
        return new HomeItemsAdapter.MyViewHolder(itemView);
    }

    private void favourAnimation(View view) {
        Animation animation;
        animation = new ScaleAnimation(1.3f, 0.90f, 1.3f, 0.90f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        view.startAnimation(animation);

        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationY", -100f, 0f);
        animY.setDuration(1000);//1.5sec
        animY.setInterpolator(new BounceInterpolator());
        animY.setRepeatCount(0);
        animY.start();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Item item = items.get(position);
        holder.itemTitle.setText(item.getTitle());
        holder.itemRating.setRating(Float.valueOf(item.getRating()));

        holder.price.setText(item.getPrice(activity));
        holder.oldPrice.setText(item.getOldPrice(activity));
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        Picasso.get()
                .load(AccessLinks.PICTURES_PREFIX+item.getImagePath())
                .into(holder.itemPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Exception e) {

                    }
                });

        favourAnimation(holder.favoriteImage);

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

}

    @Override
    public int getItemCount() {
        return items.size();
    }
}


