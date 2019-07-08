package tr.com.fkocak.kotlinretrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        val retrofit : Retrofit? = null
        fun getClient(baseURL : String): Retrofit = if(retrofit == null)
            Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        else
            retrofit
        }
}