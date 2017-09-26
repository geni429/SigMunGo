package com.myoungchi.android.sigmungo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myoungchi.android.sigmungo.Items.MyPageItems;
import com.myoungchi.android.sigmungo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geni on 2017. 9. 25..
 */

public class MyPageAdapter extends RecyclerView.Adapter<MyPageAdapter.ViewHolder> {
    private Activity mContext;
    private List<MyPageItems> mItems;

    public MyPageAdapter(Activity context, List<MyPageItems> myPageItemses){
        this.mContext = context;
        this.mItems = myPageItemses;
        Log.d("mItems", mItems+"");
    }

    @Override
    public MyPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_page_adapter, parent, false);
        return new MyPageAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyPageAdapter.ViewHolder holder, int position) {
        Log.d("hello", "test");
        holder.name.setText(mItems.get(position).getName());
        Log.d("asdf", mItems.get(position).getImg());
        Log.d("image", "http://52.15.75.60:5429/upload/" + mItems.get(position).getImg());
        Glide.with(mContext.getApplicationContext()).load("http://52.15.75.60:5429/upload/" + mItems.get(position).getImg()).centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;
        public ViewHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.restaurant_name);
            image = (ImageView)view.findViewById(R.id.restaurant_image);
        }
    }
}
