package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntriesData {

    @SerializedName("content")
    @Expose
    val content: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("html")
    @Expose
    val html: String? = null

    @SerializedName("isOioEmbed")
    @Expose
    val isOioEmbed: Boolean? = null

    @SerializedName("internaldata")
    @Expose
    val internalData: InternalData? = null

    @SerializedName("embedArticleSummary")
    @Expose
    val embedArticleSummary: EmbedArticleSummary? = null
}