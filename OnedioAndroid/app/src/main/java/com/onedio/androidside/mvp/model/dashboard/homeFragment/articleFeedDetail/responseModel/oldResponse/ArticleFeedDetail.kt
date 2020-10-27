package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Reactions
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.StatsData
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.DateOfArticle
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.ImageOfArticle

class ArticleFeedDetail {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("image")
    @Expose
    val image: ImageOfArticle? = null

    @SerializedName("dates")
    @Expose
    val dates: DateOfArticle? = null

    @SerializedName("stats")
    @Expose
    val stats: StatsData? = null

    @SerializedName("categories")
    @Expose
    val categories: MutableList<ArticleDetailCategory>? = null

    @SerializedName("type")
    @Expose
    val type: String? = null

    @SerializedName("authors")
    @Expose
    val authors: MutableList<ArticleFeedAuthors>? = null

    @SerializedName("entries")
    @Expose
    val entries: MutableList<ArticleFeedEntries>? = null

    @SerializedName("reactions")
    @Expose
    val reactions: MutableList<Reactions>? = null

    @SerializedName("comments")
    @Expose
    val comments: MutableList<CommentsData>? = null

    @SerializedName("recommendedArticles")
    @Expose
    val recommendedArticles: MutableList<RecommendedArticles>? = null

}