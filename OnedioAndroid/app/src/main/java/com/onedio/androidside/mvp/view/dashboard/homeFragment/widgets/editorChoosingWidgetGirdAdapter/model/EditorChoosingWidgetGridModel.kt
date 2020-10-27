package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.editorChoosingWidgetGirdAdapter.model

data class EditorChoosingWidgetGridModel(
    val id: String,
    val legacyId: Long,
    val imageUrl: String,
    val title: String,
    val categoryId: String?,
    val categoryName: String?,
    val badgeIcon: String?,
    val badgeId: String?,
    val badgeName: String?,
    val showInWebView: Boolean,
    val redirectUrl: String
)