package com.onedio.androidside.mvp.model.dashboard

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.responseModel.content.Response4UserContentData
import com.onedio.androidside.mvp.model.dashboard.responseModel.contentComment.Response4UserContentCommentData
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.Response4UserArticle

interface IUserProfileActivityModel {

    fun initApiService()

    fun initApiServiceV2()

    fun initApiServiceNew()

    fun getUserProfileData(userId: String, iRequestListener: IRequestListener<Response4UsersProfile>)

    fun getUserFollowings(userId: String, page: Int,iRequestListener: IRequestListener<Response4UserFollowerAndFollowing>)

    fun getUserFollowers(userId: String, page: Int, iRequestListener: IRequestListener<Response4UserFollowerAndFollowing>)

    fun getUserFavoriteArticles(userId: String,iRequestListener: IRequestListener<Response4UserFavoriteArticle>)

    fun getUserFavoriteArticlesInfinite(userId: String, page: Int, iRequestListener: IRequestListener<Response4UserFavoriteArticle>)

    fun getUserArticleData(userId: String,iRequestListener: IRequestListener<Response4UserArticle>)

    fun getUserArticlesInfinite(userId: String, page: Int, iRequestListener: IRequestListener<Response4UserArticle>)

    fun getUserProfileInfo(iRequestListener: IRequestListener<Response4UserProfileInfo>)

    fun getAnotherUserProfile(iRequestListener: IRequestListener<Response4UserProfileInfo>)

    //fun getUserArticleData(userId: String, iRequestListener: IRequestListener<Response4UserContentData>)

    fun getUserFavoriteData(userId: String, iRequestListener: IRequestListener<Response4UserContentData>)

    fun getUserContentComment(iRequestListener: IRequestListener<Response4UserContentCommentData>)

    fun doBlockUser(iRequestListener: IRequestListener<Response4DoBlockedUser>)

    fun getUserBlockedList(iRequestListener: IRequestListener<Response4UserBlockedList>)

}