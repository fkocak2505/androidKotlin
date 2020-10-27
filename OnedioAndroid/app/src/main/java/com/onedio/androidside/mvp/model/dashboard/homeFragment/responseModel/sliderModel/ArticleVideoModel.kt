package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleVideoModel {

    @SerializedName("cover")
    @Expose
    val cover: ArticleVideoCoverModel? = null

    @SerializedName("video")
    @Expose
    val video: String? = null

}