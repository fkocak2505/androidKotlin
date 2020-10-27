package com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.model

import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.TopComments

data class TagsAdapterModel(
    val type: String,
    val articleId: String,
    val legacyId: Long,
    val coverPhoto: String?,
    val articleAction: String?,
    val badgeId: String?,
    val coverPhotoText: String?,
    val coverPhotoBadgeImage: String?,
    val feedTitle: String?,
    val feedDate: String?,
    val emojiAction: String?,
    val emojiAction2: String?,
    val emojiAction3: String?,
    val emojiActionCount: String?,
    val commentCount: String?,
    val popularComment: TopComments?,
    var isUserFavorited: Boolean,
    val categoryName: String?,
    val categoryId: String?,
    val showInWebView: Boolean,
    val redirectUrl: String,
    var isAdLoaded: Boolean? = null
)