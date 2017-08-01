package com.example.sigmungo.sigmungo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sigmungo.sigmungo.Adapter.LandingAdapter;

/**
 * Created by geni on 2017. 7. 24..
 */

public class Landing extends AppCompatActivity {
    ViewPager pager;
    Button btnGroup[] = new Button[4];
    TextView landingExplain;
    Button loginBtn;
    Button kakaoLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);
        pager = (ViewPager)findViewById(R.id.landing_pager);
        landingExplain = (TextView) findViewById(R.id.landing_explain);
        loginBtn = (Button)findViewById(R.id.login_btn);
        kakaoLoginBtn = (Button)findViewById(R.id.kakao_login_btn);
        btnGroup[0] = (Button)findViewById(R.id.pager_button_1);
        btnGroup[1] = (Button)findViewById(R.id.pager_button_2);
        btnGroup[2] = (Button)findViewById(R.id.pager_button_3);
        btnGroup[3] = (Button)findViewById(R.id.pager_button_4);

        PagerThread thread = new PagerThread();
        thread.start();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });
    }

    class PagerThread extends Thread{
        public void run(){
            LandingAdapter adapter = new LandingAdapter(getApplicationContext());
            pager.setAdapter(adapter);
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if(state == 2){
                        for(Button pagerButtons : btnGroup){
                            pagerButtons.setBackgroundResource(R.drawable.pager_button_off);
                        }
                        btnGroup[pager.getCurrentItem()].setBackgroundResource(R.drawable.pager_button_on);
                        switch (pager.getCurrentItem()){
                            case 0:
                                landingExplain.setText("더이상 참지마세요");
                                break;
                            case 1:
                                landingExplain.setText("솔직한 리뷰로 당신의 의견을 표출하세요");
                                break;
                            case 2:
                                landingExplain.setText("솔직담백한 고객들의 이야기");
                                break;
                            case 3:
                                landingExplain.setText("식문고로 당신을 초대합니다.");
                                break;
                            default: break;
                        }
                    }
                }
            });
        }
    }

    public void skipLogin(View v){
        startActivity(new Intent(getApplicationContext(), Main.class));
    }
}
