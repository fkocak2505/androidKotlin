package com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed

interface IBottomSheetFragment {

    fun initBottomSheetFragmentData()

    fun changeIconIfDarkModeOn()

    fun checkApplicationSettingListData()

    /*fun setDarkModeSwitchVal(isChecked: Boolean)

    fun darkModeSwitchChangeListener()*/

    //fun prepareSectionExpandableListViewData()

    fun clickGoToSetting()

    fun handleOnedioButtonData(wName: String, response4ArticleFeed: Response4ArticlesFeed)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

}