package com.onedio.androidside.mvp.model.dashboard.homeFragment.dataModel

data class ArticleItemModel(
    val id: String,
    val legacyId: Long,
    val coverPhoto: String,
    val coverPhotoText: String,
    val coverPhotoActionImage: Int,
    val articleActionIcon: String,
    val articleActionIconId: String,
    val feedTitle: String,
    val feedDate: String,
    val emojiAction: String,
    val emojiAction2: String,
    val emojiAction3: String,
    val emojiActionCount: String,
    val commentCount: Int
)