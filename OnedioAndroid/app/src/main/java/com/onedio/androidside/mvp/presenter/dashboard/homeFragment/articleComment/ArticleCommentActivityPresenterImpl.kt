package com.onedio.androidside.mvp.presenter.dashboard.homeFragment.articleComment

import android.widget.TextView
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.IArticleCommentActivityModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4ArticleComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4SendComment
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.Response4ArticleFeedDetail
import com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel.CommentModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.ValOfUpdateUserProfileInfoStatus
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleComment.IArticleCommentActivityView
import retrofit2.Response

class ArticleCommentActivityPresenterImpl(
    val iArticleCommentActivityModel: IArticleCommentActivityModel,
    val iArticleCommentActivityView: IArticleCommentActivityView
) : IArticleCommentActivityPresenter {

    override fun getArticleCommentNew(legacyId: Long, page: Int) {
        iArticleCommentActivityView.showLoading()
        iArticleCommentActivityModel.getArticleCommentNew(legacyId, page, object :
            IRequestListener<Response4ArticleComments> {
            override fun onSuccess(response: Response<Response4ArticleComments>) {
                iArticleCommentActivityView.hideLoading()
                iArticleCommentActivityView.handleArticleCommentNew(response.body() as Response4ArticleComments)
            }

            override fun onUnSuccess(response: Response<Response4ArticleComments>) {
                iArticleCommentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleCommentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleCommentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iArticleCommentActivityView.showError(errorObj)
            }
        })
    }

    override fun getArticleComment(legacyId: Long) {
        iArticleCommentActivityView.showLoading()
        iArticleCommentActivityModel.getArticleComment(legacyId, object :
            IRequestListener<Response4ArticleFeedDetail> {
            override fun onSuccess(response: Response<Response4ArticleFeedDetail>) {
                iArticleCommentActivityView.hideLoading()
                //iArticleCommentActivityView.handleArticleComment(response.body() as Response4ArticleFeedDetail)
            }

            override fun onUnSuccess(response: Response<Response4ArticleFeedDetail>) {
                iArticleCommentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleCommentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleCommentActivityView.hideLoading()
            }
        })
    }

    override fun likeComment(itemOfComment: CommentModel, likeCountView: TextView) {
        iArticleCommentActivityModel.likeComment(itemOfComment.commentId, object :
            IRequestListener<Response4LikeAndUnLike> {
            override fun onSuccess(response: Response<Response4LikeAndUnLike>) {
                iArticleCommentActivityView.handleLikeComment(
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
                iArticleCommentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleCommentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iArticleCommentActivityView.showError(errorObj)
            }
        })
    }

    override fun unLike(itemOfComment: CommentModel, likeCountView: TextView) {
        iArticleCommentActivityModel.unLike(itemOfComment.commentId, object :
            IRequestListener<Response4LikeAndUnLike> {
            override fun onSuccess(response: Response<Response4LikeAndUnLike>) {
                iArticleCommentActivityView.handleUnlikeComment(
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
                iArticleCommentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleCommentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iArticleCommentActivityView.showError(errorObj)
            }
        })
    }

    override fun sendComment(legacyId: Long, comment: String, parentCommentId: String?) {
        iArticleCommentActivityView.showLoading()
        iArticleCommentActivityModel.sendComment(legacyId, comment, parentCommentId, object :
            IRequestListener<Response4SendComment> {
            override fun onSuccess(response: Response<Response4SendComment>) {
                iArticleCommentActivityView.hideLoading()
                //iArticleCommentActivityView.handleSendCommentData(response.body() as Response4SendComment)
            }

            override fun onUnSuccess(response: Response<Response4SendComment>) {
                iArticleCommentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleCommentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                iArticleCommentActivityView.hideLoading()
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iArticleCommentActivityView.showError(errorObj)
            }
        })
    }

    override fun sendCommentNew(
        articleId: Long,
        comment: String,
        parentCommentId: String?,
        replyTo: String?
    ) {
        iArticleCommentActivityModel.sendCommentNew(
            articleId,
            comment,
            parentCommentId,
            replyTo,
            object :
                IRequestListener<Response4LikeAndUnLike> {
                override fun onSuccess(response: Response<Response4LikeAndUnLike>) {
                    iArticleCommentActivityView.handleSendCommentDataNew(response.body() as Response4LikeAndUnLike)
                }

                override fun onUnSuccess(response: Response<Response4LikeAndUnLike>) {
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                    iArticleCommentActivityView.showError(errorObj)
                }

                override fun onFail(t: Throwable?) {
                    iArticleCommentActivityView.hideLoading()
                    val errorObj = Response4ErrorObj()
                    errorObj.status =
                        ValOfUpdateUserProfileInfoStatus()
                    errorObj.status?.message =
                        "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                    iArticleCommentActivityView.showError(errorObj)
                }
            })
    }

    override fun addFavorite(legacyId: Long) {
        iArticleCommentActivityModel.addFavorite(legacyId, object :
            IRequestListener<Response4AddFavoriteArticle> {
            override fun onSuccess(response: Response<Response4AddFavoriteArticle>) {
                iArticleCommentActivityView.handleAddFavorite(response.body() as Response4AddFavoriteArticle)
            }

            override fun onUnSuccess(response: Response<Response4AddFavoriteArticle>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleCommentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iArticleCommentActivityView.showError(errorObj)
            }
        })
    }

    override fun deleteFavorite(legacyId: Long) {
        iArticleCommentActivityModel.deleteFavorite(legacyId, object :
            IRequestListener<Response4AddFavoriteArticle> {
            override fun onSuccess(response: Response<Response4AddFavoriteArticle>) {
                iArticleCommentActivityView.handleDeleteFavorite(response.body() as Response4AddFavoriteArticle)
            }

            override fun onUnSuccess(response: Response<Response4AddFavoriteArticle>) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Şu anda teknik bir sıkıntı mevcut. Lütfen tekrar deneyiniz"
                iArticleCommentActivityView.showError(errorObj)
            }

            override fun onFail(t: Throwable?) {
                val errorObj = Response4ErrorObj()
                errorObj.status =
                    ValOfUpdateUserProfileInfoStatus()
                errorObj.status?.message =
                    "Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz."
                iArticleCommentActivityView.showError(errorObj)
            }
        })
    }


}