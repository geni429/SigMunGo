package com.myoungchi.android.sigmungo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.myoungchi.android.sigmungo.Items.ValueObject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by geni on 2017. 7. 25..
 */

public class Splash extends AppCompatActivity {
    private Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
//        Realm적용중 이슈발생, 수정필요
//        mRealm.init(getApplicationContext());
//        mRealm = Realm.getDefaultInstance();
//        mRealm.beginTransaction();
//        ValueObject vo = mRealm.createObject(ValueObject.class);
//        mRealm.commitTransaction();
//        Log.d("getVo", getVo().size()+"");
//        if(getVo().size() == 1){
//            Initializing(mRealm, vo);
//        }
//
//        if(!getVo().get(0).isFirst() && getVo().get(0).isLogin()){
//            Log.d("realm on", mRealm.where(ValueObject.class).findFirst().toString());
//            startActivity(new Intent(getApplicationContext(), Main.class));
//        } else if(!getVo().get(0).isFirst() && !getVo().get(0).isLogin()){
//            Log.d("realm login", mRealm.where(ValueObject.class).findFirst().toString());
//            startActivity(new Intent(getApplicationContext(), SignIn.class));
//        } else {
//            updateFirst(mRealm, vo);
//            startActivity(new Intent(getApplicationContext(), Landing.class));
//        }
        try {
            Thread.sleep(1000);
            startActivity(new Intent(getApplicationContext(), Landing.class));
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mRealm.close();
    }

    private RealmResults<ValueObject> getVo(){
        return mRealm.where(ValueObject.class).findAll();
    }

    private void Initializing(Realm realm, ValueObject vo){
        realm.beginTransaction();
        vo.setFirst(true);
        vo.setLogin(false);
        vo.setId("");
        realm.commitTransaction();
        Log.d("realm first", mRealm.where(ValueObject.class).findFirst().toString());
    }

    private void updateFirst(Realm realm, ValueObject vo){
        Log.d("realm status", realm.toString());
        realm.beginTransaction();
        vo.setFirst(false);
        realm.commitTransaction();
    }
}
