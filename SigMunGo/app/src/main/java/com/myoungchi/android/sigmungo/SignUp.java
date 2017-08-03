package com.myoungchi.android.sigmungo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dsm2017 on 2017-07-25.
 */


public class SignUp extends AppCompatActivity {
    APIinterface apIinterface;
    Toolbar toolbar;
    EditText name;
    EditText phone;
    EditText id;
    EditText password;
    EditText cf_password;
    Button submit;

    private boolean cf_check;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.signup);
        name = (EditText)findViewById(R.id.input_name);
        phone = (EditText)findViewById(R.id.input_phoneNumber);
        id = (EditText)findViewById(R.id.input_id);
        password = (EditText)findViewById(R.id.input_userPassword);
        cf_password = (EditText)findViewById(R.id.input_passwordOK);
        submit = (Button)findViewById(R.id.submit);
        apIinterface = APIclient.getClient().create(APIinterface.class);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cf_check){
                    doSignUp(id.getText().toString(),
                            name.getText().toString(),
                            password.getText().toString(),
                            phone.getText().toString());
                } else {
                }
            }
        });

        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                apIinterface.doIdCheck(s.toString()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("id check", response.code()+"");
                        Log.d("id check", response.isSuccessful()+"");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cf_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(password.getText().toString().equals("")){
                        password.setHint("비밀번호를 설정해주세요");
                        password.setHintTextColor(Color.parseColor("#FF7C4E"));
                    }
                }
            }
        });

        cf_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(password.getText().toString())){
                    cf_password.setTextColor(Color.parseColor("#FF7C4E"));
                    cf_check = false;
                } else {
                    cf_password.setTextColor(Color.BLACK);
                    cf_check = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void doSignUp(String id, String name, String password, String phone){
        apIinterface.doSignUp(name, phone, id, password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("SignUp POST", "Success");
                    Log.d("SignUp POST", response.code()+"");
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                } else {
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void onBackBtnClicked(View v){
        finish();
    }
}