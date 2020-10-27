package com.onedio.androidside.mvp.presenter.dashboard.homeFragment

import android.view.View
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig

interface IHomeFeedFragmentActivityViewPresenter {

    fun getWidgetConfig()

    fun getArticleSliderData()

    fun getArticlesFeed(response4WidgetConfig: Response4WidgetConfig, page: Int, perPage: Int)

    fun getWidgetDataWithTagId(wName: String, targetId: String, page: Int, perPage:Int)

    fun getArticleFeedCategoryWidgetDataWidthTag4OnedioButton(wName: String, targetId: String, page: Int, perPage:Int)

    fun getArticleFeedCategoryWidgetDataWidthTagId(wName: String, targetId: String, page: Int, perPage: Int)

    fun getArticleFeedCategoryWidgetDataWithCategoryId(wName: String, categoryId: String, page: Int, perPage:Int)

    fun getCategoryWidgetDataByName(isFirst: Boolean, wName: String, targetId: String, page: Int, perPage:Int)

    fun addFavorite(legacyId: Long, viewText: View, viewIcon: View)

    fun deleteFavorite(legacyId: Long, viewText: View, viewIcon: View)

    fun getArticleData4Infinite(page: Int, perPage: Int, isProgressShow: Boolean)

}