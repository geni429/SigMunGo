package com.example.sigmungo.sigmungo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sigmungo.sigmungo.Adapter.MainPagerAdapter;
import com.example.sigmungo.sigmungo.Adapter.MainRecyclerAdapter;
import com.example.sigmungo.sigmungo.Items.MainItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geni on 2017. 7. 26..
 */

public class Main extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.main);
        recyclerView = (RecyclerView) findViewById(R.id.restaurants_info);
        pager = (ViewPager)findViewById(R.id.main_pager);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_item_restaurant:
                        Log.d("nav_item_restaurant", "clicked");
                        break;
                    case R.id.nav_item_notice:
                        Log.d("nav_item_notice", "clicked");
                        break;
                    case R.id.nav_item_help:
                        Log.d("nav_item_help", "clicked");
                        break;
                }

                return true;
            }
        });

        PagerThread thread = new PagerThread();
        thread.start();
        initData();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setLocation(View v){
        startActivity(new Intent(getApplicationContext(), SetLocation.class));
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

    public void toolbarItemOnClick(View v){
        switch (v.getId()){
            case R.id.sidemenu_btn:
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.d("sidemenu_btn", "clicked");
                break;
            case R.id.simplewrite_btn:
                Log.d("simplewrite_btn", "clicked");
                break;
        }
    }
}
