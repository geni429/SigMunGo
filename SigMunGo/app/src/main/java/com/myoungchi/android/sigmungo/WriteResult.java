package com.myoungchi.android.sigmungo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.Items.UserData;
import com.myoungchi.android.sigmungo.http_client.APIclient;
import com.myoungchi.android.sigmungo.http_client.APIinterface;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geni on 2017. 9. 25..
 */

public class WriteResult extends AppCompatActivity {
    private TextView writeResult;
    private Button submit, back;
    private String result;
    private APIinterface apiInterface;
    private Realm mRealm;
    private String id;
    private Intent mIntent;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_confirm);
        sharedPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        mIntent = getIntent();

        apiInterface = APIclient.getClient().create(APIinterface.class);

        writeResult = (TextView)findViewById(R.id.write_result);
        submit = (Button)findViewById(R.id.submit);
        back = (Button)findViewById(R.id.back);

        result = mIntent.getStringExtra("firstKeywordContent") + " " + mIntent.getStringExtra("secondKeywordContent") + " " + mIntent.getStringExtra("thirdKeywordContent");
        writeResult.setText(result);

        mRealm.init(getApplicationContext());
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserData userData = realm.where(UserData.class).findFirst();
                id = userData.getUserId();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", sharedPreferences.getString("ContentId", ""));
                apiInterface.doPost(sharedPreferences.getString("ContentId", mIntent.getStringExtra("contentid")), id, result).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        startActivity(new Intent(getApplicationContext(), MyPage.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WriteComplain.class));
                finish();
            }
        });
    }

    public void onBackBtnClicked(View view){
        startActivity(new Intent(getApplicationContext(), WriteComplain.class));
        finish();
    }
}
