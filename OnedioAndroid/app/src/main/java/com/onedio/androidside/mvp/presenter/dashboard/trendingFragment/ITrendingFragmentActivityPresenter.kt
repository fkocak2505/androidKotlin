package com.onedio.androidside.mvp.presenter.dashboard.trendingFragment

interface ITrendingFragmentActivityPresenter {

    fun getTrendDataNew(page: Int, perPage: Int, isFirst: Boolean)

    fun getTrendData(page: Int, perPage: Int)

}