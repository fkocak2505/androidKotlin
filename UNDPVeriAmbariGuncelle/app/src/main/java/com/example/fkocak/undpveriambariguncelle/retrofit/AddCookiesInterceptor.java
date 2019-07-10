package com.example.fkocak.undpveriambariguncelle.retrofit;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        ArrayList<String> cookies = DenemSingleton.getInstance().getCookies();
        for (String cookie : cookies) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}