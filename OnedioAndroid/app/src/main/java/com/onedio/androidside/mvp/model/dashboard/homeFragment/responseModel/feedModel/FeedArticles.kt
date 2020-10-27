package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.DateOfArticle

class FeedArticles {

    @SerializedName("id")
    @Expose
    val id : String? = null

    @SerializedName("legacyId")
    @Expose
    val legacyId : Long? = null

    @SerializedName("title")
    @Expose
    val titleOfArticle : String? = null

    @SerializedName("image")
    @Expose
    val imageOfArticle : ImageOfSlider? = null

    @SerializedName("dates")
    @Expose
    val dates : DateOfArticle? = null

    @SerializedName("reactions")
    @Expose
    val reactions : MutableList<Reactions>? = null

    @SerializedName("stats")
    @Expose
    val stats : StatsData? = null

    @SerializedName("badge")
    @Expose
    val badge : BadgeData? = null

    @SerializedName("category")
    @Expose
    val category : CategoryArticle? = null

    @SerializedName("videoData")
    @Expose
    val videoData : VideoData? = null

    @SerializedName("topComments")
    @Expose
    val topComments : MutableList<TopComments>? = null

    @SerializedName("isUserFavorited")
    @Expose
    val isUserFavorited : Boolean? = null

    @SerializedName("description")
    @Expose
    val description : String? = null

}