package com.onedio.androidside.mvp.view.dashboard.search.moreUser

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch

interface IMoreUserActivityView {

    fun initMoreUserComponent()

    fun fillSearchAllUsersData()

    fun setSearchedWordAndCount(searchedText: String, searchedCount: String)

    fun changeComponentTypeFace()

    fun handleFilteredSearchData(response4UserSearch: Response4UserSearch)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

}