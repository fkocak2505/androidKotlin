package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.testWidgetRecyclerListAdapter.model

data class TestWidgetModel(
    val id: String,
    val legacyId: Long,
    val imageURL: String,
    val testTitle: String,
    var categoryId: String?,
    var categoryName: String?,
    val badgeIcon: String,
    val badgeId: String,
    val badgeName: String,
    val showInWebView: Boolean,
    val isFavorited: Boolean,
    val redirectUrl: String
)