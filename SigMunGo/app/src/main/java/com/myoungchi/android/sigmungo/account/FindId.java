package com.myoungchi.android.sigmungo.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myoungchi.android.sigmungo.R;

/**
 * Created by geni on 2017. 8. 4..
 */

public class FindId extends AppCompatActivity {
    private EditText name, phone;
    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_id);
        name = (EditText)findViewById(R.id.user_name);
        phone = (EditText)findViewById(R.id.user_phone);
        submit = (Button)findViewById(R.id.submit);
    }

    //툴바에서 back버튼을 클릭할시에 종료시켜주는 코드
    public void onBackBtnClicked(View v){
        finish();
    }
}
