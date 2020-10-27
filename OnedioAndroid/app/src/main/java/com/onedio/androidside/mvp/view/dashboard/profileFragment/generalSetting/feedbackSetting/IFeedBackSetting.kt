package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.feedbackSetting

import com.onedio.androidside.mvp.model.Response4ErrorObj

interface IFeedBackSetting {

    fun handleFeedBackResponse(response4SendFeedback: String)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)
}