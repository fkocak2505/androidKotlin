package com.onedio.androidside.mvp.view.dashboard.onedioFragment

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory

interface IOnedioFragmentActivityView {

    fun initOnedioFeedComponent()

    fun getAllCategoryAsTree()

    fun handleAllCategoryData(response4AllCategory: Response4AllCategory)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun showEzDialogMessage(titleText: String, messageText: String, buttonText: String, headerColor: Int, titleTextColor: Int, messageTextColor: Int, buttonTextColor: Int)

}