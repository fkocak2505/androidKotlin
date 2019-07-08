package tr.com.fkocak.kotlinretrofit.api


import retrofit2.Call
import retrofit2.http.GET
import tr.com.fkocak.kotlinretrofit.mvp.model.responseModel.Hero

interface ApiService {
    @GET("marvel")
    fun getHeroes(): Call<List<Hero>>

}