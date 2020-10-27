package com.onedio.androidside.mvp.model.dashboard.profil

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4UserFollowerAndFollowing {

    @SerializedName("data")
    @Expose
    val data: MutableList<UsersProfilData>? = null

}