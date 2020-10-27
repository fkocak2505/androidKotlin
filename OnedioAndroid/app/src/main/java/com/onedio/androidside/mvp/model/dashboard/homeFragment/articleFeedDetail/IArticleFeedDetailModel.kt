package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.Response4ArticleFeedDetail
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.emojiReaction.Response4EmojiReaction
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface IArticleFeedDetailModel {

    fun initAPIServiceV2TestCluster()

    fun initApiService()

    fun sendReadState(legacyId: Long, iRequestListener: IRequestListener<String>)

    fun getArticleFeedDetail(legacyId: Long, iRequestListener: IRequestListener<Response4ArticleFeedDetail>)

    fun getArticleFeedDetailNew(legacyId: Long, iRequestListener: IRequestListener<Response4ArticleFeedDetails>)

    fun getArticleFeedByCategoryIdNew(
        categoryId: String,
        page: Int,
        perPage: Int,
        sort: String,
        duration: String,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    )

    fun upReactionNew(legacyId: Long, emojiCode: String, iRequestListener: IRequestListener<Response4LikeAndUnLike>)

    fun downReactionnew(legacyId: Long, emojiCode: String, iRequestListener: IRequestListener<Response4LikeAndUnLike>)

    fun upReaction(articleId: String, emojiCode: String, iRequestListener: IRequestListener<Response4EmojiReaction>)

    fun downReaction(articleId: String, emojiCode: String, iRequestListener: IRequestListener<Response4EmojiReaction>)

    fun addFavorite(legacyId: Long, iRequestListener: IRequestListener<Response4AddFavoriteArticle>)

    fun deleteFavorite(legacyId: Long, iRequestListener: IRequestListener<Response4AddFavoriteArticle>)

    fun likeComment(commentId: String, iRequestListener: IRequestListener<Response4LikeAndUnLike>)

    fun unLike(commentId: String, iRequestListener: IRequestListener<Response4LikeAndUnLike>)

}