package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.following

import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing

interface IFollowingFragmentActivityView {

    fun initUserFollowingActivityComponents()

    fun changeTypeFace()

    fun propRecylerViewComponent()

    fun handleUserFollowerData(response4UserFollowerAndFollowing: Response4UserFollowerAndFollowing)

    fun showLoading()

    fun hideLoading()

    fun showError(response4ErrorObj: Response4ErrorObj)

}