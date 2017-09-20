package com.myoungchi.android.sigmungo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.R;

/**
 * Created by geni on 2017. 9. 20..
 */

public class WCSecondAdapter extends RecyclerView.Adapter<WCSecondAdapter.ViewHolder> {
    private String[] secondKeywords;
    private String[][][] thirdKeywords;
    private RecyclerView thirdKeyword;
    private int mIndex;
    private Context mContext;
    public WCSecondAdapter(String[] secondKeywords, String[][][] thirdKeywords, RecyclerView thirdKeyword, int index, Context context){
        this.secondKeywords = secondKeywords;
        this.thirdKeywords = thirdKeywords;
        this.thirdKeyword = thirdKeyword;
        this.mIndex = index;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyword_btn, parent, false);
        return new WCSecondAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.keywordBtn.setText(secondKeywords[position]);
        holder.keywordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WCThirdAdapter wcThirdAdapter = new WCThirdAdapter(thirdKeywords[mIndex][position]);
                thirdKeyword.setLayoutManager(new LinearLayoutManager(mContext));
                thirdKeyword.setAdapter(wcThirdAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return secondKeywords.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keywordBtn;
        public ViewHolder(View view) {
            super(view);
            keywordBtn = (TextView)view.findViewById(R.id.keywordBtn);
        }
    }
}
