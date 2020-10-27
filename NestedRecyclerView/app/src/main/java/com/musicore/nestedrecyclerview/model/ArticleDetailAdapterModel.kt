package com.musicore.nestedrecyclerview.model

data class ArticleDetailAdapterModel(
    val type: String,
    val data: Response4ArticleDetail,
    var isAdLoaded: Boolean? = null
)