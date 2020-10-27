package com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.food

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ICategoryFeedFoodActivityModel {

    fun initAPIService()

    fun getCategoryFeedFoodData(categoryId: String, page: Int, perPage: Int, dateFilterType: String, sortType: String, iRequestListener: IRequestListener<Response4FeedModel>)

    fun getCategoryFeedFoodDataNew(categoryId: String, page: Int, perPage: Int, dateFilterType: String, sortType: String, iRequestListener: IRequestListener<Response4ArticlesFeed>)

}