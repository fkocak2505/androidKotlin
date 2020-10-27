package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model

data class VideoAdapterModel(
    val type: String,
    val id: String,
    val legacyId: Long,
    val categoryItemPhoto: String,
    val categoryItemVideoLink: String,
    val categoryItemTitle: String,
    val categoryItemSubTitle: String,
    val categoryItemReadCount: String,
    val categoryItemCreatedDate: String,
    val categoryId: String,
    val categoryName: String,
    val showInWebView: Boolean,
    val redirectUrl: String,
    val isHideCountLayout: Boolean,
    var isAdLoaded: Boolean? = null
)