package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.adapter.holder.entryAdapter

import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleEntryUrls
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailEntryEmbedArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailEntryInternalData
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.ArticleImageModel

data class Entries(
    val id: String? = null,
    val legacyId: Long? = null,
    val mode: String? = null,
    val title: String? = null,
    val content: String? = null,
    val image: ArticleImageModel? = null,
    val html: String? = null,
    val internaldata: ArticleFeedDetailEntryInternalData? = null,
    val embedSource: String? = null,
    val embedArticle: ArticleFeedDetailEntryEmbedArticle? = null,
    val urls: ArticleEntryUrls? = null
)