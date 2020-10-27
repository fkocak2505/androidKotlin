package com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4Tags {

    @SerializedName("tags")
    @Expose
    var tags: MutableList<Tags>? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null

}