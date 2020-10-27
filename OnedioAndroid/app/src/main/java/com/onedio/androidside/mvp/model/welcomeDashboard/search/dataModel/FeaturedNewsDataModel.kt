package com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel

data class FeaturedNewsDataModel(
    var id: String,
    var legacyId: Long,
    var featuredNewsImage: String,
    var featuredNewsTitle: String,
    var featuredNewsDate: String,
    var showInWebView: Boolean,
    var redirectUrl: String
)