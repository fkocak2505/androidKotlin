package com.mobilhanem.retrofitloginpost.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by alper on 13/02/17.
 */

public class UserResponse implements Serializable {

    @SerializedName("islem")
    private String islem;
    @SerializedName("method")
    private String method;
    @SerializedName("hata")
    private String hata;
    @SerializedName("giris")
    private boolean giris;
    @SerializedName("bilgiler")
    private Bilgiler bilgiler;

    public String getIslem() {
        return islem;
    }

    public String getMethod() {
        return method;
    }

    public boolean isGiris() {
        return giris;
    }

    public String getHata() {
        return hata;
    }

    public Bilgiler getBilgiler() {
        return bilgiler;
    }
}
