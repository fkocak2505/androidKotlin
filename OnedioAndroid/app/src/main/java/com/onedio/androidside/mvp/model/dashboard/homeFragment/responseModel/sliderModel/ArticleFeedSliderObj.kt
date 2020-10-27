package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleFeedSliderObj {

    @SerializedName("feed")
    @Expose
    val feed: MutableList<FeedArticleModel>? = null

}