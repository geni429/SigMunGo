package com.myoungchi.android.sigmungo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by geni on 2017. 7. 31..
 */

public class MainGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public MainGridLayoutManager(Context context, int spanCount, boolean isScrollEnabled) {
        super(context, spanCount);
        this.isScrollEnabled = isScrollEnabled;
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
