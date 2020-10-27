package com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.video

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ICategoryFeedVideoActivityModel {

    fun initAPIServiceV2TestCluster()

    fun getVideoArticleData(categoryId: String, page: Int, perPage: Int, dateFilterType: String, sortType: String, iRequestListener: IRequestListener<Response4FeedModel>)

    fun getVideoArticleDataNew(categoryId: String, page: Int, perPage: Int, dateFilterType: String, sortType: String, iRequestListener: IRequestListener<Response4ArticlesFeed>)



}