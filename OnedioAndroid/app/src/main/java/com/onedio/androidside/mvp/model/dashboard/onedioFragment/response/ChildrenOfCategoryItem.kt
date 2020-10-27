package com.onedio.androidside.mvp.model.dashboard.onedioFragment.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChildrenOfCategoryItem {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("parent")
    @Expose
    val parent: String? = null

    @SerializedName("isActive")
    @Expose
    val isActive: Boolean? = null

    @SerializedName("categoryIconAsPng")
    @Expose
    val categoryIconAsPng: String? = null





}