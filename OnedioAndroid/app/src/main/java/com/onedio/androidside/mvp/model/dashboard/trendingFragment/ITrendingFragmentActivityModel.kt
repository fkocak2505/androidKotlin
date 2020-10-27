package com.onedio.androidside.mvp.model.dashboard.trendingFragment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ITrendingFragmentActivityModel {

    fun initApiUtilsTestCluster()

    fun initApiUtilsFeed()

    fun getTrendDataNew(page: Int, perPage: Int, iRequestListener: IRequestListener<Response4ArticlesFeed>)

    fun getTrendData(page: Int, perPage: Int, iRequestListener: IRequestListener<Response4FeedModel>)



}