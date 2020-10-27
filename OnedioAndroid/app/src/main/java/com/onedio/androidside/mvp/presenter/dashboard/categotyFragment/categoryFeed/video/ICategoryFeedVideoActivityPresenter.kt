package com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed.video

interface ICategoryFeedVideoActivityPresenter {

    fun getVideoArticleDataNew(isProgressList: Boolean, categoryId: String, page: Int, perPage: Int, dateFilterType: String, sortType: String, isFirstLoad: Boolean)

}