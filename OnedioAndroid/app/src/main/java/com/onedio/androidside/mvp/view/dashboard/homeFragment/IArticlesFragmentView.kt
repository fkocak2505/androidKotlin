package com.onedio.androidside.mvp.view.dashboard.homeFragment

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig

interface IArticlesFragmentView {

    fun handleWidgetConfigData(response4WidgetConfig: Response4WidgetConfig)

    fun handleOnedioButtonData(wName: String, response4ArticlesFeed: Response4ArticlesFeed)

    fun handleArticleSliderData(response4ArticleSlider: Response4ArticleSlider)

    fun handleArticlesByTagId(
        wName: String,
        response4ArticlesFeed: Response4ArticlesFeed
    )

    fun handleArticlesFeedData(
        response4ArticlesFeed: Response4ArticlesFeed
    )

    fun showError(response4ErrorObj: Response4ErrorObj, isContinue: Boolean)

}