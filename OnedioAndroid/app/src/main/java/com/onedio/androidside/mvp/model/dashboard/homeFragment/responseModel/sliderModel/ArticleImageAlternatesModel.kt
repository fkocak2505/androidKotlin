package com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleImageAlternatesModel {

    @SerializedName("standardResolution")
    @Expose
    val standardResolution: ArticleImageAlternatesHighResolution? = null

    /*@SerializedName("highResolution")
    @Expose
    val highResolution: ArticleImageAlternatesHighResolution? = null*/

}