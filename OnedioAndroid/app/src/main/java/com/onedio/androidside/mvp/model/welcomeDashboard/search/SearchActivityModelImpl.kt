package com.onedio.androidside.mvp.model.welcomeDashboard.search

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.Response4MostSharedArticle

class SearchActivityModelImpl :
    ISearchActivityModel {

    private lateinit var iApiService: IApiService


    override fun initApiService() {
        iApiService = ApiUtils.getAPIServiceV2TestCluster()
    }

    override fun initApiServiceOnedio() {
        iApiService = ApiUtils.getAPIServiceOnedio()
    }

    override fun initApiServiceNew() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun getMostSharedArticleNew(iRequestListener: IRequestListener<Response4ArticlesFeed>) {
        initApiServiceNew()

        iApiService.getMostPopularArticle().enqueue(object : retrofit2.Callback<Response4ArticlesFeed>{
            override fun onFailure(call: Call<Response4ArticlesFeed>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4ArticlesFeed>?,
                response: Response<Response4ArticlesFeed>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getMostSharedArticle(iRequestListener: IRequestListener<Response4MostSharedArticle>) {
        initApiService()

        iApiService.getMostSharedArticle()
            .enqueue(object : retrofit2.Callback<Response4MostSharedArticle> {
                override fun onFailure(call: Call<Response4MostSharedArticle>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4MostSharedArticle>?,
                    response: Response<Response4MostSharedArticle>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }


    override fun getSearchData(
        type: String,
        keyword: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<String>
    ) {

        initApiServiceOnedio()

        iApiService.getSearchData(type, keyword, page, perPage).enqueue(object : retrofit2.Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })

    }

}