package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetDataWithTagId

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.FeedArticles

class Response4WidgetDataWithTagId {

    @SerializedName("data")
    @Expose
    val data: MutableList<FeedArticles>? = null

}