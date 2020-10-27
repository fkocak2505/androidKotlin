package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model

import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.TopComments

data class HugeCardModelTrending(
    val articleId: String,
    val type: String,
    val legacyId: Long,
    val coverPhoto: String?,
    val articleAction: String?,
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
    val categoryId: String?
)