package com.example.batapos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batapos.R;
import com.example.batapos.model.Ram;

import java.util.ArrayList;
import java.util.List;

public class AdapterRam extends RecyclerView.Adapter<AdapterRam.MyViewHolder> {
    private ArrayList<Ram> android;

    public AdapterRam(ArrayList<Ram> android) {
        this.android = android;
    }

    @Override
    public AdapterRam.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_data_produk, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(android.get(position).getName());
        holder.tv_version.setText(android.get(position).getVer());
        holder.tv_api_level.setText(android.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name,tv_version,tv_api_level;
        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_version = view.findViewById(R.id.tv_version);
            tv_api_level = view.findViewById(R.id.tv_app_level);
        }
    }
}
