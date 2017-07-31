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
import android.widget.Toast;

import com.example.sigmungo.sigmungo.Items.SignInRequest;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dsm2017 on 2017-07-25.
 */

public class SignIn extends AppCompatActivity {
    APIinterface apIinterface;
    TextView signUp;
    EditText inputId;
    EditText inputPw;
    Button signIn;

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.signin);
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
                if(response.isSuccessful()){
                    //When Login Success
                    Log.d("SignIn POST", "Success");
                    startActivity(new Intent(getApplicationContext(), Main.class));
                } else {
                    //When Login Failure
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String errorMessage = jsonObject.get("message").toString();
                        if(errorMessage.equals("nonexistentId")){
                            Log.d("SignIn POST", "nonexistentId");
                            Toast.makeText(getApplicationContext(), "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("SignIn POST", "wrongPassword");
                            Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignInRequest> call, Throwable t) {
                Log.d("SignIn POST", "Failure");
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
