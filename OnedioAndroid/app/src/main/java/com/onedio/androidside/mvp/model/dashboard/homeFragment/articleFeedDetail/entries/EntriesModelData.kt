package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.entries

import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailEntryEmbedArticle

data class EntriesModelData(
    val id: String,
    val legacyId: Long,
    val categoryName: String,
    val cateegoryId: String,
    val mode: String,
    val entryTitle: String?,
    val entryContent: String?,
    val entryImage: String?,
    val htmlData: String?,
    val embedSource: String?,
    val onedioVideoEmbedFile: String?,
    val embedArticle: ArticleFeedDetailEntryEmbedArticle?,
    val source: String,
    val postDelayed: Boolean
)