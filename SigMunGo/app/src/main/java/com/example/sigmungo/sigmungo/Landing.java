package com.example.sigmungo.sigmungo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.sigmungo.sigmungo.Adapter.LandingAdapter;

/**
 * Created by geni on 2017. 7. 24..
 */

public class Landing extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        pager=(ViewPager)findViewById(R.id.landing_pager);
        LandingAdapter adapter = new LandingAdapter(getLayoutInflater());
        pager.setAdapter(adapter);
    }
}
