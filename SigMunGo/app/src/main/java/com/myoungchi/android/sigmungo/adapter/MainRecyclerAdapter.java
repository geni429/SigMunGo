package com.myoungchi.android.sigmungo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myoungchi.android.sigmungo.Items.MainItems;
import com.myoungchi.android.sigmungo.R;
import com.myoungchi.android.sigmungo.RestaurantDetail;

import java.util.List;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private List<MainItems> mDataset;
    private Context mContext;

    public MainRecyclerAdapter(List<MainItems> dataset, Context context){
        this.mDataset = dataset;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_info_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MainItems items = mDataset.get(position);
        Glide.with(mContext).load(R.drawable.the_terrace_photo1).centerCrop().into(holder.restaurantImg);
        holder.restaurantName.setText(items.getRestaurantName());
        holder.restaurantLocation.setText(items.getRestuarantLocation());
        holder.sympathyCount.setText(items.getSympathyCount()+"");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RestaurantDetail.class);
                intent.putExtra("contentid", items.getContentID());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView restaurantImg;
        private TextView restaurantName;
        private TextView restaurantLocation;
        private TextView sympathyCount;
        private LinearLayout layout;

        public ViewHolder(View view){
            super(view);
            this.restaurantImg = (ImageView) view.findViewById(R.id.restaurant_img);
            this.restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            this.restaurantLocation = (TextView) view.findViewById(R.id.restaurant_location);
            this.sympathyCount = (TextView) view.findViewById(R.id.sympathy_count);
            this.layout = (LinearLayout) view.findViewById(R.id.layout);
        }
    }
}
