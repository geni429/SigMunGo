package com.myoungchi.android.sigmungo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.R;
import com.myoungchi.android.sigmungo.RestaurantDetail;

/**
 * Created by geni on 2017. 9. 20..
 */

public class WCFirstAdapter extends RecyclerView.Adapter<WCFirstAdapter.ViewHolder> {
    private WCSecondAdapter wcSecondAdapter;
    private WCThirdAdapter wcThirdAdapter;
    private RecyclerView secondKeyword;
    private RecyclerView thirdKeyword;
    private String[] firstKeywords;
    private String[][] secondKeywords;
    private String[][][] thirdKeywords;
    private Activity mContext;
    private String mContentId;


    public WCFirstAdapter(String[] firstKeywords,
                          String[][] secondKeywords,
                          String[][][] thirdKeywords,
                          Activity context,
                          RecyclerView secondKeyword,
                          RecyclerView thirdKeyword,
                          String contentId){
        this.secondKeyword = secondKeyword;
        this.thirdKeyword = thirdKeyword;
        this.firstKeywords = firstKeywords;
        this.secondKeywords = secondKeywords;
        this.thirdKeywords = thirdKeywords;
        this.mContext = context;
        this.mContentId = contentId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyword_btn, parent, false);
        return new WCFirstAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.keywordBtn.setText(firstKeywords[position]);
        holder.keywordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wcSecondAdapter = new WCSecondAdapter(secondKeywords[position], thirdKeywords, thirdKeyword, position, mContext, firstKeywords[position], mContentId);
                String[] dumy = {};
                wcThirdAdapter = new WCThirdAdapter(dumy, "", "", mContext, "");
                thirdKeyword.setLayoutManager(new LinearLayoutManager(mContext));
                thirdKeyword.setAdapter(wcThirdAdapter);
                secondKeyword.setLayoutManager(new LinearLayoutManager(mContext));
                secondKeyword.setAdapter(wcSecondAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return firstKeywords.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keywordBtn;
        public ViewHolder(View view) {
            super(view);
            keywordBtn = (TextView)view.findViewById(R.id.keywordBtn);
        }
    }
}
