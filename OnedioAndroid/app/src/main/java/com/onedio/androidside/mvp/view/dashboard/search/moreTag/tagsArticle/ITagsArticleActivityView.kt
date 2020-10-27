package com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ITagsArticleActivityView {

    fun initTagsActivityComponent()

    fun handleTagsArticle(response4TagArticle: Response4ArticlesFeed)

    fun showLoading()

    fun hideLoading()

    fun showLoading4List()

    fun hideLoading4List()

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