package com.onedio.androidside.mvp.view.dashboard.search

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.FeaturedNewsDataModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.SearchListDataModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch

interface ISearchAcitivityView {

    fun initSearchActivityComponent()

    fun getMostSharedArticle()

    //fun handleMostSharedArticle(response4MostSharedArticle: Response4MostSharedArticle)

    fun handleMostPopularArticle(response4ArticlesFeed: Response4ArticlesFeed)

    fun setMostSharedArticleGridAdapter(mListOfMostSharedArticle: MutableList<FeaturedNewsDataModel>)

    fun fillMostSharedArticleData(response4MostSharedArticle: Response4ArticlesFeed): MutableList<FeaturedNewsDataModel>

    fun clickSearchArea()

    fun changeVisibilityNewsFeatured(isVisible: Boolean)

    fun handleUserSearchData(response4UserSearch: Response4UserSearch)

    fun setUserSearchedData2Adapter(mUserList: MutableList<SearchListDataModel>, response4UserSearch: Response4UserSearch)

    fun handleTagSearchData(response4Tags: Response4Tags)

    fun setTagSearchedData2Adapter(mTagList: MutableList<String>,response4Tags: Response4Tags)

    fun handleArticleSearchData(response4Article: Response4Article)

    fun setArticleSearchData2Adapter(mArticleList: MutableList<FeaturedNewsDataModel>, response4Article: Response4Article)

    fun clickMoreContent()

    fun clickMoreUser()

    fun clickMoreTag()

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun changeComponentTypeFace()

}