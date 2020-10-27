package com.onedio.androidside.mvp.view.dashboard.categoryFragment

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory

interface ICategoryFragmentActivityView {

    fun initCategoryComponent()

    fun getAllCategoryAsTree()

    fun handleCategoryData(response4AllCategory: Response4AllCategory)

    fun handleBadgeCategoryData(response4AllCategory: Response4AllCategory)

    fun showLoading()

    fun hideLoading()

    fun showError(methodName: String, response4ErrorObj: Response4ErrorObj)



}