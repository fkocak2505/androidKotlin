package com.onedio.androidside.mvp.presenter.dashboard.homeFragment

import android.view.View
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig

interface IArticlesFragmentViewPresenter {

    fun getWidgetConfig()

    fun getArticleFeedCategoryWidgetDataWidthTag4OnedioButton(wName: String, targetId: String, page: Int, perPage:Int)

    fun getArticleSliderData()

    fun getArticlesByTagId(wName: String, targetId: String, page: Int, perPage: Int)

    fun getArticlesFeed(page: Int, perPage: Int)

}