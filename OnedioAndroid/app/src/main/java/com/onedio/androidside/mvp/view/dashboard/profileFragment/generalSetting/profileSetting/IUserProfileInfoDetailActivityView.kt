package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.profileSetting

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfilePhoto
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList.Response4CountryList

interface IUserProfileInfoDetailActivityView {

    fun initProfileSettingActivityComponents()

    fun changeIconIfDarkModeOn()

    fun changeNavMenuIcon()

    fun checkIconIfDarkModeOn(): Boolean

    fun changeComponentTypeface()

    fun initDatePicker()

    fun getCountryList()

    fun changeUserBirthday2Local(birthDay: String): String

    fun updateUserProfileInfoData()

    fun clickUpdateUserProfileInfo()

    fun clickProfilePic()

    fun pickImageFromGallery()

    fun handleUserProfileData(response4UsersProfile: Response4UsersProfile)

    fun handleUpdateUserProfileInfo(response4UpdateUserProfileInfo: Response4UpdateUserProfileInfo)

    fun handleUpdateUserProfilePhotoInfo(response4UpdateUserProfilePhoto: Response4UpdateUserProfilePhoto)

    fun handleCountryListData(response4CountryList: Response4CountryList)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj : Response4ErrorObj)

    //fun goBack()

    fun clickEmptyArea()

    fun scrollEditText()

    fun edtOfTouchListener()

    fun showEzDialogMessage(isCallbackTrigger: Boolean, titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

    //fun goDashboard()
}