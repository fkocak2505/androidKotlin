package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.entries

import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.EntriesData
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.ImageOfArticle

data class EntriesModel(val mode: String, val id: String, val image: ImageOfArticle?, val data: EntriesData?)