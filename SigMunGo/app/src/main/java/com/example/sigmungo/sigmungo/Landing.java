package com.example.sigmungo.sigmungo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.sigmungo.sigmungo.Adapter.LandingAdapter;

/**
 * Created by geni on 2017. 7. 24..
 */

public class Landing extends AppCompatActivity {
    ViewPager pager;
    Button btnGroup[] = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);
        pager = (ViewPager)findViewById(R.id.landing_pager);
        btnGroup[0] = (Button)findViewById(R.id.pager_button_1);
        btnGroup[1] = (Button)findViewById(R.id.pager_button_2);
        btnGroup[2] = (Button)findViewById(R.id.pager_button_3);
        btnGroup[3] = (Button)findViewById(R.id.pager_button_4);

        LandingAdapter adapter = new LandingAdapter(getLayoutInflater());
        pager.setAdapter(adapter);
    }

//    private class PagerTask extends AsyncTask{
//        @Override
//        protected Object doInBackground(Object[] params) {
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            LandingAdapter adapter = new LandingAdapter(getLayoutInflater(), getApplicationContext());
//            pager.setAdapter(adapter);
//            pager.setOffscreenPageLimit(4);
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            super.onPostExecute(o);
//        }
//
//        @Override
//        protected void onProgressUpdate(Object[] values) {
//            super.onProgressUpdate(values);
//        }
//    }
//
//    class PagerThread extends Thread{
//        public void run(){
//
//            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                    if(state == 2){
//                        for(Button pagerButtons : btnGroup){
//                            pagerButtons.setBackgroundResource(R.drawable.pager_button_off);
//                        }
//                        btnGroup[pager.getCurrentItem()].setBackgroundResource(R.drawable.pager_button_on);
//                    }
//                }
//            });
//        }
//    }
}
