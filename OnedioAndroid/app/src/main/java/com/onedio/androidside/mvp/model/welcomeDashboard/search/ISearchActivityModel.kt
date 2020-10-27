package com.onedio.androidside.mvp.model.welcomeDashboard.search

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.Response4MostSharedArticle

interface ISearchActivityModel {

    fun initApiService()

    fun initApiServiceNew()

    fun initApiServiceOnedio()

    fun getMostSharedArticleNew(iRequestListener: IRequestListener<Response4ArticlesFeed>)

    fun getMostSharedArticle(iRequestListener: IRequestListener<Response4MostSharedArticle>)

    fun getSearchData(type: String, keyword: String, page: Int, perPage: Int, iRequestListener: IRequestListener<String>)

}