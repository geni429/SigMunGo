package com.example.sigmungo.sigmungo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sigmungo.sigmungo.Items.MainItems;
import com.example.sigmungo.sigmungo.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<MainItems> mDataset;

    public MainRecyclerAdapter(List<MainItems> dataset){
        this.mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_info_items,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainItems items = mDataset.get(position);
        Glide.with(holder.restaurantImg).load(items.getRestaurantImage()).into(holder.restaurantImg);
        holder.restaurantName.setText(items.getRestaurantName());
        holder.restaurantLocation.setText(items.getRestuarantLocation());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView restaurantImg;
        TextView restaurantName;
        TextView restaurantLocation;

        public ViewHolder(View view){
            super(view);
            this.restaurantImg = (ImageView) view.findViewById(R.id.restaurant_img);
            this.restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            this.restaurantLocation = (TextView) view.findViewById(R.id.restaurant_location);
        }
    }
}
