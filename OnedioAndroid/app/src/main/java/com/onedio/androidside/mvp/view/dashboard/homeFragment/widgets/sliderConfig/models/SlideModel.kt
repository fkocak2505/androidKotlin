package com.denzcoskun.imageslider.models

data class SlideModel(
    val sliderItemLegacyId: Long,
    val sliderItemId: String,
    val imageUrl: String,
    val title: String,
    var centerCrop: Boolean,
    var categoryId: String,
    var categoryName: String,
    val showInWebView: Boolean,
    val redirectUrl: String
)