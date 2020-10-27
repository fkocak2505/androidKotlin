package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleImageModel

class ArticleFeedDetailsEntryModel {

    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("legacyId")
    @Expose
    var legacyId: Long? = null

    @SerializedName("mode")
    @Expose
    var mode: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("image")
    @Expose
    var image: ArticleImageModel? = null

    @SerializedName("html")
    @Expose
    var html: String? = null

    @SerializedName("internaldata")
    @Expose
    var internaldata: ArticleFeedDetailEntryInternalData? = null

    @SerializedName("embedSource")
    @Expose
    var embedSource: String? = null

    @SerializedName("embedArticle")
    @Expose
    var embedArticle: ArticleFeedDetailEntryEmbedArticle? = null

    @SerializedName("urls")
    @Expose
    var urls: ArticleEntryUrls? = null





}