package com.myoungchi.android.sigmungo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.R;
import com.myoungchi.android.sigmungo.WriteResult;

/**
 * Created by geni on 2017. 9. 20..
 */

public class WCThirdAdapter extends RecyclerView.Adapter<WCThirdAdapter.ViewHolder> {
    private String[] thirdKeywords;
    private String firstKeywordContent;
    private String secondKeywordContent;
    private Context mContext;

    public WCThirdAdapter(String[] thirdKeywords, String firstKeywordContent, String secondKeywordContent, Context context){
        this.thirdKeywords = thirdKeywords;
        this.firstKeywordContent = firstKeywordContent;
        this.secondKeywordContent = secondKeywordContent;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyword_btn, parent, false);
        return new WCThirdAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.keywordBtn.setText(thirdKeywords[position]);
        holder.keywordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WriteResult.class);
                intent.putExtra("firstKeywordContent", firstKeywordContent);
                intent.putExtra("secondKeywordContent", secondKeywordContent);
                intent.putExtra("thirdKeywordContent", thirdKeywords[position]);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thirdKeywords.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keywordBtn;
        public ViewHolder(View view) {
            super(view);
            keywordBtn = (TextView) view.findViewById(R.id.keywordBtn);
        }
    }
}
