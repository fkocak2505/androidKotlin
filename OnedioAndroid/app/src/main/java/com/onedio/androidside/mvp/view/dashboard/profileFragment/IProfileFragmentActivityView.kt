package com.onedio.androidside.mvp.view.dashboard.profileFragment

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.Response4UserArticle

interface IProfileFragmentActivityView {

    fun initComponent()

    fun changeComponentTextFont()

    fun changeIconIfDarkModeOn()

    fun clickProfileSetting()

    fun clickUserFollowerCountOrText()

    fun clickUserFollowingCountOrText()

    fun clickUserBlockedUser()

    fun clickDoUserBlock()

    fun clickStartMessageButton()

    //fun getUserProfilInfo()

    fun fillUserProfileInfo()

    fun isAnotherUserBlock()

    fun getAnotherUserProfile()

    fun showLoading()

    fun hideLoading()

    fun handleUserProfileInfoData(response4UsersProfile: Response4UsersProfile, userId: String)

    fun handleUserFavoriteArticle(response4UserFavoriteArticle: Response4UserFavoriteArticle, userId: String)

    fun handleUserFavoriteArticleInfinite(response4UserFavoriteArticle: Response4UserFavoriteArticle, userId: String)

    fun handleUserArticlesData(response4UserArticle: Response4UserArticle, userId: String)

    fun handleUserArticlesDataInfinite(response4UserArticle: Response4UserArticle)

    fun handleUserFollowingsData(response4UserFollowerAndFollowing: Response4UserFollowerAndFollowing)

    fun handleUserFollowersData(response4UserFollowerAndFollowing: Response4UserFollowerAndFollowing)

    fun handleAnotherUserProfileInfoData(response4UserProfileInfo: Response4UserProfileInfo)

    fun handleAnotherUserBlockInfoData(response4DoBlockedUser: Response4DoBlockedUser)

    fun handleUserBlockedList(resposne4UserBlockedList: Response4UserBlockedList)

    fun blockAnotherUserProperty()

    fun showError(response4ErrorObj: Response4ErrorObj)

    fun forNow()

}