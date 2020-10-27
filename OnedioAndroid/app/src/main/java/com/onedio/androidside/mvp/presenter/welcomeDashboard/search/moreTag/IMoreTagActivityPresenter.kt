package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreTag

interface IMoreTagActivityPresenter {

    fun filterSearchedTagByParams(searchedWord: String, page: Int, perPage: Int)

}