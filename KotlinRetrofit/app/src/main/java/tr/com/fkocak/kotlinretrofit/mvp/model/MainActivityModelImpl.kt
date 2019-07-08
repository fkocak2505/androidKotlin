package tr.com.fkocak.kotlinretrofit.mvp.model

import retrofit2.Call
import retrofit2.Response
import tr.com.fkocak.kotlinretrofit.IRequestResultListener
import tr.com.fkocak.kotlinretrofit.api.ApiService
import tr.com.fkocak.kotlinretrofit.api.ApiUtils
import tr.com.fkocak.kotlinretrofit.mvp.model.responseModel.Hero

class MainActivityModelImpl : IMainActivityModel {

    var apiService : ApiService? = null

    override fun initApiService() {
        apiService = ApiUtils.getAPIService()
    }

    override fun getHeroes(iRequestResultListener: IRequestResultListener<List<Hero>>) {

        initApiService()

        apiService?.getHeroes()?.enqueue(object : retrofit2.Callback<List<Hero>>{
            override fun onFailure(call: Call<List<Hero>>?, t: Throwable?) {
                iRequestResultListener.onFail()
            }

            override fun onResponse(call: Call<List<Hero>>?, response: Response<List<Hero>>?) {
                when(response?.isSuccessful){
                    true -> iRequestResultListener.onSuccess(response)
                    else -> iRequestResultListener.onFail()
                }
            }
        })
    }
}