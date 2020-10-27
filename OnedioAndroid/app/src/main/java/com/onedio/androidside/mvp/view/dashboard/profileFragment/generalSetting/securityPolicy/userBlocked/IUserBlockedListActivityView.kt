package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.securityPolicy.userBlocked

import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.BlockedUser
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList

interface IUserBlockedListActivityView {

    fun initUserBlockedActivityComponent()

    fun changeComponentTypeFace()

    fun changeIconIfDarkModeOn()

    fun getUserBlockedListData()

    fun handleUserBlockedListData(response4UserBlockedList: Response4UserBlockedList)

    fun propRecylerViewComponent(listOfBlockedList: List<BlockedUser>)

    fun showLoading()

    fun hideLoading()

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

    fun goSecurityActivityView()

}