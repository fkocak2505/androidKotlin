package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleCommentReplies {

    @SerializedName("count")
    @Expose
    val count: Int? = null

    @SerializedName("data")
    @Expose
    val data: MutableList<ArticleCommentsModel>? = null

}