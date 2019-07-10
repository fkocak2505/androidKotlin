package com.example.fkocak.undpveriambariguncelle.retrofit.commandPattern;

import retrofit2.Call;
import retrofit2.Callback;

public class RetrofitCallReceiver {
    public <T> void callServiceAsync(Call<T> tCall, Callback<T> callback){
        tCall.enqueue( callback );
    }
}
