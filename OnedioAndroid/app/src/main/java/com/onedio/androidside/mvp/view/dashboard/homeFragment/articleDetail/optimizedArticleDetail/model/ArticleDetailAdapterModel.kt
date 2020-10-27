package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.model

import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel

data class ArticleDetailAdapterModel(
    val type: String,
    val data: Response4ArticleFeedDetails,
    val widgetData:  FeedArticleModel? = null,
    var isAdLoaded: Boolean? = null
)