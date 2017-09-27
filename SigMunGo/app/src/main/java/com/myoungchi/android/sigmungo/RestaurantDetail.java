package com.myoungchi.android.sigmungo;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import com.google.maps.android.PolyUtil;
import com.myoungchi.android.sigmungo.account.SignIn;
import com.myoungchi.android.sigmungo.adapter.RestaurantDetailAdapter;
import com.myoungchi.android.sigmungo.http_client.APIclient;
import com.myoungchi.android.sigmungo.http_client.APIinterface;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geni on 2017. 8. 5..
 */

public class RestaurantDetail extends AppCompatActivity implements OnMapReadyCallback{
    private Toolbar toolbar;
    private TextView restaurantName, restaurantPhone, restaurantLocation;
    private Button writeComplain;
    private ViewPager restaurantPhoto;
    private Intent mIntent;
    private APIinterface apiInterface;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail);
        restaurantName = (TextView)findViewById(R.id.restaurant_name);
        restaurantPhone = (TextView)findViewById(R.id.restaurant_phone);
        restaurantLocation = (TextView)findViewById(R.id.restaurant_location);
        restaurantPhoto = (ViewPager)findViewById(R.id.restaurant_photo);
        writeComplain = (Button)findViewById(R.id.writeComplain);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        mIntent = getIntent();
        apiInterface = APIclient.getClient().create(APIinterface.class);

        apiInterface.getRestaurantDetail(mIntent.getStringExtra("contentid")).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("response", response.body()+"");
                restaurantName.setText(response.body().get("name").getAsString());
                restaurantLocation.setText(response.body().get("place").getAsString());
                restaurantPhone.setText(response.body().get("phone").getAsString());
                apiInterface.getRestaurantImgs(mIntent.getStringExtra("contentid")).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("image response", response.body().getAsJsonArray("images")+"");
                        restaurantPhoto.setAdapter(new RestaurantDetailAdapter(getApplicationContext(), response.body().getAsJsonArray("images")));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        writeComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                if(sharedPreferences.getBoolean("isSignIn", false)){
                    sharedPreferences.edit().putString("ContentId", mIntent.getStringExtra("contentid")).apply();
                    startActivity(new Intent(getApplicationContext(), WriteComplain.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스입니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SignIn.class);
                    intent.putExtra("member", false);
                    startActivity(intent);
                }
            }
        });
    }

    //Google map api 구현부
    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng SEOUL = new LatLng(37.417672, 126.918140);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("경기도 안양시 만안구 안양예술공원로 103번길 김중업 박물관 3층");
        markerOptions.snippet("더 테라스");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(18));
    }

    //툴바에서 back버튼을 클릭할시에 종료시켜주는 코드
    public void onBackBtnClicked(View v){
        finish();
    }
}
