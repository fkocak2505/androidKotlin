package com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import retrofit2.Call
import retrofit2.Response

class BottomSheetFragmentModel: IBottomSheetFragmentModel {

    private lateinit var iApiService: IApiService

    override fun initApiService4ArticleFeed() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun getArticleFeedCategoryWidgetDataWidthTagId(
        targetId: String,
        page: Int,
        perPage: Int,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {

        initApiService4ArticleFeed()

        iApiService.getArticleFeedCategoryWidgetDataWidthTagId(targetId, page, perPage)
            .enqueue(object : retrofit2.Callback<Response4ArticlesFeed>{
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