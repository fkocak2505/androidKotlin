package com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tags {

    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("route")
    @Expose
    var route: String? = null


}