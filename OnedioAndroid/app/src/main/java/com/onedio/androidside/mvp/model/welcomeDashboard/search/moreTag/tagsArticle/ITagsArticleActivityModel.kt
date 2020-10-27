package com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag.tagsArticle

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface ITagsArticleActivityModel {

    fun initApiServiceTestCluster()

    fun initApiServiceNew()

    fun getTagsArticle(
        tagsId: String, page: Int, perPage: Int, dateFilterType: String,
        sortType: String, iRequestListener: IRequestListener<Response4ArticlesFeed>
    )

}