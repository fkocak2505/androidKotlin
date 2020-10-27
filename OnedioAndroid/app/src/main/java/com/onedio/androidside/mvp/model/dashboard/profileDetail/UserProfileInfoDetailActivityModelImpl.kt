package com.onedio.androidside.mvp.model.dashboard.profileDetail

import com.google.gson.Gson
import com.onedio.androidside.IRequestListener
import com.onedio.androidside.api.ApiUtils
import com.onedio.androidside.api.interfaced.IApiService
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel.UpdateUserProfileInfoParams
import com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel.UpdateUserProfilePhotoParams
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfilePhoto
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList.Response4CountryList
import com.onedio.androidside.singleton.OnedioSingletonPattern
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class UserProfileInfoDetailActivityModelImpl :
    IUserProfileInfoDetailActivityModel {

    private lateinit var iApiService: IApiService
    private lateinit var gson: Gson


    private lateinit var updateUserProfilePhotoParams: UpdateUserProfilePhotoParams

    override fun initApiService() {
        iApiService = ApiUtils.getAPIService()
        gson = Gson()
    }

    override fun initApiServiceNew() {
        iApiService = ApiUtils.getAPIServiceSlider()
        gson = Gson()
    }

    override fun setUpdateUserProfilePhotoParams(image: String) {
        gson = Gson()
        updateUserProfilePhotoParams =
            UpdateUserProfilePhotoParams()
        updateUserProfilePhotoParams.image = image
    }

    override fun updateUserProfileInfoData(
        updateUserProfileInfoParams: UpdateUserProfileInfoParams,
        iRequestListener: IRequestListener<Response4UpdateUserProfileInfo>
    ) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.updateUserProfileInfo(
            token, "Bearer $accessToken",
            RequestBody.create(
                MediaType.parse("application/json"),
                gson.toJson(updateUserProfileInfoParams)
            )
        ).enqueue(object : retrofit2.Callback<Response4UpdateUserProfileInfo> {
            override fun onFailure(call: Call<Response4UpdateUserProfileInfo>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UpdateUserProfileInfo>?,
                response: Response<Response4UpdateUserProfileInfo>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun updateUserProfileInfoDataNew(
        updateUserProfileInfoParams: UpdateUserProfileInfoParams,
        iRequestListener: IRequestListener<Response4UpdateUserProfileInfo>
    ) {

        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.updateUserProfileInfoNew(
            token, "Bearer $accessToken",
            RequestBody.create(
                MediaType.parse("application/json"),
                gson.toJson(updateUserProfileInfoParams)
            )
        ).enqueue(object : retrofit2.Callback<Response4UpdateUserProfileInfo> {
            override fun onFailure(call: Call<Response4UpdateUserProfileInfo>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UpdateUserProfileInfo>?,
                response: Response<Response4UpdateUserProfileInfo>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun updateUserProfilePhoto(
        image: String,
        iRequestListener: IRequestListener<Response4UpdateUserProfilePhoto>
    ) {
        initApiService()

        setUpdateUserProfilePhotoParams(image)

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.updateUserProfilePhoto(
            token,
            "Bearer $accessToken",
            RequestBody.create(
                MediaType.parse("application/json"),
                gson.toJson(updateUserProfilePhotoParams)
            )
        ).enqueue(object : retrofit2.Callback<Response4UpdateUserProfilePhoto> {
            override fun onFailure(call: Call<Response4UpdateUserProfilePhoto>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4UpdateUserProfilePhoto>?,
                response: Response<Response4UpdateUserProfilePhoto>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }

    override fun getUserProfileData(
        userId: String,
        iRequestListener: IRequestListener<Response4UsersProfile>
    ) {
        initApiServiceNew()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)

        iApiService.getUsersProfileData("Bearer $accessToken", userId)
            .enqueue(object : retrofit2.Callback<Response4UsersProfile> {
                override fun onFailure(call: Call<Response4UsersProfile>?, t: Throwable?) {
                    iRequestListener.onFail(t)
                }

                override fun onResponse(
                    call: Call<Response4UsersProfile>?,
                    response: Response<Response4UsersProfile>?
                ) {
                    when (response?.isSuccessful) {
                        true -> iRequestListener.onSuccess(response)
                        false -> iRequestListener.onUnSuccess(response)
                    }
                }
            })

    }

    override fun getCountries(iRequestListener: IRequestListener<Response4CountryList>) {
        initApiService()

        val sharedPreferences = OnedioCommon.getSharedPref()
        val accessToken = OnedioCommon.getAccessToken(sharedPreferences)
        val token = OnedioCommon.getTokken(sharedPreferences)

        iApiService.getCountryList(
            token,
            "Bearer $accessToken"
        ).enqueue(object : retrofit2.Callback<Response4CountryList> {
            override fun onFailure(call: Call<Response4CountryList>?, t: Throwable?) {
                iRequestListener.onFail(t)
            }

            override fun onResponse(
                call: Call<Response4CountryList>?,
                response: Response<Response4CountryList>?
            ) {
                when (response?.isSuccessful) {
                    true -> iRequestListener.onSuccess(response)
                    false -> iRequestListener.onUnSuccess(response)
                }
            }
        })
    }
}