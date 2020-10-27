package com.mobilhanem.retrofitloginpost.model;

/**
 * Created by alper on 16/02/17.
 */

public class UserInfo {

    public String islem;
    public String kullaniciAdi;
    public String sifre;

    public UserInfo(String islem,String kullaniciAdi,String sifre){

        this.islem = islem;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;

    }
}
