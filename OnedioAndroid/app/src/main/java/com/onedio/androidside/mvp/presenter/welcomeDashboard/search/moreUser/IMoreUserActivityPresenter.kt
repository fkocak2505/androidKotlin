package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreUser

interface IMoreUserActivityPresenter {

    fun filterSearchedUserByParams(searchedWord: String, page: Int, perPage: Int)

}