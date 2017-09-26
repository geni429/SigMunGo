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

import com.myoungchi.android.sigmungo.adapter.MainPagerAdapter;
import com.myoungchi.android.sigmungo.adapter.MainRecyclerAdapter;
import com.myoungchi.android.sigmungo.Items.MainItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myoungchi.android.sigmungo.Items.UserInformation;
import com.myoungchi.android.sigmungo.http_client.APIclient;
import com.myoungchi.android.sigmungo.http_client.APIinterface;

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
    private String[] testerRestaurant1 = {
            "더 테라스",     //음식점명
            "경기도 안양시 만안구 안양예술공원로 103번길 김중업 박물관 3층",     //음식점 위치
            "031-689-4540"
    };

    private TextView sympathyCount, writingCount, userName, userId;

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

        navHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyPage.class));
            }
        });

        sympathyCount = (TextView)navHeaderView.findViewById(R.id.sympathy_count);
        writingCount = (TextView)navHeaderView.findViewById(R.id.writing_count);
        userName = (TextView)navHeaderView.findViewById(R.id.user_name);
        userId = (TextView)navHeaderView.findViewById(R.id.user_id);

        //navigationDrawer의 아이템들의 클릭이벤트를 처리해주는 코드
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
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
    }

    //위치설정 액티비티로 넘어갈때 실행되는 코드
    public void setLocation(View v){
        startActivity(new Intent(getApplicationContext(), SetLocation.class));
    }

    //이달의 음식점 ViewPager실행시에 작동되는 스레드 (AsyncTask로 마이그레이션 필요)
    class PagerThread extends Thread{
        public void run(){
            MainPagerAdapter adapter = new MainPagerAdapter(getApplicationContext());
            pager.setAdapter(adapter);
        }
    }

    //툴바에 있는 각각의 아이템이 선택되었을 때의 이벤트를 실행시켜주는 메소드
    public void toolbarItemOnClick(View v){
        switch (v.getId()){
            case R.id.sidemenu_btn:
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.d("sidemenu_btn", "clicked");
                break;
            case R.id.simplewrite_btn:
                Log.d("simplewrite_btn", "clicked");
                startActivity(new Intent(getApplicationContext(), WriteComplain.class));
                break;
        }
    }

    //음식점 정보를 통신을 통해서 불러오는 메소드
    public void getRestaurantInfo(){
        apIinterface.getRestaurantInfo().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray restaurants = response.body().getAsJsonArray("restaurant");
                for(int i = 0; i < restaurants.size(); i++){
                    JsonObject restaurant = restaurants.get(i).getAsJsonObject();
                    MainItems mainItems = new MainItems();
                    mainItems.setContentID(restaurant.get("contentid").getAsString());
                    mainItems.setRestaurantName(restaurant.get("name").getAsString());
                    mainItems.setRestuarantLocation(restaurant.get("place").getAsString());
                    mainItems.setSympathyCount(restaurant.get("sympathy").getAsInt());
                    mainItems.setImproved(restaurant.get("improved").getAsInt());
                    mainItems.setImage(restaurant.get("img").getAsString());

                    restaurantsInfo.add(i, mainItems);
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

    //user정보를 불러오는 코드 (Realm사용 예정)
    public void getUserInfo(){
        apIinterface.getUserInfo("nn").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("getUserInfo Status", response.code()+"");
                if(response.isSuccessful()){
                    userInformation.setUserName(response.body().get("name").getAsString());
                    userInformation.setUserId("("+response.body().get("id").getAsString()+")");
                    userInformation.setMyWritingCount(response.body().get("discontents").getAsString());
                    userInformation.setMySympathyCount(response.body().get("sympathy").getAsString());
                    setUserInfo();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //받아온 데이터를 바탕으로 내정보를 세팅해주는 메소드
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
