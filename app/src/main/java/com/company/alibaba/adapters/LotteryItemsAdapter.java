package com.company.alibaba.adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.company.alibaba.R;
import com.company.alibaba.entities.Lottery;
import java.util.ArrayList;
import java.util.List;


public class LotteryItemsAdapter extends RecyclerView.Adapter<LotteryItemsAdapter.MyViewHolder> {
    private List<Lottery> lotteries;
    private AppCompatActivity activity;

    public LotteryItemsAdapter(ArrayList<Lottery> lotteries, AppCompatActivity activity) {
        this.activity = activity;
        this.lotteries = lotteries;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderDate;
        public TextView orderPrice;
        public TextView lotteryId;
        public TextView lotteryName;

        public MyViewHolder(View view) {
            super(view);
            orderDate = (TextView) view.findViewById(R.id.lotteryDate);
            orderPrice = (TextView) view.findViewById(R.id.lotteryAmount);
            lotteryId = (TextView) view.findViewById(R.id.lotteryId);
            lotteryName = (TextView) view.findViewById(R.id.lotteryName);
        }
    }

    @Override
    public LotteryItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lottery_view_item,parent,false);
        return new LotteryItemsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Lottery lottery = lotteries.get(position);

            holder.lotteryName.setText(lottery.getLotteryName());
            holder.orderPrice.setText("Amount : " + lottery.getAmount(activity));
            holder.orderDate.setText("Date : " +lottery.getDate());
            holder.lotteryId.setText("Lottery Id : " + lottery.getId());

    }
    @Override
    public int getItemCount() {
        return lotteries.size();
    }
}


