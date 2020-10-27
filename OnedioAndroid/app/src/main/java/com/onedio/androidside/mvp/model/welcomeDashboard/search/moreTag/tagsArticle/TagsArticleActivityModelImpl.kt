package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag.tagsArticle

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

class TagsArticleActivityModelImpl:
    ITagsArticleActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiServiceTestCluster() {
        iApiService = ApiUtils.getAPIServiceV2TestCluster()
    }

    override fun initApiServiceNew() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun getTagsArticle(
        tagsId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {

        initApiServiceNew()

        iApiService.getTagArticle(tagsId, page, perPage, dateFilterType, sortType).enqueue(object : retrofit2.Callback<Response4ArticlesFeed>{
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