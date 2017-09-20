package com.myoungchi.android.sigmungo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myoungchi.android.sigmungo.R;

/**
 * Created by geni on 2017. 9. 20..
 */

public class WCThirdAdapter extends RecyclerView.Adapter<WCThirdAdapter.ViewHolder> {
    String[] thirdKeywords;
    public WCThirdAdapter(String[] thirdKeywords){
        this.thirdKeywords = thirdKeywords;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyword_btn, parent, false);
        return new WCThirdAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.keywordBtn.setText(thirdKeywords[position]);
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
