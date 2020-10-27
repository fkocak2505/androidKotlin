package com.onedio.androidside.mvp.presenter.welcomeDashboard.search

interface ISearchActivityViewPresenter {

    fun getMostSharedArticle()

    fun getMostPopularArticle()

    fun getSearchData(type: String, keyword: String, page: Int, perPage: Int)

}