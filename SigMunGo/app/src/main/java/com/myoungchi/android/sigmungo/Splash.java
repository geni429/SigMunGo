package com.myoungchi.android.sigmungo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.myoungchi.android.sigmungo.account.SignIn;


/**
 * Created by geni on 2017. 7. 25..
 */

public class Splash extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
        sharedPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        checkFirstRun();
    }

    public void checkFirstRun(){
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        boolean isSignIn = sharedPreferences.getBoolean("isSignIn", false);
        Log.d("isSignIn", isSignIn+"");
        if(isFirstRun) {
            startActivity(new Intent(getApplicationContext(), Landing.class));
            sharedPreferences.edit().putBoolean("isFirstRun", false).apply();
            finish();
        } else if(isSignIn) {
            startActivity(new Intent(getApplicationContext(), Main.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), SignIn.class));
            finish();
        }
    }
}
