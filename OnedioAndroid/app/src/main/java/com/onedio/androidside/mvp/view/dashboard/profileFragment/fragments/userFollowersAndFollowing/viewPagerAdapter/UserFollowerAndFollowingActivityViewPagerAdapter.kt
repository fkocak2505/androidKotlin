package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.followers.FollowerFragmentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.following.FollowingFragmentActivityViewImpl

class UserFollowerAndFollowingActivityViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm)  {

    //==================================================================================================================
    /**
     * Get Item
     */
    //==================================================================================================================
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FollowerFragmentActivityViewImpl()
            }
            else -> FollowingFragmentActivityViewImpl()
        }
    }

    //==================================================================================================================
    /**
     * get Count
     */
    //==================================================================================================================
    override fun getCount(): Int {
        return 2
    }

    //==================================================================================================================
    /**
     * get Page Title
     */
    //==================================================================================================================
    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Takipçi"
            else -> "Takip Edilen"
        }
    }

}