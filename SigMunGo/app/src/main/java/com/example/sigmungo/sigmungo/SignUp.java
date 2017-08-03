package com.example.sigmungo.sigmungo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sigmungo.sigmungo.Items.SignUpRequest;

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

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.signup);
        name = (EditText)findViewById(R.id.input_name);
        phone = (EditText)findViewById(R.id.input_phoneNumber);
        id = (EditText)findViewById(R.id.input_id);
        password = (EditText)findViewById(R.id.input_password);
        cf_password = (EditText)findViewById(R.id.input_passwordOK);
        submit = (Button)findViewById(R.id.submit);
        apIinterface = APIclient.getClient().create(APIinterface.class);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignUp(name.getText().toString(),
                        phone.getText().toString(),
                        id.getText().toString(),
                        password.getText().toString());
            }
        });
    }

    public void doSignUp(String name, String phone, String id, String password){
        apIinterface.doSignUp(name, phone, id, password).enqueue(new Callback<SignUpRequest>() {
            @Override
            public void onResponse(Call<SignUpRequest> call, Response<SignUpRequest> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                } else {
                }
            }

            @Override
            public void onFailure(Call<SignUpRequest> call, Throwable t) {

            }
        });
    }

    public void onBackBtnClicked(View v){
        finish();
    }
}