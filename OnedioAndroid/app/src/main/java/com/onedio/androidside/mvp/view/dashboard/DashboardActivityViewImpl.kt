package com.onedio.androidside.mvp.view.dashboard


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.CategoryFragmentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.HomeFeedFragmentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.ProfileActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu.BottomSheetFragment
import com.onedio.androidside.mvp.view.dashboard.search.SearchFragmentActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.TrendingFragmentViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.LoginAndRegisterDashboardActivityViewImpl
import com.onedio.androidside.onesignalConfig.NotificationDataModel
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlin.system.exitProcess


class DashboardActivityViewImpl : AppCompatActivity(),
    IDashboardActivityView {

    private var content: FrameLayout? = null

    private var fm: FragmentManager = supportFragmentManager

    private lateinit var homeFragment: Fragment
    private lateinit var trendingFragment: Fragment
    private lateinit var categoryFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var active: Fragment


    private var isHomeFeed: Boolean = false
    private var isTrending: Boolean = false
    private var isCategory: Boolean = false
    private var isSearch: Boolean = false

    private var isBack: Boolean = false
    private var homeCount: Int = 0

    private var deviceName: String = ""

    private var homeItem: MenuItem? = null

    var mIsStateAlreadySaved = false
    var mPendingShowDialog = false

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var isScreenLoadMultiple: Boolean = false

    private lateinit var sharedPreferences: SharedPreferences

    private var isDarkModeEnabled: Boolean = false

    private var theme: String = ""

    private var isNotif: Boolean = false

    //==================================================================================================================
    /**
     * Activity onCreate Func..
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        sharedPreferences = getSharedPreferences(
            OnedioConstant.SHARED_PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }


        intent.extras?.let {
            isNotif = it.getBoolean("IS_NOTIFICATION_COMING")
        } ?: run {
            isNotif = false
        }

        intent.action?.let {
            val appLinkAction = intent.action
            val appLinkData: Uri? = intent.data
            if (Intent.ACTION_VIEW == appLinkAction) {
                appLinkData?.lastPathSegment?.also { legacyId ->
                    val titleAndLegacyId = legacyId.split("?")[0]
                    val sLastPathSegment = titleAndLegacyId.split("-")
                    val legacyId = sLastPathSegment[sLastPathSegment.size - 1]
                    val articleItem =
                        HugeCardModel(
                            "FROM_NOTIFICATION",
                            legacyId.toLong(),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            false,
                            null,
                            null,
                            false,
                            ""
                        )

                    val intent =
                        Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                    intent.putExtra(
                        "ARTICLE_DETAIL_DATA",
                        Gson().toJson(articleItem)
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        } ?: run {
            intent.getStringExtra("NOTIFICATION_DATA")?.let {
                val notificationData = Gson().fromJson(it, NotificationDataModel::class.java)

                notificationData.legacyid?.let { itemOfLegacyId ->
                    val articleItem =
                        HugeCardModel(
                            "FROM_NOTIFICATION",
                            itemOfLegacyId.toLong(),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            false,
                            null,
                            null,
                            false,
                            ""
                        )

                    if (notificationData.isTest == "true") {
                        val intent =
                            Intent(
                                applicationContext,
                                TestArticleDetailActivityViewImpl::class.java
                            )
                        intent.putExtra(
                            "ARTICLE_DETAIL_DATA",
                            Gson().toJson(articleItem)
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    } else {
                        val intent =
                            Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                        intent.putExtra(
                            "ARTICLE_DETAIL_DATA",
                            Gson().toJson(articleItem)
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                } ?: run {

                }
            } ?: run {

            }
        }




        initProfileActivityComponents()

        clickFabButton()

        isDarkModeEnabled = checkIconIfDarkModeOn()

        changeTheme()

    }

    private fun changeTheme() {
        if (isDarkModeEnabled) {
            navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home_selected_dark_mode)
            navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending_dark_mode)
            navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category_dark_mode)
            navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search_dark_mode)
        } else {
            navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home_selected)
            navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending)
            navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category)
            navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search)
        }
    }


    //==================================================================================================================
    /**
     * Init Profile Activity Components
     */
    //==================================================================================================================
    override fun initProfileActivityComponents() {
        supportActionBar?.hide()

        content = findViewById(R.id.content)
        val navigation = findViewById<BottomNavigationViewEx>(R.id.navigation)
        navigation.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener


        if (!isHomeFeed) {
            homeFragment = HomeFeedFragmentActivityViewImpl.newInstance(isNotif)
            fm.beginTransaction().add(R.id.content, homeFragment, "homeFragment").commit()
            active = homeFragment
            isHomeFeed = true
            //OnedioSingletonPattern.instance.setNav(navigation)
            homeCount++
        }

        setProfilePhoto()

        onedioLogo.setOnClickListener {
            OnedioSingletonPattern.instance.getNestedScrollView().smoothScrollTo(0, 0)
        }

        dioLogo.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://dio.onedio.com"))
            startActivity(browserIntent)
        }

        //changeFragment()
    }

    //==================================================================================================================
    /**
     * Set Profil Photo..
     */
    //==================================================================================================================
    private fun setProfilePhoto() {

        val response4UsersProfile = StringSharedPrefs(
            sharedPreferences,
            OnedioConstant.SHARED_PREF_USER_PROFILE_INFO,
            ""
        ).getValue<String>()

        if (OnedioCommon.isUserLogin()) {

            /*val image = Gson().fromJson(
                OnedioSingletonPattern.instance.getUserProfileInfoData(),
                Response4UsersProfile::class.java
            ).data?.image*/

            val response4UsersProfileObj =
                Gson().fromJson(response4UsersProfile, Response4UsersProfile::class.java)
            var image: String? = null
            response4UsersProfileObj.data?.let {
                it.image?.let {
                    it.alternates?.let {
                        it.standardResolution?.let {
                            it.url?.let {
                                image = it
                            } ?: run {
                                image = ""
                            }
                        } ?: run {
                            image = ""
                        }
                    } ?: run {
                        image = ""
                    }
                } ?: run {
                    image = ""
                }
            } ?: run {
                image = ""
            }

            if (image != "") {
                Picasso.get().load(image)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(profilePhoto, object : Callback {
                        override fun onSuccess() {
                            profilePhotoProgress.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            profilePhotoProgress.visibility = View.GONE
                        }
                    })
            } else {
                profilePhotoProgress.visibility = View.GONE
            }
        } else
            profilePhotoProgress.visibility = View.GONE

        profilePhoto.setOnClickListener {

            if (OnedioCommon.isUserLogin()) {
                if (profileSettingMenu.visibility == View.VISIBLE) {
                    profileSettingMenu.visibility = View.GONE
                } else {
                    profileSettingMenu.visibility = View.VISIBLE
                }

                OnedioSingletonPattern.instance.setProfileSettingPopup(profileSettingMenu)
            } else {
                OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)
                OnedioCommon.nStartActivity(
                    applicationContext!!,
                    LoginAndRegisterDashboardActivityViewImpl::class.java
                )
            }
        }

        /*hideProfileSetting.setOnClickListener {
            profileSettingMenu.visibility = View.GONE
        }*/

        goProfileScreen.setOnClickListener {
            profileSettingMenu.visibility = View.GONE
            if (OnedioCommon.isUserLogin()) {
                val intent = Intent(applicationContext, ProfileActivityViewImpl::class.java)
                intent.putExtra("IS_USER_OWN_PROFILE", true)
                intent.putExtra("ANOTHER_USER_PROFILE", "")
                startActivityForResult(intent, 1)
                /*OnedioSingletonPattern.instance.setIsUserOwnProfile(true)
                startActivity(
                    Intent(
                        applicationContext,
                        ProfileActivityViewImpl::class.java
                    )
                )*/
            } else {
                startActivity(
                    Intent(
                        applicationContext,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                )
            }
        }

        goMessage.setOnClickListener {
            /*if (OnedioCommon.isUserLogin()) {
                startActivity(
                    Intent(
                        applicationContext,
                        MessageActivityViewImpl::class.java
                    )
                )
            } else {
                startActivity(
                    Intent(
                        applicationContext,
                        LoginAndRegisterDashboardActivityViewImpl::class.java
                    )
                )
            }*/
        }

        goNotification.setOnClickListener {
            if (OnedioCommon.isUserLogin()) {

            } else {

            }
        }
    }

    //==================================================================================================================
    /**
     * Check Icon If Dark Mode On...
     */
    //==================================================================================================================
    override fun checkIconIfDarkModeOn(): Boolean {
        theme = sharedPreferences.getString("mode", "default")!!
        return theme == "dark"
    }

    //==================================================================================================================
    /**
     * Menu Item for Onedio Feed...
     */
    //==================================================================================================================
    override fun clickFabButton() {
        fab.setOnClickListener {
            /*val bottomSheetFragment = BottomSheetFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(bottomSheetFragment, "bottomSheetFragment")
            ft.commitAllowingStateLoss()*/
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        if (mIsStateAlreadySaved) {
            mPendingShowDialog = true
        } else {
            val bottomSheetFragment =
                BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }


    }

    //==================================================================================================================
    /**
     * Bottom Navigation Menu Listener Menu Items..
     */
    //==================================================================================================================
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {

                    firebaseAnalytics = FirebaseAnalytics.getInstance(this)
                    firebaseAnalytics.setCurrentScreen(
                        this,
                        "Ana Sayfa",
                        "HomeFeedFragmentActivityViewImpl"
                    )

                    homeItem = item
                    profileSettingMenu.visibility = View.GONE

                    /*val fragment = HomeFeedFragmentActivityViewImpl.newInstance()
                    addFragment(fragment)*/
                    if (!isHomeFeed) {
                        homeFragment =
                            HomeFeedFragmentActivityViewImpl()
                        fm.beginTransaction().add(R.id.content, homeFragment, "homeFragment")
                            .hide(homeFragment).commitAllowingStateLoss()
                        isHomeFeed = true
                    }

                    if (homeCount != 0) {
                        homeCount = 0
                        OnedioSingletonPattern.instance.getNestedScrollView().smoothScrollTo(0, 0)
                    }

                    homeCount++

                    fm.beginTransaction().hide(active).show(homeFragment).commitAllowingStateLoss()
                    active = homeFragment

                    if (isDarkModeEnabled) {
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending_dark_mode)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category_dark_mode)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search_dark_mode)
                        item.setIcon(R.drawable.ic_nav_home_selected_dark_mode)
                    } else {
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search)
                        item.setIcon(R.drawable.ic_nav_home_selected)
                    }

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notification -> {
                    /*val fragment = TrendingFragmentActivityViewImpl()
                    addFragment(fragment)*/

                    firebaseAnalytics = FirebaseAnalytics.getInstance(this)
                    firebaseAnalytics.setCurrentScreen(
                        this,
                        "Trending",
                        "TrendingFragmentActivityViewImpl"
                    )

                    profileSettingMenu.visibility = View.GONE
                    isBack = false
                    homeCount = 0

                    if (!isTrending) {
                        trendingFragment =
                            TrendingFragmentViewImpl()
                        fm.beginTransaction()
                            .add(R.id.content, trendingFragment, "trendingFragment")
                            .hide(trendingFragment).commitAllowingStateLoss()
                        isTrending = true
                    }

                    fm.beginTransaction().hide(active).show(trendingFragment)
                        .commitAllowingStateLoss()
                    active = trendingFragment

                    if (isDarkModeEnabled) {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home_dark_mode)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category_dark_mode)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search_dark_mode)
                        item.setIcon(R.drawable.ic_nav_trending_selected_dark_mode)
                    } else {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search)
                        item.setIcon(R.drawable.ic_nav_trending_selected)
                    }

                    return@OnNavigationItemSelectedListener true
                }
                /*R.id.i_empty -> {
                    val fragment = OnedioFragmentActivityViewImpl()
                    addFragment(fragment)

                    if (checkIconIfDarkModeOn()) {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home_dark_mode)
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending_dark_mode)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category_dark_mode)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search_dark_mode)
                    } else {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home)
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search)
                    }

                    return@OnNavigationItemSelectedListener true
                }*/
                R.id.navigation_message -> {
                    /*val fragment = CategoryFragmentActivityViewImpl()
                    addFragment(fragment)*/

                    profileSettingMenu.visibility = View.GONE

                    isBack = false
                    homeCount = 0

                    if (!isCategory) {
                        categoryFragment =
                            CategoryFragmentActivityViewImpl()
                        fm.beginTransaction()
                            .add(R.id.content, categoryFragment, "categoryFragment")
                            .hide(categoryFragment).commitAllowingStateLoss()
                        isCategory = true;
                    }

                    fm.beginTransaction().hide(active).show(categoryFragment)
                        .commitAllowingStateLoss()
                    active = categoryFragment

                    if (isDarkModeEnabled) {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home_dark_mode)
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending_dark_mode)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search_dark_mode)
                        item.setIcon(R.drawable.ic_nav_category_selected_dark_mode)
                    } else {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home)
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search)
                        item.setIcon(R.drawable.ic_nav_category_selected)
                    }

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    /*val fragment = SearchFragmentActivityViewImpl()
                    addFragment(fragment)*/

                    profileSettingMenu.visibility = View.GONE

                    if (!isSearch) {
                        searchFragment =
                            SearchFragmentActivityViewImpl()
                        fm.beginTransaction().add(R.id.content, searchFragment, "searchFragment")
                            .hide(searchFragment).commitAllowingStateLoss()
                        isSearch = true
                    }

                    fm.beginTransaction().hide(active).show(searchFragment)
                        .commitAllowingStateLoss()
                    active = searchFragment

                    isBack = false
                    homeCount = 0

                    if (isDarkModeEnabled) {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home_dark_mode)
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending_dark_mode)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category_dark_mode)
                        item.setIcon(R.drawable.ic_nav_search_selected_dark_mode)
                    } else {
                        navigation.menu.getItem(0).setIcon(R.drawable.ic_nav_home)
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category)
                        item.setIcon(R.drawable.ic_nav_search_selected)
                    }

                    /*OnedioSingletonPattern.instance.setIsUserOwnProfile(true)*/

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    //==================================================================================================================
    /**
     * Handle Keyboard Back button...
     */
    //==================================================================================================================
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {

                if (!isBack) {
                    fm.beginTransaction().hide(active).show(homeFragment).commit()
                    active = homeFragment

                    if (checkIconIfDarkModeOn()) {
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending_dark_mode)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category_dark_mode)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search_dark_mode)
                        homeItem?.setIcon(R.drawable.ic_nav_home_selected_dark_mode)
                        navigation.selectedItemId = R.id.navigation_home
                    } else {
                        navigation.menu.getItem(1).setIcon(R.drawable.ic_nav_trending)
                        navigation.menu.getItem(3).setIcon(R.drawable.ic_nav_category)
                        navigation.menu.getItem(4).setIcon(R.drawable.ic_nav_search)
                        homeItem?.setIcon(R.drawable.ic_nav_home_selected)
                        navigation.selectedItemId = R.id.navigation_home
                    }

                    isBack = true
                    homeCount++


                } else {
                    finishAffinity()
                    exitProcess(0)
                }

                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    public override fun onResume() {
        super.onResume()
    }

    public override fun onResumeFragments() {
        super.onResumeFragments()
        mIsStateAlreadySaved = false
        if (mPendingShowDialog) {
            mPendingShowDialog = false
            showBottomSheet()
        }
    }

    public override fun onPause() {
        super.onPause()
        mIsStateAlreadySaved = true
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode == 1) {
            finish()
            startActivity(intent)
        }
    }
}