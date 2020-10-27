package com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4UserSearch {

    @SerializedName("users")
    @Expose
    val users: MutableList<Users>? = null

    @SerializedName("total")
    @Expose
    val total: Int? = null

}