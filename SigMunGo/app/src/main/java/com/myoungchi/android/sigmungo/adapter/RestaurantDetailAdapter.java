package com.myoungchi.android.sigmungo.adapter;

import android.content.Context;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.myoungchi.android.sigmungo.R;

/**
 * Created by geni on 2017. 9. 21..
 */

public class RestaurantDetailAdapter extends PagerAdapter {
    private Context mContext;
    private JsonArray mImages;
    private Pools.SimplePool<View> viewPool;

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public RestaurantDetailAdapter(Context context, JsonArray images){
        this.mContext = context;
        this.mImages = images;
        viewPool = new Pools.SynchronizedPool<>(8);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getPagerItemView();
        ImageView restaurantPhoto = (ImageView)view.findViewById(R.id.restaurant_photo);
        String image = "http://52.15.75.60:5429/upload/" + mImages.get(position).getAsString();
        Glide.with(mContext).load(image).fitCenter().into(restaurantPhoto);
        Log.d("position", position+"");
        container.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        viewPool.release((View) object);
        container.removeView((View)object);
    }

    private View getPagerItemView() {
        View view = viewPool.acquire();
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.restaurant_photo, null);
        }

        return view;
    }
}
