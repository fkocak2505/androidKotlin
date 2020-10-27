package com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_profile_user_follower_and_following.*
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.viewPagerAdapter.UserFollowerAndFollowingActivityViewPagerAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern

class UserFollowerAndFollowingActivityViewImpl : AppCompatActivity(),
    IUserFollowerAndFollowingActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var userFollowerAndFollowingActivityViewPagerAdapter: UserFollowerAndFollowingActivityViewPagerAdapter

    private var theme: String = ""

    //==================================================================================================================
    /**
     * onCreate Override Method...
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user_follower_and_following)

        val sharedPref = getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        initUserFollowerAndFollowingComponent()

        prepareToolbar()

        changeIconIfDarkModeOn()

        changeComponentTypeFace()

    }

    //==================================================================================================================
    /**
     * Init User Follower and Following Component...
     */
    //==================================================================================================================
    override fun initUserFollowerAndFollowingComponent() {
        //supportActionBar?.hide()

        userFollowerAndFollowingActivityViewPagerAdapter =
            UserFollowerAndFollowingActivityViewPagerAdapter(
                supportFragmentManager
            )
        mainViewPager.adapter = userFollowerAndFollowingActivityViewPagerAdapter
        mainViewPager.currentItem = OnedioSingletonPattern.instance.getIndex()
        tabs.setupWithViewPager(mainViewPager)

    }

    private fun prepareToolbar() {
        toolBar = toolBar4ProfileTakipBilgileri as Toolbar

        toolBar.title = "Takip Bilgileri"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if(theme == "dark"){
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

    }

    //==================================================================================================================
    /**
     * Change icon if dark mode on...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            //goBack.setImageResource(R.drawable.ic_back_dark_mode)
        } else {
            //goBack.setImageResource(R.drawable.ic_back)
        }
    }

    //==================================================================================================================
    /**
     * Change Component TypeFace...
     */
    //==================================================================================================================
    override fun changeComponentTypeFace() {
        //followInfo.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}