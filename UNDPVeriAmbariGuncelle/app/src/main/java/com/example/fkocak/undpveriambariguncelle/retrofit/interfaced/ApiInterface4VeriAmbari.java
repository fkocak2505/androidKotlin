package com.example.fkocak.undpveriambariguncelle.retrofit.interfaced;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface4VeriAmbari {

    @GET("dispatch")
    Call<String> login(@Query("cmd") String cmd, @Query("jp") String jp);
}
