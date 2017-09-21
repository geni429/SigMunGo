package com.myoungchi.android.sigmungo.account;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myoungchi.android.sigmungo.Items.UserInformation;
import com.myoungchi.android.sigmungo.Items.ValueObject;
import com.myoungchi.android.sigmungo.Landing;
import com.myoungchi.android.sigmungo.http_client.APIclient;
import com.myoungchi.android.sigmungo.http_client.APIinterface;
import com.myoungchi.android.sigmungo.Main;
import com.myoungchi.android.sigmungo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieManager;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dsm2017 on 2017-07-25.
 */

public class SignIn extends AppCompatActivity {
    private Toolbar toolbar;
    private APIinterface apIinterface;
    private EditText inputId, inputPw;
    private Button signIn;
    private UserInformation userInformation;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.signin);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        inputId = (EditText)findViewById(R.id.input_id);
        inputPw = (EditText)findViewById(R.id.input_password);
        signIn = (Button) findViewById(R.id.signin);
        apIinterface = APIclient.getClient().create(APIinterface.class);
        userInformation = new UserInformation();

        setSupportActionBar(toolbar);

        //로그인 버튼을 클릭했을시에 실행시켜주는 코드
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignIn(inputId.getText().toString(), inputPw.getText().toString());
            }
        });
    }

    public void doSignIn(String id, String password){
        apIinterface.doSignIn(id, password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    //로그인 성공시 코드
                    userInformation.setUserId(inputId.getText().toString());
                    sharedPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                    sharedPreferences.edit().putBoolean("isSignIn", true).apply();
                    Log.d("change isSignIn", sharedPreferences.getBoolean("isSignIn", true)+"");
                    startActivity(new Intent(getApplicationContext(), Main.class));
                    finish();
                } else {
                    //로그인 실패시 코드
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String errorMessage = jsonObject.get("message").toString();
                        if(errorMessage.equals("nonexistentId")){
                            //존재하지 않는 아이디를 입력한 경우
                            Log.d("SignIn POST", "nonexistentId");
                            Toast.makeText(getApplicationContext(), "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            //틀린 비밀번호를 입력한 경우
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
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("SignIn POST Fail", "Failure");
                t.printStackTrace();
            }
        });
    }

    //회원이 아니신가요를 눌렀을 시에 실행되는 코드 (회원가입 액티비티로 이동하게 된다)
    public void onSignUpClicked(View view){
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }

    //아이디 비밀번호 찾기를 선택했을 시에 실행되는 코드
    public void onFindIdPasswordClicked(View view){
        startActivity(new Intent(getApplicationContext(), FindIdPassword.class));
    }

    //툴바에서 back버튼을 클릭할시에 랜딩으로 돌아가는 코드
    public void onBackBtnClicked(View v){
        startActivity(new Intent(getApplicationContext(), Landing.class));
        finish();
    }
}
