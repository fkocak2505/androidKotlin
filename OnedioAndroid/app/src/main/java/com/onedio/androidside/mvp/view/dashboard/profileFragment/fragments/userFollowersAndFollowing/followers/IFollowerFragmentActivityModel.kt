package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers

import com.onedio.androidside.IRequestListener
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing

interface IFollowerFragmentActivityModel {

    fun initApiServiceNew()

    fun getUserFollowers(userId: String, page: Int, iRequestListener: IRequestListener<Response4UserFollowerAndFollowing>)

    fun getUserFollowing(userId: String, page: Int, iRequestListener: IRequestListener<Response4UserFollowerAndFollowing>)

}