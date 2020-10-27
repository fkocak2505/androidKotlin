package com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment

import android.widget.TextView
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4ArticleComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.ArticleCommentsModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle

interface IArticleCommentActivityView {

    fun initArticleCommentActivityComponent()

    fun setCommentDataAdapter(commentData: List<CommentModel>)

    fun addAllCommentData(commentData: MutableList<ArticleCommentsModel>): MutableList<CommentModel>

    fun setEmojiProvider()

    fun emojiButtonClickListener()

    fun handleArticleCommentNew(response4ArticleFeedDetails: Response4ArticleComments)

    fun handleLikeComment(response4LikeAndUnLike: Response4LikeAndUnLike, itemOfComment: CommentModel, likeCountView: TextView)

    fun handleUnlikeComment(response4LikeAndUnLike: Response4LikeAndUnLike, itemOfComment: CommentModel, likeCountView: TextView)

    //fun handleArticleComment(response4ArticleFeedDetail: Response4ArticleFeedDetail)

    //fun handleSendCommentData(response4SendComment: Response4SendComment)

    fun handleSendCommentDataNew(response4LikeAndUnLike: Response4LikeAndUnLike)

    //fun swipeRefreshConfig()

    fun commentRecyclerViewConfig()

    /*fun changeIconIfDarkModeOn()*/

    fun setUpEmojiPopup()

    fun handleAddFavorite(response4AddFavoriteArticle: Response4AddFavoriteArticle)

    fun handleDeleteFavorite(response4AddFavoriteArticle: Response4AddFavoriteArticle)

    /*fun setProfilePhoto()*/

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

}