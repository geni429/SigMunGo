package com.myoungchi.android.sigmungo.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.myoungchi.android.sigmungo.R;

/**
 * Created by geni on 2017. 8. 2..
 */

public class FindIdPassword extends AppCompatActivity {
    Toolbar toolbar;
    Button findId;
    Button findPw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_id_password);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        findId = (Button)findViewById(R.id.find_id);
        findPw = (Button)findViewById(R.id.find_password);

        setSupportActionBar(toolbar);

        findId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FindId.class));
            }
        });

        findPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FindPassword.class));
            }
        });
    }

    public void onBackBtnClicked(View v){
        finish();
    }
}
