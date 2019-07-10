package com.example.fkocak.undpveriambariguncelle.retrofit;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        //HashSet<String> cookies = new HashSet<>();
        ArrayList<String> cookies = new ArrayList<>();
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
        }

        DenemSingleton.getInstance().setCookies(cookies);

        return originalResponse;
    }
}