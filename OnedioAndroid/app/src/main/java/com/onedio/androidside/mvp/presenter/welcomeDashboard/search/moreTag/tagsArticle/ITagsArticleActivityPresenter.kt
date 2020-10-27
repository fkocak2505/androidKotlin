package com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreTag.tagsArticle

interface ITagsArticleActivityPresenter {

    fun getTagArticle(
        isProgressList: Boolean,
        tagId: String, page: Int, perPage: Int, dateFilterType: String,
        sortType: String,
        isFirstLoad: Boolean
    )

}