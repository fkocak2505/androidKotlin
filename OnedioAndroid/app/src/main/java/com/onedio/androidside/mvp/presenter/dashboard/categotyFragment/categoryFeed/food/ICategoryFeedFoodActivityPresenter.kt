package com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed.food

interface ICategoryFeedFoodActivityPresenter {

    fun getCategoryFeedFoodDataNew(isProgressList: Boolean, categoryId: String, page: Int, perPage: Int,dateFilterType: String, sortType: String, isFirstLoad: Boolean)

}