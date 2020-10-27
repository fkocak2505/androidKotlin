package com.onedio.androidside.mvp.view.dashboard.trendingFragment

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ITrendingFragmentActivityView {

    fun initTrendingFragmentComponent()

    fun handleTrendDataNew(response4ArticlesFeed: Response4ArticlesFeed)

    //fun handleTrendData(response4FeedModel: Response4FeedModel)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    )


}