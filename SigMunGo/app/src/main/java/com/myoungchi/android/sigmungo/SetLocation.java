package com.myoungchi.android.sigmungo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myoungchi.android.sigmungo.adapter.SetLocationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geni on 2017. 7. 29..
 */

public class SetLocation extends Activity {
    private RecyclerView recyclerView;
    private List<String> locationNames = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.set_location);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.set_location_recyclerview);
        recyclerView.setAdapter(new SetLocationAdapter(locationNames, getApplicationContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void initData(){
        for(int i=0; i<5; i++){
            locationNames.add(i, "hello");
        }
    }

    public void onBackBtnClicked(View v){
        finish();
    }
}
