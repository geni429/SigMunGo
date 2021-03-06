package com.myoungchi.android.sigmungo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.myoungchi.android.sigmungo.Items.MyPageItems;
import com.myoungchi.android.sigmungo.Items.UserData;
import com.myoungchi.android.sigmungo.Items.UserInformation;
import com.myoungchi.android.sigmungo.adapter.MyPageAdapter;
import com.myoungchi.android.sigmungo.http_client.APIclient;
import com.myoungchi.android.sigmungo.http_client.APIinterface;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geni on 2017. 8. 3..
 */

public class MyPage extends AppCompatActivity {
    private UserInformation userInformation;
    private Toolbar toolbar;
    private Spinner dropdown;
    private RecyclerView writeList;
    private APIinterface apiInterface;
    private List<MyPageItems> mDataSet = new ArrayList<>();
    private TextView userName, userId, writingCount, sympathyCount;
    private Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.my_page);
        userInformation = new UserInformation();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        userName = (TextView)findViewById(R.id.user_name);
        userId = (TextView)findViewById(R.id.user_id);
        writingCount = (TextView)findViewById(R.id.writing_count);
        sympathyCount = (TextView)findViewById(R.id.sympathy_count);
        dropdown = (Spinner)findViewById(R.id.time);
        writeList = (RecyclerView)findViewById(R.id.write_list);

        apiInterface = APIclient.getClient().create(APIinterface.class);

        mRealm.init(getApplicationContext());
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserData userData = realm.where(UserData.class).findFirst();
                userName.setText(userData.getUserName());
                userId.setText(userData.getUserId());
                writingCount.setText(userData.getDiscontentCount()+"");
                sympathyCount.setText(userData.getSympathyCount()+"");
                apiInterface.getPostList(userData.getUserId()).enqueue(new retrofit2.Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonArray result = response.body().getAsJsonArray("restaurant");
                        for(int i = 0; i < result.size(); i++){
                            MyPageItems myPageItems = new MyPageItems();
                            myPageItems.setName(result.get(i).getAsJsonObject().get("name").getAsString());
                            myPageItems.setImg(result.get(i).getAsJsonObject().get("img").getAsString());

                            mDataSet.add(myPageItems);
                        }
                        writeList.setAdapter(new MyPageAdapter(MyPage.this, mDataSet));
                        writeList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        setSupportActionBar(toolbar);
        String[] items = new String[]{"아침(breakfast)", "점심(launch)", "저녁(dinner)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    //툴바에서 back버튼을 클릭할시에 종료시켜주는 코드
    public void onBackBtnClicked(View v){
        finish();
    }
}
