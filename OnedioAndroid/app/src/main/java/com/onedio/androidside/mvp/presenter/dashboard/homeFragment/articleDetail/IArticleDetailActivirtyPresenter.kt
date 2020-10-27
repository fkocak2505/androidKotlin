package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleDetail

import android.view.View
import android.widget.TextView
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel

interface IArticleDetailActivirtyPresenter {

    fun sendReadState(legacyId: Long)

    fun getArticleFeedDetail(legacyId: Long)

    fun getArticleFeedDetailNew(legacyId: Long, isTaboola: Boolean)

    fun getRecommendWidgetArticle(categoryId: String, page: Int, perPage: Int, sort: String, duration: String, isTaboola: Boolean, legacyId: Long)

    fun upReactionNew(legacyId: Long, emojiCode: String, type: String, view: View, firstHeight: Int, emojiItemCount: Int, tvEmojiItemCount: TextView)

    fun downReactionNew(legacyId: Long, emojiCode: String, type: String, view: View, firstHeight: Int, emojiItemCount: Int, tvEmojiItemCount: TextView)

    fun upReaction(articleId: String, emojiCode: String, type: String, view: View, firstHeight: Int, emojiItemCount: Int, tvEmojiItemCount: TextView)

    fun downReaction(articleId: String, emojiCode: String, type: String, view: View, firstHeight: Int, emojiItemCount: Int, tvEmojiItemCount: TextView)

    fun addFavorite(legacyId: Long)

    fun deleteFavorite(legacyId: Long)

    fun likeComment(itemOfComment: CommentModel, likeCountView: TextView)

    fun unLike(itemOfComment: CommentModel, likeCountView: TextView)

}