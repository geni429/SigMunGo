package com.example.sigmungo.sigmungo.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.sigmungo.sigmungo.R;
import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by geni on 2017. 7. 24..
 */

public class LandingAdapter extends PagerAdapter {
    LayoutInflater inflater;

    public LandingAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.landing_pager, null);;
        ImageView img= (ImageView)view.findViewById(R.id.landing_pager_image);
        img.setImageResource(R.drawable.landing_img_1+position);
        img.setScaleType(CENTER_CROP);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
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
}
