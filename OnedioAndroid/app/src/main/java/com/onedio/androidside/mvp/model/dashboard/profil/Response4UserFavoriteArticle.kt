package com.onedio.androidside.mvp.model.dashboard.profil

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.UserArticleFavoriteModel

class Response4UserFavoriteArticle {

    @SerializedName("data")
    @Expose
    val data: MutableList<UserArticleFavoriteModel>? = null

}