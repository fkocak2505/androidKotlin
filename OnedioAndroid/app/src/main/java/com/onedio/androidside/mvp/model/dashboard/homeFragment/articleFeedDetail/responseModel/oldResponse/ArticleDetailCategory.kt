package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleDetailCategory {

    @SerializedName("_id")
    @Expose
    val id: String? = null

    @SerializedName("error")
    @Expose
    val error: String? = null

    @SerializedName("badgeSvgAsUrl")
    @Expose
    val badgePNGAsUrl: BadgeData4ArticleDetail? = null

}