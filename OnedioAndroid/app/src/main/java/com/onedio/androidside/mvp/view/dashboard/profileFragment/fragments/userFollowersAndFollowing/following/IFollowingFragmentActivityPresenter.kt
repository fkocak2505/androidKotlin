package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.following

interface IFollowingFragmentActivityPresenter {

    fun getUserFollowing(userId: String, page: Int)

}