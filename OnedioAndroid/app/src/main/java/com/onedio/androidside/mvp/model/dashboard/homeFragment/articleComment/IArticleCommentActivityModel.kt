package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4ArticleComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4SendComment
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.Response4ArticleFeedDetail
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle

interface IArticleCommentActivityModel {

    fun initAPIServiceFeed()

    fun initAPIServiceV2TestCluster()

    fun getArticleCommentNew(legacyId: Long, page: Int, iRequestListener: IRequestListener<Response4ArticleComments>)

    fun getArticleComment(legacyId: Long, iRequestListener: IRequestListener<Response4ArticleFeedDetail>)

    fun likeComment(commentId: String, iRequestListener: IRequestListener<Response4LikeAndUnLike>)

    fun unLike(commentId: String, iRequestListener: IRequestListener<Response4LikeAndUnLike>)

    fun sendComment(legacyId: Long, comment: String, parentCommentId: String??, iRequestListener: IRequestListener<Response4SendComment>)

    fun sendCommentNew(articleId: Long, comment: String, parentCommentId: String?, replyTo: String?, iRequestListener: IRequestListener<Response4LikeAndUnLike>)

    fun addFavorite(legacyId: Long, iRequestListener: IRequestListener<Response4AddFavoriteArticle>)

    fun deleteFavorite(legacyId: Long, iRequestListener: IRequestListener<Response4AddFavoriteArticle>)

}