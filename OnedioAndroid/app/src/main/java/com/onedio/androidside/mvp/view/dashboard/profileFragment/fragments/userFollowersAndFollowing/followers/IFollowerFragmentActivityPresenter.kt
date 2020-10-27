package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers

interface IFollowerFragmentActivityPresenter {

    fun getUserFollowers(userId: String, page: Int)

}