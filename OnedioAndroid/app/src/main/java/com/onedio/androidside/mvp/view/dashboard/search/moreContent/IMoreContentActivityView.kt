package com.onedio.androidside.mvp.view.dashboard.search.moreContent

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article

interface IMoreContentActivityView {

    fun initMoreContentComponent()

    fun fillSearchAllContentData()

    fun setSearchedWordAndCount(searchedText: String, searchedCount: String)

    fun clickDateSort()

    fun clickPopularitySort()

    fun changeComponentTypeFace()

    fun handleFilteredSearchData(response4Article: Response4Article)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

}