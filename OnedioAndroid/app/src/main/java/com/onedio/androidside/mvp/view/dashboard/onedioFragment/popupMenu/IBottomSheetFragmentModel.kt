package com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface IBottomSheetFragmentModel {

    fun initApiService4ArticleFeed()

    fun getArticleFeedCategoryWidgetDataWidthTagId(targetId: String, page: Int, perPage: Int, iRequestListener: IRequestListener<Response4ArticlesFeed>)

}