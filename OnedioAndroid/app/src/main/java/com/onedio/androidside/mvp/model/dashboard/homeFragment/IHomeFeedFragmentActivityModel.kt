package com.onedio.androidside.mvp.model.dashboard.homeFragment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetDataWithTagId.Response4WidgetDataWithTagId

interface IHomeFeedFragmentActivityModel {

    fun initStaticApiService()

    fun initApiService4ArticleFeed()

    fun initApiServiceV2TestCluster()

    fun initAPIServiceV2()

    fun getWidgetConfig(iRequestListener: IRequestListener<Response4WidgetConfig>)

    fun getSliderData(iRequestListener: IRequestListener<Response4ArticleSlider>)

    fun getArticlesFeed(page: Int, perPage: Int, iRequestListener: IRequestListener<Response4ArticlesFeed>)

    fun getWidgetDataWithTagId(targetId: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4WidgetDataWithTagId>)

    fun getArticleFeedCategoryWidgetDataWidthTagId(targetId: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4ArticlesFeed>)

    fun getArticleFeedCategoryWidgetDataWithCategoryId(categoryId: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4ArticlesFeed>)

    fun getCategoryWidgetDataByName(targetId: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4FeedModel>)

    fun addFavorite(legacyId: Long, iRequestListener: IRequestListener<Response4AddFavoriteArticle>)

    fun deleteFavorite(legacyId: Long, iRequestListener: IRequestListener<Response4AddFavoriteArticle>)

}