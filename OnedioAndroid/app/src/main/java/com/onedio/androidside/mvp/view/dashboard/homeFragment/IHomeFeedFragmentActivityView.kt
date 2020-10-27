package com.onedio.androidside.mvp.view.dashboard.homeFragment

import android.view.View
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.WidgetsData
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel

interface IHomeFeedFragmentActivityView {

    fun setEmojiProvider()

    fun setWidgetPlace()

    fun initHomeFeedFragmentComponent()

    fun setComponentTypeFace()

    fun getWidgetConfig()

    fun handleWidgetConfigData(response4WidgetConfig: Response4WidgetConfig)

    fun getArticleSliderData()

    fun handleArticleSliderData(response4ArticleSlider: Response4ArticleSlider)

    fun clickMoreDataView(view: View, widgetName: String, widgetId: String, functionName: String)

    fun sumOfWidgetCount(widgets: MutableList<WidgetsData>): Int

    fun getArticlesFeed(response4WidgetConfig: Response4WidgetConfig, page: Int, perPage: Int)

    fun handleOnedioButtonData(wName: String, response4ArticlesFeed: Response4ArticlesFeed)

    fun handleArticlesFeedData(
        response4WidgetConfig: Response4WidgetConfig,
        response4ArticlesFeed: Response4ArticlesFeed
    )

    fun prepareWidgetDataWithTargetId(response4WidgetConfig: Response4WidgetConfig)

    fun getWidgetDataWithTargetId(wName: String, targetId: String, page: Int, perPage: Int)

    fun handleArticleFeedCategoryWidgetDataWithTagId(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    )

    fun getCategoryWidgetDataWithTargetId(
        wName: String,
        categoryId: String,
        page: Int,
        perPage: Int
    )

    fun handleArticleFeedCategoryWidgetDataWithCategoryId(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    )

    fun getCategoryWidgetDataByName(wName: String, categoryId: String, page: Int, perPage: Int)

    fun handleCategoryDataByName(isFirst: Boolean, response4FeedModel: Response4FeedModel)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj, isContinue: Boolean)

    fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    )

    fun prepareSliderListData(
        beforeWidgetShowCount: Int,
        currWidgetShowCount: Int,
        response4ArticleSlider: Response4ArticleSlider
    )

    fun prepareHugeCardWithPopularComment()

    fun prepareEditorChoosing4GridWidgetData(wName: String, response4ArticlesFeed: Response4ArticlesFeed)

    fun prepare4TestWidgetData(wName: String, response4ArticlesFeed: Response4ArticlesFeed)

    fun prepareVideo2GridWidgetData(response4ArticlesFeed: Response4ArticlesFeed)

    fun prepare4SportsWidgetData(response4ArticlesFeed: Response4ArticlesFeed)

    fun addFavoriteArticle(legacyId: Long, viewText: View, viewIcon: View)

    fun handleAddFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle,
        viewText: View,
        viewIcon: View,
        legacyId: Long
    )

    fun deleteFavoriteArticle(legacyId: Long, viewText: View, viewIcon: View)

    fun handleDeleteFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle,
        viewText: View,
        viewIcon: View,
        legacyId: Long
    )

    fun handleArticleFeedData4Infinite(response4ArticlesFeed: Response4ArticlesFeed)

    fun prepareHugeCardArticleData(
        beforeWidgetShowCount: Int,
        currWidgetShowCount: Int,
        isInfinite: Boolean,
        articleFeed: MutableList<FeedArticleModel>,
        isSlider: Boolean
    ): MutableList<HugeCardModel>

}