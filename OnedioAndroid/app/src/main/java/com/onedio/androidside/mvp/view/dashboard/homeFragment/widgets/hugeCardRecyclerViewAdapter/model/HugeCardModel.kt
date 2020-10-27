package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model

import android.os.Parcelable
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.TopComments
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HugeCardModel(
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
    val redirectUrl: String
    ) : Parcelable