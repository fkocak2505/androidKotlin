package com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail.dataModel

data class CategoryItem(
    val id: String,
    val legacyId: Long,
    val categoryItemPhoto: String,
    val categoryItemTitle: String,
    val categoryItemSubTitle: String,
    val categoryItemReadCount: String,
    val categoryItemCreatedDate: String,
    val categoryId: String,
    val categoryName: String,
    val showInWebView: Boolean,
    val redirectUrl: String,
    val isHideReadLayout: Boolean
)