package com.myoungchi.android.sigmungo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.Items.UserInformation;

/**
 * Created by geni on 2017. 8. 3..
 */

public class MyPage extends AppCompatActivity {
    UserInformation userInformation;

    Toolbar toolbar;
    TextView userName;
    TextView userId;
    TextView writingCount;
    TextView sympathyCount;

    RecyclerView recyclerView;

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
        recyclerView = (RecyclerView)findViewById(R.id.write_list);

        setSupportActionBar(toolbar);

        //get userinformation
        userName.setText(userInformation.getUserName());
        userId.setText(userInformation.getUserId());
        writingCount.setText(userInformation.getMyWritingCount());
        sympathyCount.setText(userInformation.getMySympathyCount());
    }
}
