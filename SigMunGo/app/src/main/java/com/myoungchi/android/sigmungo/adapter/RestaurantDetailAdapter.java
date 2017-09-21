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
import com.myoungchi.android.sigmungo.R;

/**
 * Created by geni on 2017. 9. 21..
 */

public class RestaurantDetailAdapter extends PagerAdapter {
    private Context mContext;
    private Pools.SimplePool<View> viewPool;

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public RestaurantDetailAdapter(Context context){
        this.mContext = context;
        viewPool = new Pools.SynchronizedPool<>(8);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getPagerItemView();
        ImageView restaurantPhoto = (ImageView)view.findViewById(R.id.restaurant_photo);
        Glide.with(mContext).load(R.drawable.the_terrace_photo1 + position).fitCenter().into(restaurantPhoto);
        container.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        return 8;
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
