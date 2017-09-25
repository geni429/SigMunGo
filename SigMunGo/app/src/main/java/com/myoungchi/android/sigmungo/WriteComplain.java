package com.myoungchi.android.sigmungo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.myoungchi.android.sigmungo.adapter.WCFirstAdapter;
import com.myoungchi.android.sigmungo.adapter.WCSecondAdapter;
import com.myoungchi.android.sigmungo.adapter.WCThirdAdapter;

/**
 * Created by geni on 2017. 9. 20..
 */

public class WriteComplain extends AppCompatActivity {
    private Button submitBtn;
    private RecyclerView firstKeyword;
    private RecyclerView secondKeyword;
    private RecyclerView thirdKeyword;

    private WCFirstAdapter wcFirstAdapter;
    private WCSecondAdapter wcSecondAdapter;
    private WCThirdAdapter wcThirdAdapter;

    private String[] firstKeywords = {"음식의(이)", "음식점의(이)", "직원의(이)"};
    private String[][] secondKeywords = {
            {"간이", "온도가", "위생상태가 좋지 않아요(이물질 발견)", "종류가", "양이", "가격", "신선도가", "향이", "식감이", "맛이"},
            {"바닥이", "앉을 공간이 부족해요", "위생상태가 좋지 않아요(이물질 발견)", "와이파이가 없어요", "조명이", "분위기가", "냉난방이", "대기시간이 너무 길어요", "벌레가 너무 많아요"},
            {"업무시간에", "위생상태가 좋지 않아요", "고객응대가", "옷차림이"}
    };
    private String[][][] thirdKeywords = {
            {
                    {"짜요", "싱거워요"},
                    {"차가워요", "미지근해요", "뜨거워요"},
                    {""},
                    {"많아요", "적어요"},
                    {"많아요", "적어요"},
                    {"비싸요", "양에 비해 비싸요"},
                    {},
                    {"강해요", "약해요"},
                    {"질겨요", "흐물거려요", "딱딱해요"},
                    {"매워요", "달아요", "써요", "떫어요", "셔요", "느끼해요"}
            },
            {
                    {"더러워요", "미끄러워요"},
                    {""},
                    {""},
                    {""},
                    {"너무 밝아요", "어두워요"},
                    {"어두워요", "칙칙해요"},
                    {"잘 안돼서 더워요", "질 안돼서 추워요"},
                    {""},
                    {""}
            },
            {
                    {"욕설을 사용해요", "핸드폰을 과다 사용해요"},
                    {""},
                    {"불친절해요", "늦어요"},
                    {"단정하지 않아요", "청결하지 못해요", "선정적이에요"}
            }
    };

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_complain);
        mIntent = getIntent();

        firstKeyword = (RecyclerView) findViewById(R.id.firstKeyword);
        secondKeyword = (RecyclerView) findViewById(R.id.secondKeyword);
        thirdKeyword = (RecyclerView) findViewById(R.id.thirdKeyword);
        Log.d("contentid", mIntent.getStringExtra("contentid"));
        wcFirstAdapter = new WCFirstAdapter(firstKeywords, secondKeywords, thirdKeywords, this, secondKeyword, thirdKeyword, mIntent.getStringExtra("contentid"));

        firstKeyword.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        firstKeyword.setAdapter(wcFirstAdapter);
    }

    //툴바에서 back버튼을 클릭할시에 랜딩으로 돌아가는 코드
    public void onBackBtnClicked(View v){
        startActivity(new Intent(getApplicationContext(), RestaurantDetail.class));
        finish();
    }
}
