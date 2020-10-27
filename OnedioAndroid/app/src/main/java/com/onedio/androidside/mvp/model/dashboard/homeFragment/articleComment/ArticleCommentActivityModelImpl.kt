package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment

import com.google.gson.Gson
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.requestModel.SendCommentParam
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4ArticleComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4SendComment
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.Response4ArticleFeedDetail
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class ArticleCommentActivityModelImpl :
    IArticleCommentActivityModel {

    private lateinit var iApiService: IApiService

    override fun initAPIServiceFeed() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun initAPIServiceV2TestCluster() {
        iApiService = ApiUtils.getAPIServiceV2TestCluster()
    }

    override fun getArticleCommentNew(
        legacyId: Long,
        page: Int,
        iRequestListener: IRequestListener<Response4ArticleComments>
    ) {

        initAPIServiceFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getArticleComments("Bearer $accessToken",legacyId, page)
            .enqueue(object : retrofit2.Callback<Response4ArticleComments> {
                override fun onFailure(call: Call<Response4ArticleComments>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4ArticleComments>?,
                    response: Response<Response4ArticleComments>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun getArticleComment(
        legacyId: Long,
        iRequestListener: IRequestListener<Response4ArticleFeedDetail>
    ) {

        initAPIServiceV2TestCluster()

        iApiService.getArticleFeedDetail(legacyId)
            .enqueue(object : retrofit2.Callback<Response4ArticleFeedDetail> {

                override fun onFailure(call: Call<Response4ArticleFeedDetail>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4ArticleFeedDetail>?,
                    response: Response<Response4ArticleFeedDetail>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun likeComment(
        commentId: String,
        iRequestListener: IRequestListener<Response4LikeAndUnLike>
    ) {

        initAPIServiceFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.likeComment(
            "Bearer $accessToken",
            commentId
        ).enqueue(object : retrofit2.Callback<Response4LikeAndUnLike> {
            override fun onFailure(call: Call<Response4LikeAndUnLike>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4LikeAndUnLike>?,
                response: Response<Response4LikeAndUnLike>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun unLike(
        commentId: String,
        iRequestListener: IRequestListener<Response4LikeAndUnLike>
    ) {
        initAPIServiceFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.unLike(
            "Bearer $accessToken",
            commentId
        ).enqueue(object : retrofit2.Callback<Response4LikeAndUnLike> {
            override fun onFailure(call: Call<Response4LikeAndUnLike>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4LikeAndUnLike>?,
                response: Response<Response4LikeAndUnLike>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }


    override fun sendComment(
        legacyId: Long,
        comment: String,
        parentCommentId: String?,
        iRequestListener: IRequestListener<Response4SendComment>
    ) {

        initAPIServiceV2TestCluster()


        var sendCommentParam =
            SendCommentParam()
        sendCommentParam.text = comment


        parentCommentId?.let {
            sendCommentParam.parent = parentCommentId
        }

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.sendComment(
            "Bearer $accessToken",
            legacyId,
            RequestBody.create(MediaType.parse("application/json"), Gson().toJson(sendCommentParam))
        ).enqueue(object : retrofit2.Callback<Response4SendComment> {
            override fun onFailure(call: Call<Response4SendComment>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4SendComment>?,
                response: Response<Response4SendComment>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun sendCommentNew(
        articleId: Long,
        comment: String,
        parentCommentId: String?,
        replyTo: String?,
        iRequestListener: IRequestListener<Response4LikeAndUnLike>
    ) {
        initAPIServiceFeed()

        val sendCommentParam =
            SendCommentParam()
        sendCommentParam.text = comment

        parentCommentId?.let {
            sendCommentParam.parent = parentCommentId
        }

        replyTo?.let {
            sendCommentParam.replyTo = replyTo
        }

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.sendCommentNew("Bearer $accessToken",
            articleId,
            RequestBody.create(MediaType.parse("application/json"), Gson().toJson(sendCommentParam)))
            .enqueue(object : retrofit2.Callback<Response4LikeAndUnLike>{
                override fun onFailure(call: Call<Response4LikeAndUnLike>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4LikeAndUnLike>?,
                    response: Response<Response4LikeAndUnLike>?
                ) {
                    when(response?.isSuccessful){
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })

    }

    override fun deleteFavorite(
        legacyId: Long,
        iRequestListener: IRequestListener<Response4AddFavoriteArticle>
    ) {
        initAPIServiceFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.deleteFavoriteArticle("Bearer $accessToken", legacyId).enqueue(object : retrofit2.Callback<Response4AddFavoriteArticle>{
            override fun onFailure(call: Call<Response4AddFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4AddFavoriteArticle>?,
                response: Response<Response4AddFavoriteArticle>?
            ) {
                when(response?.isSuccessful){
                    false -> iRequestListener.onUnSuccess(response)
                    true -> iRequestListener.onSuccess(response)
                }
            }
        })
    }

    override fun addFavorite(
        legacyId: Long,
        iRequestListener: IRequestListener<Response4AddFavoriteArticle>
    ) {
        initAPIServiceFeed()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.addFavoriteArticle("Bearer $accessToken", legacyId).enqueue(object : retrofit2.Callback<Response4AddFavoriteArticle>{
            override fun onFailure(call: Call<Response4AddFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4AddFavoriteArticle>?,
                response: Response<Response4AddFavoriteArticle>?
            ) {
                when(response?.isSuccessful){
                    false -> iRequestListener.onUnSuccess(response)
                    true -> iRequestListener.onSuccess(response)
                }
            }
        })
    }
}