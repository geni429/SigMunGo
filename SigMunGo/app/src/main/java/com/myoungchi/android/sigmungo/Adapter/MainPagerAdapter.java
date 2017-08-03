package com.myoungchi.android.sigmungo.Adapter;

import android.content.Context;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myoungchi.android.sigmungo.R;
import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainPagerAdapter extends PagerAdapter {
    private final int MAX_POOL_SIZE = 4;
    private Context mContext;
    private Pools.SimplePool<View> viewPool;

    public MainPagerAdapter(Context context) {
        this.mContext = context;
        viewPool = new Pools.SynchronizedPool<>(MAX_POOL_SIZE);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getPagerItemView();
        ImageView img = (ImageView)view.findViewById(R.id.main_pager_image);
        TextView restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
        TextView restaurantLocation = (TextView)view.findViewById(R.id.restaurant_location);
        TextView sympathyCount = (TextView)view.findViewById(R.id.sympathy_count);
        Glide.with(mContext).load(R.drawable.landing_img_1+position).into(img);
        img.setScaleType(CENTER_CROP);
        img.setBackgroundResource(R.drawable.main_recyclerview);
        restaurantName.setText("식문고 치킨점");
        restaurantLocation.setText("대전광역시 식문구 식문동");
        sympathyCount.setText(103+"");
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        viewPool.release((View) object);
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private View getPagerItemView() {
        View view = viewPool.acquire();
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.main_pager, null);
        }

        return view;
    }
}
