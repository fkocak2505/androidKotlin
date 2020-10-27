package com.onedio.androidside.mvp.model.dashboard.profileDetail

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel.UpdateUserProfileInfoParams
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfilePhoto
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList.Response4CountryList

interface IUserProfileInfoDetailActivityModel {

    fun initApiService()

    fun initApiServiceNew()

    fun setUpdateUserProfilePhotoParams(image: String)

    fun getUserProfileData(userId: String, iRequestListener: IRequestListener<Response4UsersProfile>)

    fun updateUserProfileInfoData(updateUserProfileInfoParams: UpdateUserProfileInfoParams, iRequestListener: IRequestListener<Response4UpdateUserProfileInfo>)

    fun updateUserProfileInfoDataNew(updateUserProfileInfoParams: UpdateUserProfileInfoParams, iRequestListener: IRequestListener<Response4UpdateUserProfileInfo>)

    fun updateUserProfilePhoto(image: String, iRequestListener: IRequestListener<Response4UpdateUserProfilePhoto>)

    fun getCountries(iRequestListener: IRequestListener<Response4CountryList>)

}