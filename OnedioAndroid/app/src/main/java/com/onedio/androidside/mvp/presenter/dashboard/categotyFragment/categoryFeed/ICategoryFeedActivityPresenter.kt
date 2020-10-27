package com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed

interface ICategoryFeedActivityPresenter {


    fun getArticleFeedByCategoryIdNew(isProgressList: Boolean, categoryId: String, page: Int, perPage: Int, dateFilterType: String, sortType: String, isFirstLoad: Boolean)

}