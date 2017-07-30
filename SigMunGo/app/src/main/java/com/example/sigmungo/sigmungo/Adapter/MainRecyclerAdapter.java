package com.example.sigmungo.sigmungo.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sigmungo.sigmungo.Items.MainItems;
import com.example.sigmungo.sigmungo.Main;
import com.example.sigmungo.sigmungo.MainItemDecoration;
import com.example.sigmungo.sigmungo.R;
import com.example.sigmungo.sigmungo.RoundedAvatarDrawable;

import org.w3c.dom.Text;

import java.util.List;

import static android.R.id.mask;
import static android.widget.ImageView.ScaleType.CENTER_CROP;

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
        Log.d("MainRecycler parent", parent+"");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_info_items, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainItems items = mDataset.get(position);
        Glide.with(holder.restaurantImg).load(items.getRestaurantImage()).into(holder.restaurantImg);
        holder.restaurantImg.setScaleType(CENTER_CROP);
        holder.restaurantName.setText(items.getRestaurantName());
        holder.restaurantLocation.setText(items.getRestuarantLocation());
        holder.sympathyCount.setText(items.getSympathyCount()+"");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView restaurantImg;
        TextView restaurantName;
        TextView restaurantLocation;
        TextView sympathyCount;

        public ViewHolder(View view){
            super(view);
            this.restaurantImg = (ImageView) view.findViewById(R.id.restaurant_img);
            this.restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            this.restaurantLocation = (TextView) view.findViewById(R.id.restaurant_location);
            this.sympathyCount = (TextView) view.findViewById(R.id.sympathy_count);
        }
    }
}
