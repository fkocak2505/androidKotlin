package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ICategoryFeedActivityView {

    fun initCategoryFeedComponent()

    fun getArticleWithCategoryId(categoryId: String, page: Int, perPage: Int, dateFilterType: String, sortType: String)

    //fun handleArticleFeedDataByCategoryId(response4FeedModel: Response4FeedModel)

    fun handleArticleFeedDataByCategoryIdNew(response4ArticlesFeed: Response4ArticlesFeed)

    fun showLoading()

    fun hideLoading()

    fun showLoading4List()

    fun hideLoading4List()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)




}