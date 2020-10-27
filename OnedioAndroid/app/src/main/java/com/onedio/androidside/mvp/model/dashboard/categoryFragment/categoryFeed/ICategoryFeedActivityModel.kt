package com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ICategoryFeedActivityModel {

    fun initAPIServiceV2TestCluster()

    fun initApiSeriviceFeedUrl()

    fun getArticleFeedByCategoryId(
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        iRequestListener: IRequestListener<Response4FeedModel>
    )

    fun getArticleFeedByCategoryIdNew(
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    )

}