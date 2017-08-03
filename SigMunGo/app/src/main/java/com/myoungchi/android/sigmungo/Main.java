package com.myoungchi.android.sigmungo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.Adapter.MainPagerAdapter;
import com.myoungchi.android.sigmungo.Adapter.MainRecyclerAdapter;
import com.myoungchi.android.sigmungo.Items.MainItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myoungchi.android.sigmungo.Items.UserInformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geni on 2017. 7. 26..
 */

public class Main extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private APIinterface apIinterface;
    private List<MainItems> restaurantsInfo = new ArrayList<>();
    private UserInformation userInformation;
    private ViewPager pager;

    private TextView sympathyCount;
    private TextView writingCount;
    private TextView userName;
    private TextView userId;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.main);
        userInformation = new UserInformation();
        recyclerView = (RecyclerView) findViewById(R.id.restaurants_info);
        pager = (ViewPager)findViewById(R.id.main_pager);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        apIinterface = APIclient.getClient().create(APIinterface.class);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //Navigation Drawer Layout
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View navHeaderView = navigationView.getHeaderView(0);

        sympathyCount = (TextView)navHeaderView.findViewById(R.id.sympathy_count);
        writingCount = (TextView)navHeaderView.findViewById(R.id.writing_count);
        userName = (TextView)navHeaderView.findViewById(R.id.user_name);
        userId = (TextView)navHeaderView.findViewById(R.id.user_id);
        setUserInfo();

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
        getRestaurantInfo();
        getUserInfo();
    }

    public void setLocation(View v){
        startActivity(new Intent(getApplicationContext(), SetLocation.class));
    }

    public void initData(int index, String contentid, String img, String name, String place, String sympathy, String improved){
        MainItems items = new MainItems();
        items.setContentID(contentid);
        items.setRestaurantImage(img);
        items.setRestaurantName(name);
        items.setRestuarantLocation(place);
        items.setSympathyCount(sympathy);
        items.setImproved(improved);
        this.restaurantsInfo.add(index, items);
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

    public void getRestaurantInfo(){
        apIinterface.getRestaurantInfo().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(response.body().toString()).getAsJsonObject();
                JsonArray restaurants = jsonObject.get("restaurant").getAsJsonArray();
                Log.d("JsonArray Size", restaurants.size()+"");
                for(int i = 0; i < restaurants.size(); i++){
                    JsonObject info = restaurants.get(i).getAsJsonObject();
                    initData(i,
                            info.get("contentid").getAsString(),
                            info.get("img").getAsString(),
                            info.get("name").getAsString(),
                            info.get("place").getAsString(),
                            info.get("sympathy").getAsString(),
                            info.get("improved").getAsString());
                }
                recyclerView.setAdapter(new MainRecyclerAdapter(restaurantsInfo, getApplicationContext()));
                recyclerView.setLayoutManager(new MainGridLayoutManager(getApplicationContext(), 2, true));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Main GET", "onFailure");
            }
        });
    }

    public void getUserInfo(){
        apIinterface.getUserInfo().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    userInformation.setUserName(response.body().get("name").getAsString());
                    userInformation.setUserId(response.body().get("id").getAsString());
                    userInformation.setMyWritingCount(response.body().get("writing").getAsString());
                    userInformation.setMySympathyCount(response.body().get("sympathy").getAsString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void setUserInfo(){
        userName.setText(userInformation.getUserName());
        userId.setText(userInformation.getUserId());
        SpannableString sympathyCountNumber = new SpannableString(userInformation.getMySympathyCount());
        SpannableString writingCountNumber = new SpannableString(userInformation.getMyWritingCount());
        sympathyCountNumber.setSpan(new UnderlineSpan(), 0, sympathyCountNumber.length(), 0);
        writingCountNumber.setSpan(new UnderlineSpan(), 0, writingCountNumber.length(), 0);
        sympathyCount.setText(sympathyCountNumber);
        writingCount.setText(writingCountNumber);
    }
}
