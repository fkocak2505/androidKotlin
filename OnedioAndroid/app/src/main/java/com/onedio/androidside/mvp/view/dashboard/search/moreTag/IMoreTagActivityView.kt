package com.onedio.androidside.mvp.view.dashboard.search.moreTag

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags

interface IMoreTagActivityView {

    fun initMoreTagComponent()

    fun fillSearchAllTagsData()

    fun setSearchedWordAndCount(searchedText: String, searchedCount: String)

    fun changeComponentTypeFace()

    fun handleFilteredSearchData(response4Tags: Response4Tags)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

}