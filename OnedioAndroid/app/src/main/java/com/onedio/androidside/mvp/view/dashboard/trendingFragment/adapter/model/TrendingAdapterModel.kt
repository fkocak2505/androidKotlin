package com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.model

import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel

data class TrendingAdapterModel(
    val type: String,
    val id: String,
    val legacyId: Long,
    val coverPhoto: String,
    val title: String,
    val categoryId: String,
    val categoryName: String,
    val categoryUrl: String,
    val showInWebView: Boolean,
    val redirectUrl: String,
    var isAdLoaded: Boolean? = null
)


