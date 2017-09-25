package com.myoungchi.android.sigmungo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by geni on 2017. 9. 25..
 */

public class WriteResult extends AppCompatActivity {
    private TextView writeResult;
    private Button submit, back;
    private String result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_confirm);
        Intent intent = getIntent();

        writeResult = (TextView)findViewById(R.id.write_result);
        submit = (Button)findViewById(R.id.submit);
        back = (Button)findViewById(R.id.back);

        result = intent.getStringExtra("firstKeywordContent") + " " + intent.getStringExtra("secondKeywordContent") + " " + intent.getStringExtra("thirdKeywordContent");
        writeResult.setText(result);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyPage.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WriteComplain.class));
            }
        });
    }
}
