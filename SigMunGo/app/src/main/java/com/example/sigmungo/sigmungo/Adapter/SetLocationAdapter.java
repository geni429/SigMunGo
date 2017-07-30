package com.example.sigmungo.sigmungo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sigmungo.sigmungo.R;

import java.util.List;

/**
 * Created by geni on 2017. 7. 29..
 */

public class SetLocationAdapter extends RecyclerView.Adapter<SetLocationAdapter.ViewHolder>  {
    private List<String> locationNames;

    public SetLocationAdapter(List<String> locationNames){
        this.locationNames = locationNames;
    }

    @Override
    public SetLocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("setLocation parent", parent+"");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.locations_list, parent, false);
        SetLocationAdapter.ViewHolder holder = new SetLocationAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(SetLocationAdapter.ViewHolder holder, int position) {
        holder.location_name.setText(this.locationNames.get(position));
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
