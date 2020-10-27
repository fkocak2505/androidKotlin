package com.onedio.androidside.mvp.presenter.dashboard.profileDetail

import com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel.UpdateUserProfileInfoParams

interface IUserProfileInfoDetailActivityPresenter {

    fun updateUserProfileInfoData(updateUserProfileInfoParams: UpdateUserProfileInfoParams)

    fun updateUserProfileInfoDataNew(updateUserProfileInfoParams: UpdateUserProfileInfoParams)

    fun updateUserProfilePhoto(image: String)

    fun getUserProfileData(userId: String)

    fun getCountries()

}