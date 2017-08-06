package com.myoungchi.android.sigmungo.account;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myoungchi.android.sigmungo.http_client.APIclient;
import com.myoungchi.android.sigmungo.http_client.APIinterface;
import com.myoungchi.android.sigmungo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dsm2017 on 2017-07-25.
 */


public class SignUp extends AppCompatActivity {
    private APIinterface apIinterface;
    private Toolbar toolbar;
    private EditText name, phone, id, password, cf_password;
    private TextView checkIdOverlap, checkPassword;
    private Button submit;

    //비밀번호 재확인 여부
    private boolean cf_check;
    //비밀번호 입력 여부
    private boolean pw_null_check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.signup);
        name = (EditText)findViewById(R.id.input_name);
        phone = (EditText)findViewById(R.id.input_phoneNumber);
        id = (EditText)findViewById(R.id.input_id);
        password = (EditText)findViewById(R.id.input_userPassword);
        cf_password = (EditText)findViewById(R.id.input_passwordOK);
        submit = (Button)findViewById(R.id.submit);
        checkIdOverlap = (TextView)findViewById(R.id.check_id_overlap);
        checkPassword = (TextView)findViewById(R.id.check_password);
        apIinterface = APIclient.getClient().create(APIinterface.class);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        checkIdOverlap.setVisibility(View.GONE);
        checkPassword.setVisibility(View.GONE);

        //회원가입을 시켜주는 코드
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //비밀번호 확인이 되었는지, 비밀번호가 입력되어 있는지, 아이디가 입력되어 있는지, 핸드폰번호가 입력되어 있는지, 이름이 입력되어있는지
                if(cf_check && pw_null_check){
                    doSignUp(id.getText().toString(),
                            name.getText().toString(),
                            password.getText().toString(),
                            phone.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //아이디가 이미 존재하는 아이디인지 확인시켜주는 코드
        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                apIinterface.doIdCheck(s.toString()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            checkIdOverlap.setVisibility(View.VISIBLE);
                        } else if(response.code() == 204){
                            checkIdOverlap.setVisibility(View.GONE);
                        }
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

        //비밀번호 입력을 하지않은 경우를 확인시켜주는 코드
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("")){
                    checkPassword.setVisibility(View.GONE);
                } else {
                    checkPassword.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //비밀번호 확인을 클릭했을 때 비밀번호 입력란이 비어있는지 안비어있는지 확인시켜주는 코드
        cf_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(password.getText().toString().equals("")){
                        checkPassword.setVisibility(View.VISIBLE);
                        pw_null_check = false;
                    } else {
                        checkPassword.setVisibility(View.GONE);
                        pw_null_check = true;
                    }
                }
            }
        });

        //비밀번호 확인에서 입력한 값과 비밀번호에서 입력한 값이 일치한지 확인시켜주는 코드
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

    //submit버튼 클릭시에 회원가입을 요청하는 코드
    public void doSignUp(String id, String name, String password, String phone){
        apIinterface.doSignUp(name, phone, id, password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //툴바에서 back버튼을 눌렀을 시에 종료시켜주는 코드
    public void onBackBtnClicked(View v){
        finish();
    }
}