package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers

import retrofit2.Call
import retrofit2.Response
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.singleton.OnedioSingletonPattern

class FollowerFragmentActivityModelImpl:
    IFollowerFragmentActivityModel {

    private lateinit var iApiService: IApiService

    override fun initApiServiceNew() {
        iApiService = ApiUtils.getAPIServiceSlider()
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

    override fun getUserFollowing(
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


}