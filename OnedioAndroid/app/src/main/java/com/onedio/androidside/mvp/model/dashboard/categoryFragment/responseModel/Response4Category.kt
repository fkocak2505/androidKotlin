package com.onedio.androidside.mvp.model.dashboard.categoryFragment.responseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response4Category {

    @SerializedName("data")
    @Expose
    val data: MutableList<DataOfCategory>? = null

}