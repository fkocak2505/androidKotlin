package tr.gov.saglik.uets.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import tr.gov.saglik.uets.api.convertor.CustomRetrofitConverterFactory;


/**
 * Created by fatihkocak on 25.04.2019.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        //if (retrofit==null) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofit = null;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(CustomRetrofitConverterFactory.create())
                .build();

        //}
        return retrofit;
    }
}
