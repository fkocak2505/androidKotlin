package com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.emojiReaction.Response4EmojiReaction
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.Response4ArticleFeedDetail
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import retrofit2.Call
import retrofit2.Response

class ArticleFeedDetailModelImpl :
    IArticleFeedDetailModel {

    private lateinit var iApiService: IApiService

    override fun initAPIServiceV2TestCluster() {
        iApiService = ApiUtils.getAPIServiceV2TestCluster()
    }

    override fun initApiService() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }

    override fun getArticleFeedDetailNew(
        legacyId: Long,
        iRequestListener: IRequestListener<Response4ArticleFeedDetails>
    ) {

        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getArticleFeedDetailNew(
            "Bearer $accessToken",
            legacyId
        ).enqueue(object : retrofit2.Callback<Response4ArticleFeedDetails> {
            override fun onFailure(call: Call<Response4ArticleFeedDetails>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4ArticleFeedDetails>?,
                response: Response<Response4ArticleFeedDetails>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getArticleFeedByCategoryIdNew(
        categoryId: String,
        page: Int,
        perPage: Int,
        sort: String,
        duration: String,
        iRequestListener: IRequestListener<Response4ArticlesFeed>
    ) {
        initApiService()

        iApiService.getArticleFeedCategoryWidgetDataWithCategoryId(
            categoryId,
            page,
            perPage,
            sort,
            duration
        )
            .enqueue(object : retrofit2.Callback<Response4ArticlesFeed> {
                override fun onFailure(call: Call<Response4ArticlesFeed>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4ArticlesFeed>?,
                    response: Response<Response4ArticlesFeed>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun sendReadState(legacyId: Long, iRequestListener: IRequestListener<String>) {
        initApiService()

        iApiService.sendReadState(legacyId).enqueue(object : retrofit2.Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                when (response?.isSuccessful) {
                    false -> iRequestListener.onUnSuccess(response)
                    true -> iRequestListener.onSuccess(response)
                }
            }
        })
    }

    override fun getArticleFeedDetail(
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


    override fun upReactionNew(
        legacyId: Long,
        emojiCode: String,
        iRequestListener: IRequestListener<Response4LikeAndUnLike>
    ) {
        initApiService()

        iApiService.upReactionNew(legacyId, emojiCode)
            .enqueue(object : retrofit2.Callback<Response4LikeAndUnLike> {
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

    override fun downReactionnew(
        legacyId: Long,
        emojiCode: String,
        iRequestListener: IRequestListener<Response4LikeAndUnLike>
    ) {

        initApiService()

        iApiService.downReactionNew(legacyId, emojiCode)
            .enqueue(object : retrofit2.Callback<Response4LikeAndUnLike> {
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

    override fun upReaction(
        articleId: String,
        emojiCode: String,
        iRequestListener: IRequestListener<Response4EmojiReaction>
    ) {

        initAPIServiceV2TestCluster()

        iApiService.upReaction(articleId, emojiCode)
            .enqueue(object : retrofit2.Callback<Response4EmojiReaction> {
                override fun onFailure(call: Call<Response4EmojiReaction>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4EmojiReaction>?,
                    response: Response<Response4EmojiReaction>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun downReaction(
        articleId: String,
        emojiCode: String,
        iRequestListener: IRequestListener<Response4EmojiReaction>
    ) {

        initAPIServiceV2TestCluster()

        iApiService.downReaction(articleId, emojiCode)
            .enqueue(object : retrofit2.Callback<Response4EmojiReaction> {
                override fun onFailure(call: Call<Response4EmojiReaction>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4EmojiReaction>?,
                    response: Response<Response4EmojiReaction>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }


    override fun addFavorite(
        legacyId: Long,
        iRequestListener: IRequestListener<Response4AddFavoriteArticle>
    ) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.addFavoriteArticle(
            "Bearer $accessToken",
            legacyId
        ).enqueue(object : retrofit2.Callback<Response4AddFavoriteArticle> {
            override fun onFailure(call: Call<Response4AddFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4AddFavoriteArticle>?,
                response: Response<Response4AddFavoriteArticle>?
            ) {
                when (response?.isSuccessful) {
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
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.deleteFavoriteArticle(
            "Bearer $accessToken",
            legacyId
        ).enqueue(object : retrofit2.Callback<Response4AddFavoriteArticle> {
            override fun onFailure(call: Call<Response4AddFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4AddFavoriteArticle>?,
                response: Response<Response4AddFavoriteArticle>?
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

        initApiService()

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
        initApiService()

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

}