package com.example.fkocak.undpveriambariguncelle.retrofit;

import java.util.ArrayList;

public class DenemSingleton {
    private static DenemSingleton instance = null;

    ArrayList<String> cookies;

    public ArrayList<String> getCookies() {
        return cookies;
    }

    public void setCookies(ArrayList<String> cookies) {
        this.cookies = cookies;
    }

    public static DenemSingleton getInstance(){
        if(instance == null) instance = new DenemSingleton();
        return instance;
    }
}
