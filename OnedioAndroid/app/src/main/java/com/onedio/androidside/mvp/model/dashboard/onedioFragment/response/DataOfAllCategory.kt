package com.onedio.androidside.mvp.model.dashboard.onedioFragment.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleCategoryIconsModel

class DataOfAllCategory {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("color")
    @Expose
    val color: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("isActive")
    @Expose
    val isActive: Boolean? = null

    @SerializedName("displayIndex")
    @Expose
    val displayIndex: Int? = null

    @SerializedName("children")
    @Expose
    val children: ArrayList<ChildrenOfCategoryItem>? = null

    @SerializedName("icons")
    @Expose
    val icons: ArticleCategoryIconsModel? = null



}