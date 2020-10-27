package com.onedio.androidside.mvp.model.dashboard

import com.google.gson.Gson
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.responseModel.content.Response4UserContentData
import com.onedio.androidside.mvp.model.dashboard.responseModel.contentComment.Response4UserContentCommentData
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.Response4UserArticle
import com.onedio.androidside.singleton.OnedioSingletonPattern
import retrofit2.Call
import retrofit2.Response

class UserProfileActivityModelImpl :
    IUserProfileActivityModel {

    private lateinit var iApiService: IApiService
    private lateinit var gson: Gson

    //==================================================================================================================
    /**
     * Init Api Service..
     */
    //==================================================================================================================
    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
    }

    override fun initApiServiceV2() {
        iApiService = ApiUtils.getAPIServiceV2()
    }

    override fun initApiServiceNew() {
        iApiService = ApiUtils.getAPIServiceSlider()
    }


    override fun getUserProfileData(userId: String, iRequestListener: IRequestListener<Response4UsersProfile>) {

        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUsersProfileData("Bearer $accessToken",userId).enqueue(object : retrofit2.Callback<Response4UsersProfile>{
            override fun onFailure(call: Call<Response4UsersProfile>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UsersProfile>?,
                response: Response<Response4UsersProfile>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getUserFollowings(
        userId: String,
        page: Int,
        iRequestListener: IRequestListener<Response4UserFollowerAndFollowing>
    ) {
        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUserFollowings("Bearer $accessToken", userId, page).enqueue(object : retrofit2.Callback<Response4UserFollowerAndFollowing>{
            override fun onFailure(call: Call<Response4UserFollowerAndFollowing>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserFollowerAndFollowing>?,
                response: Response<Response4UserFollowerAndFollowing>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getUserFollowers(
        userId: String,
        page: Int,
        iRequestListener: IRequestListener<Response4UserFollowerAndFollowing>
    ) {
        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUserFollowers("Bearer $accessToken", userId, page).enqueue(object : retrofit2.Callback<Response4UserFollowerAndFollowing>{
            override fun onFailure(call: Call<Response4UserFollowerAndFollowing>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserFollowerAndFollowing>?,
                response: Response<Response4UserFollowerAndFollowing>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }


    //==================================================================================================================
    /**
     * Model Request for UserProfileInfo Data..
     */
    //==================================================================================================================
    override fun getUserProfileInfo(iRequestListener: IRequestListener<Response4UserProfileInfo>) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.getUserProfileInfo(
            token,
            "Bearer $accessToken"
        ).enqueue(object : retrofit2.Callback<Response4UserProfileInfo> {
            override fun onFailure(call: Call<Response4UserProfileInfo>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserProfileInfo>?,
                response: Response<Response4UserProfileInfo>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getUserFavoriteArticles(userId: String, iRequestListener: IRequestListener<Response4UserFavoriteArticle>) {
        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUserFavoriteArticles("Bearer $accessToken", userId).enqueue(object : retrofit2.Callback<Response4UserFavoriteArticle>{
            override fun onFailure(call: Call<Response4UserFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserFavoriteArticle>?,
                response: Response<Response4UserFavoriteArticle>?
            ) {
                when(response?.isSuccessful){
                    false -> iRequestListener.onUnSuccess(response)
                    true -> iRequestListener.onSuccess(response)
                }
            }
        })
    }

    override fun getUserFavoriteArticlesInfinite(
        userId: String,
        page: Int,
        iRequestListener: IRequestListener<Response4UserFavoriteArticle>
    ) {
        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUserFavoriteArticlesInfinite("Bearer $accessToken", userId, page).enqueue(object : retrofit2.Callback<Response4UserFavoriteArticle>{
            override fun onFailure(call: Call<Response4UserFavoriteArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserFavoriteArticle>?,
                response: Response<Response4UserFavoriteArticle>?
            ) {
                when(response?.isSuccessful){
                    false -> iRequestListener.onUnSuccess(response)
                    true -> iRequestListener.onSuccess(response)
                }
            }
        })
    }

    override fun getUserArticleData(userId: String, iRequestListener: IRequestListener<Response4UserArticle>) {
        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUserArticles("Bearer $accessToken", userId).enqueue(object : retrofit2.Callback<Response4UserArticle>{
            override fun onFailure(call: Call<Response4UserArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserArticle>?,
                response: Response<Response4UserArticle>?
            ) {
                when(response?.isSuccessful){
                    false -> iRequestListener.onUnSuccess(response)
                    true -> iRequestListener.onSuccess(response)
                }
            }
        })
    }

    override fun getUserArticlesInfinite(
        userId: String,
        page: Int,
        iRequestListener: IRequestListener<Response4UserArticle>
    ) {
        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUserArticlesInfinite("Bearer $accessToken", userId, page).enqueue(object : retrofit2.Callback<Response4UserArticle>{
            override fun onFailure(call: Call<Response4UserArticle>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserArticle>?,
                response: Response<Response4UserArticle>?
            ) {
                when(response?.isSuccessful){
                    false -> iRequestListener.onUnSuccess(response)
                    true -> iRequestListener.onSuccess(response)
                }
            }
        })
    }

    override fun getAnotherUserProfile(iRequestListener: IRequestListener<Response4UserProfileInfo>) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.getAnotherUserProfile(
            token,
            "Bearer $accessToken",
            OnedioSingletonPattern.instance.getAnotherUserId()
        ).enqueue(object : retrofit2.Callback<Response4UserProfileInfo> {
            override fun onFailure(call: Call<Response4UserProfileInfo>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserProfileInfo>?,
                response: Response<Response4UserProfileInfo>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun doBlockUser(iRequestListener: IRequestListener<Response4DoBlockedUser>) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.doUserBlocked(
            token,
            "Bearer $accessToken",
            OnedioSingletonPattern.instance.getAnotherUserName()).enqueue(object : retrofit2.Callback<Response4DoBlockedUser>{
            override fun onFailure(call: Call<Response4DoBlockedUser>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(call: Call<Response4DoBlockedUser>?, response: Response<Response4DoBlockedUser>?) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getUserBlockedList(iRequestListener: IRequestListener<Response4UserBlockedList>) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.getUserBlockedListData(
            token,
            "Bearer $accessToken").enqueue(object : retrofit2.Callback<Response4UserBlockedList>{
            override fun onFailure(call: Call<Response4UserBlockedList>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UserBlockedList>?,
                response: Response<Response4UserBlockedList>?
            ) {
                when(response?.isSuccessful){
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    /*override fun getUserArticleData(userId: String, iRequestListener: IRequestListener<Response4UserContentData>) {
        initApiServiceV2()

        //5c5a888ef978fdf41823dafc

        iApiService.getUserArticleData(userId)
            .enqueue(object : retrofit2.Callback<Response4UserContentData> {
                override fun onFailure(call: Call<Response4UserContentData>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4UserContentData>?,
                    response: Response<Response4UserContentData>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }*/

    override fun getUserFavoriteData(userId: String, iRequestListener: IRequestListener<Response4UserContentData>) {
        initApiServiceV2()

        iApiService.getUserFavoriteData(userId)
            .enqueue(object : retrofit2.Callback<Response4UserContentData> {
                override fun onFailure(call: Call<Response4UserContentData>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4UserContentData>?,
                    response: Response<Response4UserContentData>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })
    }

    override fun getUserContentComment(iRequestListener: IRequestListener<Response4UserContentCommentData>) {
        initApiServiceV2()


    }


}