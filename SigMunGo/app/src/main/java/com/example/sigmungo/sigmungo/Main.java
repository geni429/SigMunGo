package com.example.sigmungo.sigmungo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import com.example.sigmungo.sigmungo.Adapter.MainPagerAdapter;
import com.example.sigmungo.sigmungo.Adapter.MainRecyclerAdapter;
import com.example.sigmungo.sigmungo.Items.MainItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geni on 2017. 7. 26..
 */

public class Main extends Activity {
    private RecyclerView recyclerView;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.main);

        recyclerView = (RecyclerView) findViewById(R.id.restaurants_info);
        pager = (ViewPager)findViewById(R.id.main_pager);
        PagerThread thread = new PagerThread();
        thread.start();
        initData();
    }

    public void initData(){
        List<MainItems> restaurantsInfo = new ArrayList<>();
        for(int i=0; i<5; i++){
            MainItems items = new MainItems();
            items.setRestaurantImage(R.drawable.restaurant_img_1+i);
            items.setRestaurantName("식문고 식당");
            items.setRestuarantLocation("대전광역시 식문구 식문동");
            items.setSympathyCount(103);
            restaurantsInfo.add(items);
        }

        recyclerView.setAdapter(new MainRecyclerAdapter(restaurantsInfo));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    class PagerThread extends Thread{
        public void run(){
            MainPagerAdapter adapter = new MainPagerAdapter(getApplicationContext());
            pager.setAdapter(adapter);
        }
    }
}