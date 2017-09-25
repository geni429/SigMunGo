package com.myoungchi.android.sigmungo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.R;
import com.myoungchi.android.sigmungo.WriteComplain;
import com.myoungchi.android.sigmungo.WriteResult;

/**
 * Created by geni on 2017. 9. 20..
 */

public class WCSecondAdapter extends RecyclerView.Adapter<WCSecondAdapter.ViewHolder> {
    private String[] secondKeywords;
    private String[][][] thirdKeywords;
    private RecyclerView thirdKeyword;
    private int mIndex;
    private Activity mContext;
    private String firstKeyordContent;
    private String mContentId;

    public WCSecondAdapter(String[] secondKeywords, String[][][] thirdKeywords, RecyclerView thirdKeyword, int index, Activity context, String firstKeywordContent, String contentid){
        this.secondKeywords = secondKeywords;
        this.thirdKeywords = thirdKeywords;
        this.thirdKeyword = thirdKeyword;
        this.mIndex = index;
        this.mContext = context;
        this.firstKeyordContent = firstKeywordContent;
        this.mContentId = contentid;
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
                if(thirdKeywords[mIndex][position][0].equals("")){
                    Log.d("thirdKeyword", thirdKeywords[mIndex][position][0]);
                    Intent intent = new Intent(mContext, WriteResult.class);
                    intent.putExtra("firstKeywordContent", firstKeyordContent);
                    intent.putExtra("secondKeywordContent", secondKeywords[position]);
                    intent.putExtra("thirdKeywordContent", "");
                    intent.putExtra("contentid", mContentId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    mContext.finish();
                } else {
                    WCThirdAdapter wcThirdAdapter = new WCThirdAdapter(thirdKeywords[mIndex][position], firstKeyordContent, secondKeywords[position], mContext, mContentId);
                    thirdKeyword.setLayoutManager(new LinearLayoutManager(mContext));
                    thirdKeyword.setAdapter(wcThirdAdapter);
                }
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
