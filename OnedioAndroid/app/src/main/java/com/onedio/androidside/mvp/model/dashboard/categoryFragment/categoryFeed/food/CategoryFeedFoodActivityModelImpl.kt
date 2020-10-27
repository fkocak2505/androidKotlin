package com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.food

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

class CategoryFeedFoodActivityModelImpl :
    ICategoryFeedFoodActivityModel {

    private lateinit var iApiService: IApiService

    override fun initAPIService() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun getCategoryFeedFoodData(
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        iRequestListener: IRequestListener<Response4FeedModel>
    ) {

        initAPIService()

        iApiService.getCategoryWidgetDataWithTargetId(categoryId, page, perPage, dateFilterType, sortType).enqueue(object : retrofit2.Callback<Response4FeedModel>{
            override fun onFailure(call: Call<Response4FeedModel>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4FeedModel>?,
                response: Response<Response4FeedModel>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getCategoryFeedFoodDataNew(
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {

        initAPIService()

        iApiService.getArticleFeedCategoryWidgetDataWithCategoryId(categoryId, page, perPage, dateFilterType, sortType).enqueue(object : retrofit2.Callback<Response4ArticlesFeed>{
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
}