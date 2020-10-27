package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleComment

import android.widget.TextView
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel

interface IArticleCommentActivityPresenter {

    fun getArticleCommentNew(legacyId: Long, page: Int)

    fun getArticleComment(legacyId: Long)

    fun likeComment(itemOfComment: CommentModel, likeCountView: TextView)

    fun unLike(itemOfComment: CommentModel, likeCountView: TextView)

    fun sendComment(legacyId: Long, comment: String, parentCommentId: String?)

    fun sendCommentNew(articleId: Long, comment: String, parentCommentId: String?, replyTo: String?)

    fun deleteFavorite(legacyId: Long)

    fun addFavorite(legacyId: Long)

}