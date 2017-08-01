package com.example.sigmungo.sigmungo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sigmungo.sigmungo.Main;
import com.example.sigmungo.sigmungo.R;

import java.util.List;

/**
 * Created by geni on 2017. 7. 29..
 */

public class SetLocationAdapter extends RecyclerView.Adapter<SetLocationAdapter.ViewHolder>  {
    private List<String> locationNames;
    private Context mContext;

    public SetLocationAdapter(List<String> locationNames, Context context){
        this.locationNames = locationNames;
        this.mContext = context;
    }

    @Override
    public SetLocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locations_list, parent, false);
        SetLocationAdapter.ViewHolder holder = new SetLocationAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SetLocationAdapter.ViewHolder holder, final int position) {
        holder.location_name.setText(this.locationNames.get(position));
        holder.location_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Main.class);
                intent.putExtra("location_name", locationNames.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView location_name;

        public ViewHolder(View view){
            super(view);
            this.location_name = (TextView) view.findViewById(R.id.location_name);
        }
    }
}
