package com.example.batapos.Rest;

import com.example.batapos.model.GetRam;
import com.example.batapos.model.GetStock;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("android/jsonandroid")
    Call<GetRam> getRam();

    @GET("android/jsonandroid")
    Call<GetStock> getStock();
}
