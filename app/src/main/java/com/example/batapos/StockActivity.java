package com.example.batapos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.batapos.Rest.ApiInterface;
import com.example.batapos.adapter.AdapterRam;
import com.example.batapos.adapter.AdapterStock;
import com.example.batapos.model.GetRam;
import com.example.batapos.model.GetStock;
import com.example.batapos.model.Ram;
import com.example.batapos.model.Stock;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StockActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Stock> stockArrayList;
    private AdapterStock mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        initViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Persediaan");
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.stock_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<GetStock> call = request.getStock();
        call.enqueue(new Callback<GetStock>() {
            @Override
            public void onResponse(Call<GetStock> call, Response<GetStock> response) {
                GetStock getStock = response.body();
                stockArrayList = new ArrayList<>(Arrays.asList(((GetStock) getStock).getPersediaan()));
                mAdapter = new AdapterStock(stockArrayList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetStock> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
