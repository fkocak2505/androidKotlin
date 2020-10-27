package com.onedio.androidside.mvp.model.dashboard.trendingFragment

import android.content.Context
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.AnalyticsApplication
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.singleton.OnedioSingletonPattern
import retrofit2.Call
import retrofit2.Response

class TrendingFragmentActivityModelImpl :
    ITrendingFragmentActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiUtilsTestCluster() {
        iApiService = ApiUtils.getAPIServiceV2TestCluster()
    }

    override fun initApiUtilsFeed() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun getTrendDataNew(
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {

        initApiUtilsFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getTrendData("Bearer $accessToken", page)
            .enqueue(object : retrofit2.Callback<Response4ArticlesFeed> {
                override fun onFailure(call: Call<Response4ArticlesFeed>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4ArticlesFeed>?,
                    response: Response<Response4ArticlesFeed>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun getTrendData(
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4FeedModel>
    ) {

        initApiUtilsTestCluster()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getArticle("Bearer $accessToken", page, perPage)
            .enqueue(object : retrofit2.Callback<Response4FeedModel> {
                override fun onFailure(call: Call<Response4FeedModel>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4FeedModel>?,
                    response: Response<Response4FeedModel>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })

    }

}