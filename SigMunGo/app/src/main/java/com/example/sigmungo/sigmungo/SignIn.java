package com.example.sigmungo.sigmungo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sigmungo.sigmungo.Items.SignInRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dsm2017 on 2017-07-25.
 */

public class SignIn extends AppCompatActivity {
    private Map<String, String> params;
    APIinterface apIinterface;
    TextView signUp;
    EditText inputId;
    EditText inputPw;
    Button signIn;

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.signin);
        params = new HashMap<>();
        signUp = (TextView)findViewById(R.id.signup);
        inputId = (EditText)findViewById(R.id.input_id);
        inputPw = (EditText)findViewById(R.id.input_password);
        signIn = (Button) findViewById(R.id.signin);
        apIinterface = APIclient.getClient().create(APIinterface.class);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignIn(inputId.getText().toString(), inputPw.getText().toString());
            }
        });
    }

    public void doSignIn(String id, String password){
        Log.d("ID and Password", id + " " + password);
        apIinterface.doSignIn(id, password).enqueue(new Callback<SignInRequest>() {
            @Override
            public void onResponse(Call<SignInRequest> call, Response<SignInRequest> response) {
                Log.d("SignIn status code", response.code()+"");
                Log.d("SignIn response body", response.body().toString());
            }

            @Override
            public void onFailure(Call<SignInRequest> call, Throwable t) {
                Log.d("fail", "failure");
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void signUp(View view){
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }
}

//                Call signInCall = apIinterface.doSignIn(inputId.getText().toString(), inputPw.getText().toString());
//                signInCall.enqueue(new Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                        Log.d("SignIn status code", response.code()+"");
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        t.printStackTrace();
//                        call.cancel();
//                    }
//                });

