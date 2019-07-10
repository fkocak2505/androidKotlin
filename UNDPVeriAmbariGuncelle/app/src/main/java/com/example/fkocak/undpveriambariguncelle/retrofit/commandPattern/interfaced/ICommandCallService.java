package com.example.fkocak.undpveriambariguncelle.retrofit.commandPattern.interfaced;

import retrofit2.Call;
import retrofit2.Callback;

public interface ICommandCallService {
    public <T> void callAsync(Call<T> tCall, Callback<T> callback);
}
