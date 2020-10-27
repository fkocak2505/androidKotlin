package com.onedio.androidside.mvp.model.dashboard.profil

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleAuthorsStatsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleImageModel

class UsersProfilData {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("username")
    @Expose
    val username: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("image")
    @Expose
    val image: ArticleImageModel? = null

    @SerializedName("country")
    @Expose
    val country: String? = null

    @SerializedName("city")
    @Expose
    val city: String? = null

    @SerializedName("gender")
    @Expose
    val gender: String? = null

    @SerializedName("birthdate")
    @Expose
    val birthdate: String? = null

    @SerializedName("occupation")
    @Expose
    val occupation: String? = null

    @SerializedName("slogan")
    @Expose
    val slogan: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("stats")
    @Expose
    val stats: ArticleAuthorsStatsModel? = null



}
