package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleDetail

import android.view.View
import android.widget.TextView
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.IArticleFeedDetailModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.emojiReaction.Response4EmojiReaction
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.Response4ArticleFeedDetail
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.IArticleDetailActivityView
import retrofit2.Response

class ArticleDetailActivirtyPresenterImpl(
    val iArticleFeedDetailModel: IArticleFeedDetailModel,
    val iArticleFeedDetailActivityView: IArticleDetailActivityView
) : IArticleDetailActivirtyPresenter {

    override fun sendReadState(legacyId: Long) {
        iArticleFeedDetailModel.sendReadState(legacyId, object :
            IRequestListener<String> {
            override fun onSuccess(response: Response<String>) {
                iArticleFeedDetailActivityView.handleSendRead("")
            }

            override fun onUnSuccess(response: Response<String>) {
                iArticleFeedDetailActivityView.handleSendRead("")
            }

            override fun onFail(t: Throwable?) {
                iArticleFeedDetailActivityView.onFailMethod("")
            }
        })
    }

    override fun getArticleFeedDetailNew(legacyId: Long, isTaboola: Boolean) {
        //iArticleFeedDetailActivityView.showLoading()
        iArticleFeedDetailModel.getArticleFeedDetailNew(legacyId, object :
            IRequestListener<Response4ArticleFeedDetails> {
            override fun onSuccess(response: Response<Response4ArticleFeedDetails>) {
                iArticleFeedDetailActivityView.hideLoading()
                iArticleFeedDetailActivityView.handleArticleFeedDetailDataNew(
                    response.body() as Response4ArticleFeedDetails,
                    isTaboola
                )
            }

            override fun onUnSuccess(response: Response<Response4ArticleFeedDetails>) {
                iArticleFeedDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleFeedDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz.(getArticleFeedDetailNew)"
                iArticleFeedDetailActivityView.showError(errorObj)
            }
        })
    }

    override fun getRecommendWidgetArticle(
        categoryId: String,
        page: Int,
        perPage: Int,
        sort: String,
        duration: String,
        isTaboola: Boolean,
        legaycId: Long
    ) {
        iArticleFeedDetailActivityView.showLoading()
        iArticleFeedDetailModel.getArticleFeedByCategoryIdNew(
            categoryId,
            page,
            perPage,
            sort,
            duration,
            object : IRequestListener<Response4ArticlesFeed> {
                override fun onSuccess(response: Response<Response4ArticlesFeed>) {
                    //iArticleFeedDetailActivityView.hideLoading()
                    iArticleFeedDetailActivityView.handleRecommendWidgetData(response.body() as Response4ArticlesFeed, isTaboola, legaycId)
                }

                override fun onUnSuccess(response: Response<Response4ArticlesFeed>) {
                    iArticleFeedDetailActivityView.hideLoading()
                    //iArticleFeedDetailActivityView.handleRecommendWidgetData(Response4ArticlesFeed(), isTaboola, legaycId)
                    /*val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iArticleFeedDetailActivityView.showError(errorObj)*/
                    iArticleFeedDetailActivityView.handleRecommendWidgetData(Response4ArticlesFeed(), isTaboola, legaycId)
                }

                override fun onFail(t: Throwable?) {
                    iArticleFeedDetailActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz.(getRecommendWidgetArticle)"
                    iArticleFeedDetailActivityView.showError(errorObj)
                }
            })
    }


    override fun getArticleFeedDetail(legacyId: Long) {
        iArticleFeedDetailActivityView.showLoading()
        iArticleFeedDetailModel.getArticleFeedDetail(legacyId, object :
            IRequestListener<Response4ArticleFeedDetail> {
            override fun onSuccess(response: Response<Response4ArticleFeedDetail>) {
                iArticleFeedDetailActivityView.hideLoading()
                //iArticleFeedDetailActivityView.handleArticleFeedDetailData(response.body() as Response4ArticleFeedDetail)
            }

            override fun onUnSuccess(response: Response<Response4ArticleFeedDetail>) {
                iArticleFeedDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleFeedDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iArticleFeedDetailActivityView.showError(errorObj)
            }
        })
    }

    override fun downReactionNew(
        legacyId: Long,
        emojiCode: String,
        type: String,
        view: View,
        firstHeight: Int,
        emojiItemCount: Int,
        tvEmojiItemCount: TextView
    ) {
        iArticleFeedDetailModel.downReactionnew(legacyId, emojiCode, object :
            IRequestListener<Response4LikeAndUnLike> {
            override fun onSuccess(response: Response<Response4LikeAndUnLike>) {
                iArticleFeedDetailActivityView.handleEmojiUpReactionNew(
                    response.body() as Response4LikeAndUnLike,
                    legacyId,
                    emojiCode,
                    type,
                    view,
                    firstHeight,
                    emojiItemCount,
                    tvEmojiItemCount
                )
            }

            override fun onUnSuccess(response: Response<Response4LikeAndUnLike>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {

            }
        })
    }

    override fun upReactionNew(
        legacyId: Long,
        emojiCode: String,
        type: String,
        view: View,
        firstHeight: Int,
        emojiItemCount: Int,
        tvEmojiItemCount: TextView
    ) {
        iArticleFeedDetailModel.upReactionNew(legacyId, emojiCode, object :
            IRequestListener<Response4LikeAndUnLike> {
            override fun onSuccess(response: Response<Response4LikeAndUnLike>) {
                iArticleFeedDetailActivityView.handleEmojiUpReactionNew(
                    response.body() as Response4LikeAndUnLike,
                    legacyId,
                    emojiCode,
                    type,
                    view,
                    firstHeight,
                    emojiItemCount,
                    tvEmojiItemCount
                )
            }

            override fun onUnSuccess(response: Response<Response4LikeAndUnLike>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
            }
        })
    }

    override fun upReaction(
        articleId: String,
        emojiCode: String,
        type: String,
        view: View,
        firstHeight: Int,
        emojiItemCount: Int,
        tvEmojiItemCount: TextView
    ) {
        iArticleFeedDetailActivityView.showLoading()
        iArticleFeedDetailModel.upReaction(articleId, emojiCode, object :
            IRequestListener<Response4EmojiReaction> {
            override fun onSuccess(response: Response<Response4EmojiReaction>) {
                iArticleFeedDetailActivityView.hideLoading()
                //iArticleFeedDetailActivityView.handleEmojiReaction(response.body() as Response4EmojiReaction, articleId, emojiCode, type, view, firstHeight, emojiItemCount, tvEmojiItemCount)
            }

            override fun onUnSuccess(response: Response<Response4EmojiReaction>) {
                iArticleFeedDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleFeedDetailActivityView.hideLoading()
            }
        })
    }

    override fun downReaction(
        articleId: String,
        emojiCode: String,
        type: String,
        view: View,
        firstHeight: Int,
        emojiItemCount: Int,
        tvEmojiItemCount: TextView
    ) {
        iArticleFeedDetailActivityView.showLoading()
        iArticleFeedDetailModel.downReaction(articleId, emojiCode, object :
            IRequestListener<Response4EmojiReaction> {
            override fun onSuccess(response: Response<Response4EmojiReaction>) {
                iArticleFeedDetailActivityView.hideLoading()
                //iArticleFeedDetailActivityView.handleEmojiReaction(response.body() as Response4EmojiReaction, articleId, emojiCode, type, view, firstHeight, emojiItemCount, tvEmojiItemCount)
            }

            override fun onUnSuccess(response: Response<Response4EmojiReaction>) {
                iArticleFeedDetailActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleFeedDetailActivityView.hideLoading()
            }
        })
    }

    override fun addFavorite(legacyId: Long) {
        iArticleFeedDetailModel.addFavorite(legacyId, object :
            IRequestListener<Response4AddFavoriteArticle> {
            override fun onSuccess(response: Response<Response4AddFavoriteArticle>) {
                iArticleFeedDetailActivityView.handleAddFavoriteDataModel(response.body() as Response4AddFavoriteArticle)
            }

            override fun onUnSuccess(response: Response<Response4AddFavoriteArticle>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
            }
        })
    }

    override fun deleteFavorite(legacyId: Long) {
        iArticleFeedDetailModel.deleteFavorite(legacyId, object :
            IRequestListener<Response4AddFavoriteArticle> {
            override fun onSuccess(response: Response<Response4AddFavoriteArticle>) {
                iArticleFeedDetailActivityView.handleDeleteFavoriteDataModel(response.body() as Response4AddFavoriteArticle)
            }

            override fun onUnSuccess(response: Response<Response4AddFavoriteArticle>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
            }
        })
    }

    override fun likeComment(itemOfComment: CommentModel, likeCountView: TextView) {
        iArticleFeedDetailModel.likeComment(itemOfComment.commentId, object :
            IRequestListener<Response4LikeAndUnLike> {
            override fun onSuccess(response: Response<Response4LikeAndUnLike>) {
                iArticleFeedDetailActivityView.handleLikeComment(
                    response.body() as Response4LikeAndUnLike,
                    itemOfComment,
                    likeCountView
                )
            }

            override fun onUnSuccess(response: Response<Response4LikeAndUnLike>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
            }
        })
    }

    override fun unLike(itemOfComment: CommentModel, likeCountView: TextView) {
        iArticleFeedDetailModel.unLike(itemOfComment.commentId, object :
            IRequestListener<Response4LikeAndUnLike> {
            override fun onSuccess(response: Response<Response4LikeAndUnLike>) {
                iArticleFeedDetailActivityView.handleUnlikeComment(
                    response.body() as Response4LikeAndUnLike,
                    itemOfComment,
                    likeCountView
                )
            }

            override fun onUnSuccess(response: Response<Response4LikeAndUnLike>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleFeedDetailActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
            }
        })
    }


}