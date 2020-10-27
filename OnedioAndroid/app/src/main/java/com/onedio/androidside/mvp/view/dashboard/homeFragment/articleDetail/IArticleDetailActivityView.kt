package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsEntryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleFeedDetailsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface IArticleDetailActivityView {

    fun initArticleDetailActivityComponent()

    fun getArticleFeedDetail(legacyId: Long, isTaboola: Boolean)

    fun handleArticleFeedDetailDataNew(response4ArticleFeedDetails: Response4ArticleFeedDetails, isTaboola: Boolean)

    fun getRecommendWidgetData(legaycId: Long,isTaboola: Boolean)

    fun handleRecommendWidgetData(response4ArticlesFeed: Response4ArticlesFeed, isTaboola: Boolean, legacyId: Long)

    //fun handleArticleFeedDetailData(response4ArticleFeedDetail: Response4ArticleFeedDetail)

    fun setHeadOfArticleDetail(articleFeedDetailsModel: ArticleFeedDetailsModel)

    fun loadImageWithPicasso(imageUrl: String, imageView: ImageView, progressBar: ProgressBar)

    fun setEntriesData(arrOfEntriesData: MutableList<ArticleFeedDetailsEntryModel>)

    fun handleEmojiUpReactionNew(
        response4LikeAndUnLike: Response4LikeAndUnLike,
        legacyId: Long,
        emojiCode: String,
        type: String,
        view: View,
        firstHeight: Int,
        emojiItemCount: Int,
        tvEmojiItemCount: TextView
    )

    /*fun handleEmojiReaction(
        response4EmojiReaction: Response4EmojiReaction,
        articleId: String,
        emojiCode: String,
        type: String,
        view: View,
        firstHeight: Int,
        emojiItemCount: Int,
        tvEmojiItemCount: TextView
    )*/

    fun handleAddFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle
    )

    fun handleDeleteFavoriteDataModel(
        response4AddFavoriteArticle: Response4AddFavoriteArticle
    )

    fun handleLikeComment(response4LikeAndUnLike: Response4LikeAndUnLike, itemOfComment: CommentModel, likeCountView: TextView)

    fun handleUnlikeComment(response4LikeAndUnLike: Response4LikeAndUnLike, itemOfComment: CommentModel, likeCountView: TextView)

    fun openVisibilityOfLayout()

    fun handleSendRead(response4ReadState: String)

    fun onFailMethod(response4ReadState: String)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    )

}