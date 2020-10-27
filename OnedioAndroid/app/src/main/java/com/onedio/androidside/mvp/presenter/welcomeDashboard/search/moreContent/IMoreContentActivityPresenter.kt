package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreContent

interface IMoreContentActivityPresenter {

    fun filterSearchedContentByParams(searchedWord: String, page: Int, perPage: Int, date: String, sort: String?)

}