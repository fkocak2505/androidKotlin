package com.onedio.androidside.mvp.model.dashboard.homeFragment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetDataWithTagId.Response4WidgetDataWithTagId
import retrofit2.Call
import retrofit2.Response

class HomeFeedFragmentActivityModelImpl :
    IHomeFeedFragmentActivityModel {

    private lateinit var iApiService: IApiService

    override fun initStaticApiService() {
        iApiService = ApiUtils.getAPIServiceStaticOnedio()
    }

    override fun initApiService4ArticleFeed() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun initApiServiceV2TestCluster() {
        iApiService = ApiUtils.getAPIServiceV2TestCluster()
    }

    override fun initAPIServiceV2() {
        iApiService = ApiUtils.getAPIServiceV2()
    }

    override fun getWidgetConfig(iRequestListener: IRequestListener<Response4WidgetConfig>) {

        //initStaticApiService()
        initApiService4ArticleFeed()

        iApiService.getWidgetConfig().enqueue(object : retrofit2.Callback<Response4WidgetConfig> {
            override fun onFailure(call: Call<Response4WidgetConfig>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4WidgetConfig>?,
                response: Response<Response4WidgetConfig>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getSliderData(iRequestListener: IRequestListener<Response4ArticleSlider>) {
        initApiService4ArticleFeed()

        iApiService.getArticleSliderData()
            .enqueue(object : retrofit2.Callback<Response4ArticleSlider> {
                override fun onFailure(call: Call<Response4ArticleSlider>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4ArticleSlider>?,
                    response: Response<Response4ArticleSlider>?
                ) {

                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }

                }
            })
    }

    override fun getArticlesFeed(
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {
        initApiService4ArticleFeed()

        //val accessToken = OnedioSingletonPattern.instance.getAccessToken()
        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getArticlesFeed("Bearer $accessToken", page, perPage)
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

    override fun getWidgetDataWithTagId(
        targetId: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4WidgetDataWithTagId>
    ) {

        initApiServiceV2TestCluster()

        iApiService.getWidgetDataWithTagId(targetId, page, perPage)
            .enqueue(object : retrofit2.Callback<Response4WidgetDataWithTagId> {
                override fun onFailure(call: Call<Response4WidgetDataWithTagId>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4WidgetDataWithTagId>?,
                    response: Response<Response4WidgetDataWithTagId>?
                ) {

                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun getArticleFeedCategoryWidgetDataWidthTagId(
        targetId: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {

        initApiService4ArticleFeed()

        iApiService.getArticleFeedCategoryWidgetDataWidthTagId(targetId, page, perPage)
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

    override fun getArticleFeedCategoryWidgetDataWithCategoryId(
        categoryId: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {
        initApiService4ArticleFeed()

        iApiService.getArticleFeedCategoryWidgetDataWithCategoryId(
            categoryId,
            page,
            perPage,
            "recent",
            "1month"
        )
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

    override fun getCategoryWidgetDataByName(
        targetId: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4FeedModel>
    ) {

        initAPIServiceV2()

        iApiService.getCategoryWidgetDataByName(targetId, page, perPage)
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

    override fun addFavorite(
        legacyId: Long,
        iRequestListener: IRequestListener<Response4AddFavoriteArticle>
    ) {
        initApiService4ArticleFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.addFavoriteArticle(
            "Bearer $accessToken",
            legacyId
        ).enqueue(object : retrofit2.Callback<Response4AddFavoriteArticle> {
            override fun onFailure(call: Call<Response4AddFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4AddFavoriteArticle>?,
                response: Response<Response4AddFavoriteArticle>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun deleteFavorite(
        legacyId: Long,
        iRequestListener: IRequestListener<Response4AddFavoriteArticle>
    ) {
        initApiService4ArticleFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.deleteFavoriteArticle(
            "Bearer $accessToken",
            legacyId
        ).enqueue(object : retrofit2.Callback<Response4AddFavoriteArticle> {
            override fun onFailure(call: Call<Response4AddFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4AddFavoriteArticle>?,
                response: Response<Response4AddFavoriteArticle>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }
}