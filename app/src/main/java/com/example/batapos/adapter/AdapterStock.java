package com.example.batapos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batapos.model.Ram;
import com.example.batapos.model.Stock;

import java.util.ArrayList;

public class AdapterStock extends RecyclerView.Adapter<AdapterStock.StockViewHolder> {
    private ArrayList<Stock> stocks;

    public AdapterStock(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public AdapterStock.StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.batapos.R.layout.stock_item, parent, false);
        return new AdapterStock.StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        holder.tv_name.setText(stocks.get(position).getName());
        holder.tv_version.setText(stocks.get(position).getVer());
        holder.tv_api_level.setText(stocks.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    public class StockViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name,tv_version,tv_api_level;
        public StockViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(com.example.batapos.R.id.tv_name);
            tv_version = view.findViewById(com.example.batapos.R.id.tv_version);
            tv_api_level = view.findViewById(com.example.batapos.R.id.tv_app_level);
        }
    }
}
