package com.onedio.androidside.mvp.presenter.dashboard

interface IUserProfileInfoActivityPresenter {

    fun getUserProfileData(userId: String)

    fun getUserFavoriteArticles(userId: String)

    fun getUserFavoriteArticlesInfinite(userId: String, page: Int)

    fun getUserArticleData(userId: String)

    fun getUserArticlesInfinite(userId: String, page: Int)

    fun getUserFollowers(userId: String, page: Int)

    fun getUserFollowings(userId: String, page: Int)

    fun getUserProfileInfo()

    fun getAnotherUserProfile()

    //fun getUserArticleData(userId : String)

    fun getUserFavoriteData(userId: String)

    fun doBlockUser()

    fun getUserBlockedList()

}