package com.onedio.androidside.mvp.view.dashboard.homeFragment

import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel

data class ArticlesFragmentAdapterModel(
    val type: String,
    val data: MutableList<FeedArticleModel>,
    val articleItem: HugeCardModel?,
    var isAdLoaded: Boolean? = null
)