package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreContent

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article

class MoreContentActivityModelImpl:
    IMoreContentActivityModel {
    private lateinit var iApiService: IApiService

    override fun initApiService() {
        iApiService = ApiUtils.getAPIServiceOnedio()
    }

    override fun filterSearchedContentByParams(
        searchedWord: String,
        page: Int,
        perPage: Int,
        date: String,
        sort: String?,
        iRequestListener: IRequestListener<Response4Article>
    ) {

        initApiService()

        iApiService.filterSearchedContentByParams(searchedWord, page, perPage, date, sort!!).enqueue(object : retrofit2.Callback<Response4Article>{
            override fun onFailure(call: Call<Response4Article>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4Article>?,
                response: Response<Response4Article>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })


    }
}