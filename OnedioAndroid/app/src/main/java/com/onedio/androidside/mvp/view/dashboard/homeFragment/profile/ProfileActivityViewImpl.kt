package com.onedio.androidside.mvp.view.dashboard.homeFragment.profile

//import kotlinx.android.synthetic.main.fragment_profile.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.UserProfileActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.UserArticleFavoriteModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.presenter.dashboard.UserProfileInfoActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.adapter.UserProfileArticlesRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.adapter.UserProfileRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.profileFragment.IProfileFragmentActivityView
import com.onedio.androidside.mvp.view.dashboard.profileFragment.fragments.userFollowersAndFollowing.UserFollowerAndFollowingActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.GeneralSettingActivityViewImpl
import com.onedio.androidside.prefs.StringSharedPrefs
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.aaa_profile.*


class ProfileActivityViewImpl : AppCompatActivity(),
    IProfileFragmentActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var userProfileInfoActivityPresenterImpl: UserProfileInfoActivityPresenterImpl

    private lateinit var mAdapterUserFavorite: UserProfileRecyclerViewAdapter
    private lateinit var mAdapterUserArticle: UserProfileArticlesRecyclerViewAdapter

    private var arrOfUserFavoriteData: MutableList<UserArticleFavoriteModel>? = mutableListOf()
    private var arrOfUserArticleData: MutableList<FeedArticleModel>? = mutableListOf()

    private var isContinueInfiniteFavorite: Boolean = true
    private var pageFavorite = 1

    private var isContinueInfiniteArticle: Boolean = true
    private var pageArticle = 1

    private var isOwnUserProfile: Boolean = false
    private lateinit var anotheruserId: String

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var sharedPreferences: SharedPreferences

    private var theme: String = ""

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_profile)
        setContentView(R.layout.aaa_profile)

        sharedPref = getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        sharedPreferences = getSharedPreferences(
            OnedioConstant.SHARED_PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )

        isOwnUserProfile = intent.getBooleanExtra("IS_USER_OWN_PROFILE", false)
        anotheruserId = intent.getStringExtra("ANOTHER_USER_PROFILE")!!

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        prepareToolbar()

        initComponent()

        getProfileData()

        clickActivitiesButton()

        clickCommentButton()

        clickFavoritesButton()

        clickArticleButton()

        clickProfileSetting()

        clickUserFollowerCountOrText()

        clickUserFollowingCountOrText()

        /*changeIconIfDarkModeOn()clickUserFollowerCountOrText()

        clickUserFollowingCountOrText()

        clickStartMessageButton()

        clickUserBlockedUser()

        clickDoUserBlock()*/


    }

    private fun changeTheme(){

        if(theme == "dark"){
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.statusBarColor)
            profileConstraint.setBackgroundColor(Color.parseColor("#15212b"))
            toolBar4Profile.setBackgroundColor(Color.parseColor("#39444e"))
            toProfileSetting.setImageResource(R.drawable.ic_setting_dark_mode)
            profilePhotoUser.foreground = ContextCompat.getDrawable(applicationContext, R.drawable.custom_image_view_white_dark_mode)
            username.setTextColor(Color.parseColor("#FFFFFF"))
            userAboutInfo.setTextColor(Color.parseColor("#FFFFFF"))
            followingCount.setTextColor(Color.parseColor("#FFFFFF"))
            followingCountInfo.setTextColor(Color.parseColor("#FFFFFF"))
            followersCount.setTextColor(Color.parseColor("#FFFFFF"))
            followersCountInfo.setTextColor(Color.parseColor("#FFFFFF"))
            noFavoriteContentText.setTextColor(Color.parseColor("#FFFFFF"))
            noContentText.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark)
            profileConstraint.setBackgroundColor(Color.parseColor("#FFFFFF"))
            toolBar4Profile.setBackgroundColor(Color.parseColor("#F5F5F5"))
            toProfileSetting.setImageResource(R.drawable.ic_setting)
            profilePhotoUser.foreground = ContextCompat.getDrawable(applicationContext, R.drawable.custom_image_view_white)
            username.setTextColor(Color.parseColor("#191919"))
            userAboutInfo.setTextColor(Color.parseColor("#191919"))
            followingCount.setTextColor(Color.parseColor("#191919"))
            followingCountInfo.setTextColor(Color.parseColor("#191919"))
            followersCount.setTextColor(Color.parseColor("#191919"))
            followersCountInfo.setTextColor(Color.parseColor("#191919"))
            noFavoriteContentText.setTextColor(Color.parseColor("#191919"))
            noContentText.setTextColor(Color.parseColor("#191919"))

        }
    }

    private fun prepareToolbar() {
        toolBar = toolBar4Profile as Toolbar

        toolBar.title = "Profil"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        if(theme == "dark"){
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
        }else{
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
        }

        toolBar.setOnClickListener {

        }

        changeTheme()

    }

    //==================================================================================================================
    /**
     * Init Component...
     */
    //==================================================================================================================
    @SuppressLint("RestrictedApi")
    override fun initComponent() {
        //supportActionBar?.hide()

        userProfileInfoActivityPresenterImpl =
            UserProfileInfoActivityPresenterImpl(
                UserProfileActivityModelImpl(),
                this
            )

        changeComponentTextFont()
    }

    private fun getProfileData() {
        if (isOwnUserProfile) {
            userProfileInfoActivityPresenterImpl.getUserProfileData("me")

        } else {
            userProfileInfoActivityPresenterImpl.getUserProfileData(anotheruserId)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun handleUserProfileInfoData(
        response4UsersProfile: Response4UsersProfile,
        userId: String
    ) {

        if(userId == "me"){
            StringSharedPrefs(
                sharedPreferences,
                OnedioConstant.SHARED_PREF_USER_PROFILE_INFO,
                ""
            ).setValue(Gson().toJson(response4UsersProfile))
        }

        //OnedioSingletonPattern.instance.setUserProfileInfoData(Gson().toJson(response4UsersProfile))

        sendFirebaseAnalyticsLogEvent(response4UsersProfile)

        response4UsersProfile.data?.let {
            val userProfilData = response4UsersProfile.data
            profileConstraint.visibility = View.VISIBLE

            userProfilData.image?.let {
                Picasso.get().load(userProfilData.image.alternates?.standardResolution?.url)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(profilePhotoUser, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError(e: Exception?) {
                            profilePhotoUser.setImageResource(R.drawable.empty_avatar)
                        }
                    })
            } ?: run {

            }

            var userName = ""
            var userRealName = ""
            userProfilData.username?.let {
                userName = userProfilData.username
            } ?: run {
                userName = ""
            }

            userProfilData.name?.let {
                userRealName = " • " + userProfilData.name
            } ?: run {
                userRealName = ""
            }

            val builder = SpannableStringBuilder()
            builder.append(userName)
            val redSpannable = SpannableString(userRealName)
            redSpannable.setSpan(
                ForegroundColorSpan(Color.parseColor("#aaaaaa")),
                0,
                userRealName.length,
                0
            )
            builder.append(redSpannable)

            //var userFullName = userName + userRealName
            username.setText(builder, TextView.BufferType.SPANNABLE)

            userProfilData.title?.let {
                userAboutInfo.text = userProfilData.title
            } ?: run {
                userAboutInfo.text = ""
            }

            userProfilData.stats?.let {
                followingCount.text = userProfilData.stats.followers.toString()
                followersCount.text = userProfilData.stats.followings.toString()
            } ?: run {
                followingCount.text = ""
                followersCount.text = ""
            }
        } ?: run {
            showEzDialogMessage1(
                "Hata..!",
                "Kullanıcı bilgileri çekilirken bir hata oluştu..",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext!!,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext!!, R.color.constWhite),
                ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
                ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
            )
        }

        //getUserFavoriteArticles(userId)
        getUserArticleData(userId)

    }

    private fun getUserFavoriteArticles(userId: String) {
        userProfileInfoActivityPresenterImpl.getUserFavoriteArticles(userId)
    }

    override fun handleUserFavoriteArticle(
        response4UserFavoriteArticle: Response4UserFavoriteArticle,
        userId: String
    ) {
        settingRecyclerView()

        val arrOfTrendingData: MutableList<HugeCardModel> = mutableListOf()

        arrOfUserFavoriteData = response4UserFavoriteArticle.data

        /*if (arrOfUserFavoriteData?.size != 0) {
            userArticleRecyclerView.visibility = View.GONE
            userFavoriteRecyclerView.visibility = View.VISIBLE
            noFavoriteContentText.visibility = View.INVISIBLE
            noFavoriteContentImage.visibility = View.INVISIBLE
            noContentImage.visibility = View.INVISIBLE
            noContentText.visibility = View.INVISIBLE
        } else {
            userArticleRecyclerView.visibility = View.GONE
            userFavoriteRecyclerView.visibility = View.GONE
            noFavoriteContentText.visibility = View.VISIBLE
            noFavoriteContentImage.visibility = View.VISIBLE
            noContentImage.visibility = View.INVISIBLE
            noContentText.visibility = View.INVISIBLE
        }*/

        arrOfUserFavoriteData?.forEach {
            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""


            it.categories?.let {
                if (it.size != 0) {
                    categoryUrl = it[0].icons?.png!!
                    categoryId = it[0].id!!
                    categoryName = it[0].name!!
                } else {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } ?: run {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }

            var redirectUrl: String? = null
            it.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var showInWebView: Boolean? = null
            it.showInWebview?.let {
                showInWebView = it
            }?: run{
                showInWebView = true
            }

            var image: String? = null
            it.image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            image = it
                        }?: run{
                            image = ""
                        }
                    }?: run{
                        image = ""
                    }
                }?: run{
                    image = ""
                }
            }?: run{
                image = ""
            }

            /*if (it.categories?.size != 0) {
                it.categories?.let { it1 ->
                    categoryUrl = it1[0].icons?.png!!
                    categoryId = it1[0].id!!
                    categoryName = it1[0].name!!
                } ?: run {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } else {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }*/


            arrOfTrendingData.add(
                HugeCardModel(
                    it.id!!,
                    it.legacyId!!,
                    image,
                    categoryUrl,
                    null,
                    it.title!!,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    true,
                    categoryName,
                    categoryId,
                    showInWebView!!,
                    redirectUrl!!
                )
            )
        }

        mAdapterUserFavorite =
            UserProfileRecyclerViewAdapter(
                applicationContext,
                arrOfTrendingData
            ) { itemOfHugeCardModel, _ ->
                val articleItem =
                    HugeCardModel(
                        itemOfHugeCardModel.articleId,
                        itemOfHugeCardModel.legacyId,
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
                        true,
                        itemOfHugeCardModel.categoryName,
                        itemOfHugeCardModel.categoryId,
                        itemOfHugeCardModel.showInWebView,
                        itemOfHugeCardModel.redirectUrl
                    )
                /*OnedioSingletonPattern.instance.setArticleItem(
                    articleItem
                )*/

                OnedioSingletonPattern.instance.setActivity(ProfileActivityViewImpl::class.java)

                when {
                    itemOfHugeCardModel.redirectUrl != "" -> {
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(itemOfHugeCardModel.redirectUrl))
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(browserIntent)
                    }
                    itemOfHugeCardModel.showInWebView -> {
                        val intent =
                            Intent(
                                applicationContext,
                                TestArticleDetailActivityViewImpl::class.java
                            )
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else -> {
                        /*val intent =
                            Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)*/

                        val intent =
                            Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    }
                }
                /*OnedioCommon.nStartActivity(
                    applicationContext,
                    TestArticleDetailActivityViewImpl::class.java
                ) else
                OnedioCommon.nStartActivity(
                    applicationContext,
                    ArticleDetailActivityViewImpl::class.java
                )*/
                startAnim()
            }

        userFavoriteRecyclerView.adapter = mAdapterUserFavorite

        profileUserDataNestedScrollView.setOnScrollChangeListener(object :
            NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                var aa = v?.getChildAt(0)?.measuredHeight
                var bbb = v?.measuredHeight

                aa?.let {
                    if (scrollY == (aa - bbb!!)) {
                        if (isContinueInfiniteFavorite && userFavoriteRecyclerView.visibility == View.VISIBLE) {
                            pageFavorite++
                            getUserFavoriteArticlesInfinite(userId, pageFavorite)
                        }
                        if (isContinueInfiniteArticle && userArticleRecyclerView.visibility == View.VISIBLE) {
                            pageArticle++
                            getUserArticlesInfinite(userId, pageArticle)
                        }
                    }
                } ?: run {

                }
            }
        })

        //getUserArticleData(userId)

    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }

    private fun getUserArticleData(userId: String) {
        userProfileInfoActivityPresenterImpl.getUserArticleData(userId)
    }

    override fun handleUserArticlesData(response4UserArticle: Response4UserArticle, userId: String) {
        val arrOffTrendingData: MutableList<HugeCardModel> = mutableListOf()

        arrOfUserArticleData = response4UserArticle.data
        isContinueInfiniteFavorite = arrOfUserArticleData?.size != 0

        if (arrOfUserArticleData?.size != 0) {
            userArticleRecyclerView.visibility = View.VISIBLE
            userFavoriteRecyclerView.visibility = View.GONE
            noFavoriteContentText.visibility = View.INVISIBLE
            noFavoriteContentImage.visibility = View.INVISIBLE
            noContentImage.visibility = View.INVISIBLE
            noContentText.visibility = View.INVISIBLE
        } else {
            userArticleRecyclerView.visibility = View.GONE
            userFavoriteRecyclerView.visibility = View.GONE
            noFavoriteContentText.visibility = View.INVISIBLE
            noFavoriteContentImage.visibility = View.INVISIBLE
            noContentImage.visibility = View.VISIBLE
            noContentText.visibility = View.VISIBLE
        }

        response4UserArticle.data?.forEach {

            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""


            it.categories?.let {
                if (it.size != 0) {
                    categoryUrl = it[0].icons?.png!!
                    categoryId = it[0].id!!
                    categoryName = it[0].name!!
                } else {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } ?: run {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }

            var redirectUrl: String? = null
            it.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }


            /*if (it.categories?.size != 0) {
                it.categories?.let { it1 ->
                    categoryUrl = it1[0].icons?.png!!
                    categoryId = it1[0].id!!
                    categoryName = it1[0].name!!
                } ?: run {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } else {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }*/


            arrOffTrendingData.add(
                HugeCardModel(
                    it.id!!,
                    it.legacyId!!,
                    it.image?.alternates?.standardResolution?.url,
                    categoryUrl,
                    null,
                    it.title!!,
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
                    categoryName,
                    categoryId,
                    it.showInWebview!!,
                    redirectUrl!!
                )
            )
        }

        mAdapterUserArticle =
            UserProfileArticlesRecyclerViewAdapter(
                applicationContext,
                arrOffTrendingData
            ) { itemOfHugeCardModel, _ ->
                val articleItem =
                    HugeCardModel(
                        itemOfHugeCardModel.articleId,
                        itemOfHugeCardModel.legacyId,
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
                        itemOfHugeCardModel.categoryName,
                        itemOfHugeCardModel.categoryId,
                        itemOfHugeCardModel.showInWebView,
                        itemOfHugeCardModel.redirectUrl
                    )
                /*OnedioSingletonPattern.instance.setArticleItem(
                    articleItem
                )*/

                OnedioSingletonPattern.instance.setActivity(ProfileActivityViewImpl::class.java)

                when {
                    itemOfHugeCardModel.redirectUrl != "" -> {
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(itemOfHugeCardModel.redirectUrl))
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(browserIntent)
                    }
                    itemOfHugeCardModel.showInWebView -> {
                        val intent =
                            Intent(
                                applicationContext,
                                TestArticleDetailActivityViewImpl::class.java
                            )
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                    else -> {
                        val intent =
                            Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
                /*OnedioCommon.nStartActivity(
                    applicationContext,
                    TestArticleDetailActivityViewImpl::class.java
                ) else
                OnedioCommon.nStartActivity(
                    applicationContext,
                    ArticleDetailActivityViewImpl::class.java
                )*/

                startAnim()
            }

        userArticleRecyclerView.adapter = mAdapterUserArticle

        getUserFavoriteArticles(userId)
    }

    private fun getUserFavoriteArticlesInfinite(userId: String, page: Int) {
        userProfileInfoActivityPresenterImpl.getUserFavoriteArticlesInfinite(userId, page)
    }

    override fun handleUserFavoriteArticleInfinite(
        response4UserFavoriteArticle: Response4UserFavoriteArticle,
        userId: String
    ) {

        isContinueInfiniteFavorite = response4UserFavoriteArticle.data?.size != 0
        val arrOfUserFavorite: MutableList<HugeCardModel> = mutableListOf()

        response4UserFavoriteArticle.data?.forEach {
            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""

            it.categories?.let {
                if (it.size != 0) {
                    categoryUrl = it[0].icons?.png!!
                    categoryId = it[0].id!!
                    categoryName = it[0].name!!
                } else {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } ?: run {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }

            var redirectUrl: String? = null
            it.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            /*if (it.categories?.size != 0) {
                it.categories?.let { it1 ->
                    categoryUrl = it1[0].icons?.png!!
                    categoryId = it1[0].id!!
                    categoryName = it1[0].name!!
                } ?: run {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } else {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }*/





            arrOfUserFavorite.add(
                HugeCardModel(
                    it.id!!,
                    it.legacyId!!,
                    it.image?.alternates?.standardResolution?.url,
                    categoryUrl,
                    null,
                    it.title!!,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    true,
                    categoryName,
                    categoryId,
                    it.showInWebview!!,
                    redirectUrl!!
                )
            )
        }

        mAdapterUserFavorite.addItems(arrOfUserFavorite)

    }

    private fun getUserArticlesInfinite(userId: String, page: Int) {
        userProfileInfoActivityPresenterImpl.getUserArticlesInfinite(userId, page)
    }

    override fun handleUserArticlesDataInfinite(response4UserArticle: Response4UserArticle) {
        isContinueInfiniteArticle = response4UserArticle.data?.size != 0
        val arrOfUserArticle: MutableList<HugeCardModel> = mutableListOf()

        response4UserArticle.data?.forEach {

            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""

            it.categories?.let {
                if (it.size != 0) {
                    categoryUrl = it[0].icons?.png!!
                    categoryId = it[0].id!!
                    categoryName = it[0].name!!
                } else {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } ?: run {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }

            /*if (it.categories?.size != 0) {
                it.categories?.let { it1 ->
                    categoryUrl = it1[0].icons?.png!!
                    categoryId = it1[0].id!!
                    categoryName = it1[0].name!!
                } ?: run {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } else {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }*/

            var redirectUrl: String? = null
            it.redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }


            arrOfUserArticle.add(
                HugeCardModel(
                    it.id!!,
                    it.legacyId!!,
                    it.image?.alternates?.standardResolution?.url,
                    categoryUrl,
                    null,
                    it.title!!,
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
                    categoryName,
                    categoryId,
                    it.showInWebview!!,
                    redirectUrl!!
                )
            )
        }

        mAdapterUserArticle.addItems(arrOfUserArticle)
    }

    private fun clickActivitiesButton() {
        /*activities.setOnClickListener {
            activities.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_blue_radius_top
            )
            activities.setTextColor(Color.parseColor("#FFFFFF"))

            userFavoriteRecyclerView.visibility = View.INVISIBLE
            userArticleRecyclerView.visibility = View.INVISIBLE

            comment.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            comment.setTextColor(Color.parseColor("#191919"))

            favorites.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            favorites.setTextColor(Color.parseColor("#191919"))

            articles.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            articles.setTextColor(Color.parseColor("#191919"))
        }*/
    }

    private fun clickCommentButton() {
        /*comment.setOnClickListener {
            comment.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_blue_radius_top
            )
            comment.setTextColor(Color.parseColor("#FFFFFF"))

            userFavoriteRecyclerView.visibility = View.INVISIBLE
            userArticleRecyclerView.visibility = View.INVISIBLE

            activities.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            activities.setTextColor(Color.parseColor("#191919"))

            favorites.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            favorites.setTextColor(Color.parseColor("#191919"))

            articles.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            articles.setTextColor(Color.parseColor("#191919"))
        }*/
    }

    private fun clickFavoritesButton() {
        favorites.setOnClickListener {

            if (arrOfUserFavoriteData?.size != 0) {
                userArticleRecyclerView.visibility = View.GONE
                userFavoriteRecyclerView.visibility = View.VISIBLE
                noFavoriteContentText.visibility = View.INVISIBLE
                noFavoriteContentImage.visibility = View.INVISIBLE
                noContentImage.visibility = View.INVISIBLE
                noContentText.visibility = View.INVISIBLE
            } else {
                userArticleRecyclerView.visibility = View.GONE
                userFavoriteRecyclerView.visibility = View.GONE
                noFavoriteContentText.visibility = View.VISIBLE
                noFavoriteContentImage.visibility = View.VISIBLE
                noContentImage.visibility = View.INVISIBLE
                noContentText.visibility = View.INVISIBLE
            }


            /*favorites.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_blue_radius_top
            )*/
            favorites.setBackgroundResource(R.drawable.custom_button_blue_radius_top);
            favorites.setTextColor(Color.parseColor("#FFFFFF"))

            /*comment.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            comment.setTextColor(Color.parseColor("#191919"))

            activities.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            activities.setTextColor(Color.parseColor("#191919"))*/

            if(theme == "dark"){
                articles.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray_dark_mode)
                articles.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                articles.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
                articles.setTextColor(Color.parseColor("#191919"))
            }


        }
    }

    private fun clickArticleButton() {
        articles.setOnClickListener {

            if (arrOfUserArticleData?.size != 0) {
                userArticleRecyclerView.visibility = View.VISIBLE
                userFavoriteRecyclerView.visibility = View.GONE
                noFavoriteContentText.visibility = View.INVISIBLE
                noFavoriteContentImage.visibility = View.INVISIBLE
                noContentImage.visibility = View.INVISIBLE
                noContentText.visibility = View.INVISIBLE
            } else {
                userArticleRecyclerView.visibility = View.GONE
                userFavoriteRecyclerView.visibility = View.GONE
                noFavoriteContentText.visibility = View.INVISIBLE
                noFavoriteContentImage.visibility = View.INVISIBLE
                noContentImage.visibility = View.VISIBLE
                noContentText.visibility = View.VISIBLE
            }

            /*articles.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_blue_radius_top
            )*/

            articles.setBackgroundResource(R.drawable.custom_button_blue_radius_top);
            articles.setTextColor(Color.parseColor("#FFFFFF"))


            /*comment.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            comment.setTextColor(Color.parseColor("#191919"))*/

            if(theme == "dark"){
                favorites.background = ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray_dark_mode)
                favorites.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                favorites.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
                favorites.setTextColor(Color.parseColor("#191919"))
            }


            /*activities.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_button_gray)
            activities.setTextColor(Color.parseColor("#191919"))*/
        }
    }


    //==================================================================================================================
    /**
     * Change Component Text Font...
     */
    //==================================================================================================================
    override fun changeComponentTextFont() {
        /*userNameAndSurname.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        userBio.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        userFollowed.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtUserFollowed.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        userFollower.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtOfFollower.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        blockUseInfoName.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        blockedUserInfo.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)*/
    }

    //==================================================================================================================
    /**
     * Change Icon if dark mode on...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        /*if (OnedioSharedPrefs(applicationContext!!).isNightModeEnabled()) {
            toProfileSetting.setImageResource(R.drawable.ic_setting_dark_mode)
            fabMessage.setImageDrawable(resources.getDrawable(R.drawable.ic_fab_envolepe_dark_mode))
        } else {
            toProfileSetting.setImageResource(R.drawable.ic_setting)
            fabMessage.setImageDrawable(resources.getDrawable(R.drawable.ic_fab_envolepe))
        }*/
    }

    //==================================================================================================================
    /**
     * Click Profile Setting...
     */
    //==================================================================================================================
    override fun clickProfileSetting() {
        if (!isOwnUserProfile) {
            toProfileSetting.visibility = View.INVISIBLE
        } else {
            toProfileSetting.visibility = View.VISIBLE
            toProfileSetting.setOnClickListener {
                /*OnedioSingletonPattern.instance.setTabIndex(4)*/

                startActivity(
                    Intent(
                        applicationContext,
                        GeneralSettingActivityViewImpl::class.java
                    )
                )
            }
        }
    }

    private fun settingRecyclerView() {
        userFavoriteRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(applicationContext)
        userFavoriteRecyclerView.layoutManager = layoutManager

        userArticleRecyclerView.setHasFixedSize(true)
        val layoutManager1 = LinearLayoutManager(applicationContext)
        userArticleRecyclerView.layoutManager = layoutManager1

        /*val dividerItemDecoration = DividerItemDecoration(
            viewP.trendingRecyclerView.context,
            layoutManager.orientation
        )*/
        //viewP.trendingRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    //==================================================================================================================
    /**
     * Click User Follower Count or Text...
     */
    //==================================================================================================================
    override fun clickUserFollowerCountOrText() {

        followersCount.setOnClickListener {
            OnedioSingletonPattern.instance.setIndex(1)
            if (isOwnUserProfile) {
                userProfileInfoActivityPresenterImpl.getUserFollowers("me", 1)
            } else {
                userProfileInfoActivityPresenterImpl.getUserFollowers(
                    anotheruserId,
                    1
                )
            }
        }

        followersCountInfo.setOnClickListener {
            OnedioSingletonPattern.instance.setIndex(1)
            if (isOwnUserProfile) {
                userProfileInfoActivityPresenterImpl.getUserFollowers("me", 1)
            } else {
                userProfileInfoActivityPresenterImpl.getUserFollowers(
                    anotheruserId,
                    1
                )
            }
        }
    }

    override fun handleUserFollowersData(response4UserFollowerAndFollowing: Response4UserFollowerAndFollowing) {

        OnedioSingletonPattern.instance.setUserFollowersData(response4UserFollowerAndFollowing)

        if (isOwnUserProfile) {
            userProfileInfoActivityPresenterImpl.getUserFollowings("me", 1)
        } else {
            userProfileInfoActivityPresenterImpl.getUserFollowings(
                anotheruserId,
                1
            )
        }

    }

    override fun handleUserFollowingsData(response4UserFollowerAndFollowing: Response4UserFollowerAndFollowing) {

        OnedioSingletonPattern.instance.setUserFollowingsData(response4UserFollowerAndFollowing)
        val intent =
            Intent(applicationContext, UserFollowerAndFollowingActivityViewImpl::class.java)
        intent.putExtra("IS_USER_OWN_PROFILE", isOwnUserProfile)
        intent.putExtra("ANOTHER_USER_PROFILE", anotheruserId)
        startActivity(intent)
        /*startActivity(
            Intent(
                applicationContext,
                UserFollowerAndFollowingActivityViewImpl::class.java
            )
        )*/

    }

    //==================================================================================================================
    /**
     * Click User Following Count or Text...
     */
    //==================================================================================================================
    override fun clickUserFollowingCountOrText() {
        followingCount.setOnClickListener {
            OnedioSingletonPattern.instance.setIndex(0)
            if (isOwnUserProfile) {
                userProfileInfoActivityPresenterImpl.getUserFollowers("me", 1)
            } else {
                userProfileInfoActivityPresenterImpl.getUserFollowers(
                    anotheruserId,
                    1
                )
            }
        }

        followingCountInfo.setOnClickListener {
            OnedioSingletonPattern.instance.setIndex(0)
            if (isOwnUserProfile) {
                userProfileInfoActivityPresenterImpl.getUserFollowers("me", 1)
            } else {
                userProfileInfoActivityPresenterImpl.getUserFollowers(
                    anotheruserId,
                    1
                )
            }
        }
    }

    //==================================================================================================================
    /**
     * Click User Blocked User...
     */
    //==================================================================================================================
    override fun clickUserBlockedUser() {
        /*userBio.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    UserBlockListActivityViewImpl::class.java
                )
            )
        }*/
    }

    //==================================================================================================================
    /**
     * Click Do User Block...
     */
    //==================================================================================================================
    override fun clickDoUserBlock() {
        /*blockedUser.setOnClickListener {
            //startActivity(Intent(activity?.applicationContext, UserDoBlockActivityViewImpl::class.java))
            userProfileInfoActivityPresenterImpl.doBlockUser()
        }*/
    }

    //==================================================================================================================
    /**
     * Click Start Message Button...
     */
    //==================================================================================================================
    override fun clickStartMessageButton() {
        /*fabMessage.setOnClickListener {
            OnedioSingletonPattern.instance.setConversationStart(false)
            OnedioSingletonPattern.instance.setUserNameAndSurname(userNameAndSurname.text.toString())
            startActivity(
                Intent(
                    applicationContext,
                    MessageActivityConversationViewImpl::class.java
                )
            )
        }*/
    }

    override fun fillUserProfileInfo() {
        /*fragmentProfileConstraint.visibility = View.VISIBLE

        //val response4UserProfileInfo = Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UserProfileInfo::class.java)
        val response4UserProfileInfo = Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UsersProfile::class.java)

        OnedioSingletonPattern.instance.setUserId(response4UserProfileInfo?.data?.id!!)

        userNameAndSurname.text =
            response4UserProfileInfo.data.username
        userBio.text = "Hakkında kısmı gelicek"
        userFollower.text =
            response4UserProfileInfo.data.stats?.followers.toString()
        userFollowed.text =
            response4UserProfileInfo.data.stats?.followings.toString()

        *//*if (response4UserProfileInfo.valOfUserProfileInfoData?.avatar!!.take(1) != "/")
            Picasso.get().load(response4UserProfileInfo.valOfUserProfileInfoData?.avatar!!)
                .into(profilePic)*//*

        Picasso.get().load(response4UserProfileInfo.data.image?.alternates?.highResolution?.url)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(profilePic, object : Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {

                }
            })



        profileActivityViewPagerAdapter =
            ProfileActivityViewPagerAdapter(
                supportFragmentManager
            )
        mainViewPager.adapter = profileActivityViewPagerAdapter
        tabs.setupWithViewPager(mainViewPager)
        tabs.tabRippleColor = null*/


    }

    //==================================================================================================================
    /**
     * Is Another User is Block?...
     */
    //==================================================================================================================
    override fun isAnotherUserBlock() {
        userProfileInfoActivityPresenterImpl.getUserBlockedList()
    }

    //==================================================================================================================
    /**
     * Get Another User Profile Info...
     */
    //==================================================================================================================
    override fun getAnotherUserProfile() {
        userProfileInfoActivityPresenterImpl.getAnotherUserProfile()
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        avlIndicatorProgressProfile.playAnimation()
        avlIndicatorProgressProfile.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Hide Loading...
     */
    //==================================================================================================================
    override fun hideLoading() {
        avlIndicatorProgressProfile.cancelAnimation()
        avlIndicatorProgressProfile.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //==================================================================================================================
    /**
     * Handle Another User Profile Info Data...
     */
    //==================================================================================================================
    override fun handleAnotherUserProfileInfoData(response4UserProfileInfo: Response4UserProfileInfo) {
        /*fragmentProfileConstraint.visibility = View.VISIBLE

        if (response4UserProfileInfo.status?.code == 200) {
            userNameAndSurname.text =
                response4UserProfileInfo.valOfUserProfileInfoData?.username
            userBio.text = response4UserProfileInfo.valOfUserProfileInfoData?.title
            userFollower.text =
                response4UserProfileInfo.valOfUserProfileInfoData?.followerCount.toString()
            userFollowed.text =
                response4UserProfileInfo.valOfUserProfileInfoData?.followingCount.toString()

            if (!response4UserProfileInfo.valOfUserProfileInfoData?.avatar!!.take(1).equals("/")) {
                Picasso.get().load(response4UserProfileInfo.valOfUserProfileInfoData?.avatar!!)
                    .into(profilePic)

                //// User image URL...
                OnedioSingletonPattern.instance.setConversationUserImageURL(response4UserProfileInfo.valOfUserProfileInfoData?.avatar!!)
            } else {
                OnedioSingletonPattern.instance.setConversationUserImageURL("emptyAvatar")
            }


            profileActivityViewPagerAdapter =
                ProfileActivityViewPagerAdapter(
                    supportFragmentManager
                )
            mainViewPager.adapter = profileActivityViewPagerAdapter
            tabs.setupWithViewPager(mainViewPager)
            tabs.tabRippleColor = null

        } else {
            EZDialog.Builder(this)
                .setTitle("Hata..!")
                .setMessage("Daha sonra tekrar deneyiniz..")
                .setPositiveBtnText("Tamam")
                .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
                .setTitleTextColor(resources.getColor(R.color.constWhite))
                .setMessageTextColor(resources.getColor(R.color.constTextColor))
                .setButtonTextColor(resources.getColor(R.color.constTextColor))
                .setCancelableOnTouchOutside(true)
                .OnPositiveClicked {
                    startActivity(
                        Intent(
                            applicationContext,
                            LoginAndRegisterDashboardActivityViewImpl::class.java
                        )
                    )
                }
                .build()
        }*/
    }

    //==================================================================================================================
    /**
     * Handle Another User Block Info Data...
     */
    //==================================================================================================================
    @SuppressLint("SetTextI18n")
    override fun handleAnotherUserBlockInfoData(response4DoBlockedUser: Response4DoBlockedUser) {
        /*fragmentProfileConstraint.visibility = View.VISIBLE

        if (response4DoBlockedUser.status?.code == 200) {
            if (response4DoBlockedUser.isBlocked!!) {

                blockUseInfoName.text =
                    OnedioSingletonPattern.instance.getAnotherUserName() + " kullanıcısı engellenmiş!"
                blockAnotherUserProperty()

                //startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))
            } else
                EZDialog.Builder(this)
                    .setTitle("Hata..!")
                    .setMessage("Engelleme sırasında bir sorun oluştur.")
                    .setPositiveBtnText("Tamam")
                    .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
                    .setTitleTextColor(resources.getColor(R.color.constWhite))
                    .setMessageTextColor(resources.getColor(R.color.constTextColor))
                    .setButtonTextColor(resources.getColor(R.color.constTextColor))
                    .setCancelableOnTouchOutside(false)
                    .OnPositiveClicked {

                    }
                    .build()
        } else
            EZDialog.Builder(this)
                .setTitle("Hata..!")
                .setMessage("Şu an da bir sorun var. Daha sonra tekrar deneyiniz.")
                .setPositiveBtnText("Tamam")
                .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
                .setTitleTextColor(resources.getColor(R.color.constWhite))
                .setMessageTextColor(resources.getColor(R.color.constTextColor))
                .setButtonTextColor(resources.getColor(R.color.constTextColor))
                .setCancelableOnTouchOutside(false)
                .OnPositiveClicked {

                }
                .build()*/
    }

    //==================================================================================================================
    /**
     * Handle User Blocked List...
     */
    //==================================================================================================================
    @SuppressLint("SetTextI18n")
    override fun handleUserBlockedList(resposne4UserBlockedList: Response4UserBlockedList) {
        /*for (itemOfBlockedUser in resposne4UserBlockedList.data!!) {
            if (itemOfBlockedUser.username.equals(OnedioSingletonPattern.instance.getAnotherUserName())) {
                blockUseInfoName.text =
                    itemOfBlockedUser.username + " kullanıcısı engellenmiş!"
                blockAnotherUserProperty()
            }
        }

        getAnotherUserProfile()*/
    }

    //==================================================================================================================
    /**
     * Block Another User Property...
     */
    //==================================================================================================================
    @SuppressLint("RestrictedApi", "SetTextI18n")
    override fun blockAnotherUserProperty() {
        /*fabMessage.visibility = View.INVISIBLE
        followButton.visibility = View.INVISIBLE
        tabs.visibility = View.INVISIBLE
        mainViewPager.visibility = View.INVISIBLE
        viewLine.visibility = View.INVISIBLE

        blockedUser.setBackgroundResource(R.drawable.custom_button_gray)
        blockedUser.setTextColor(resources.getColor(R.color.whiteColor))
        blockedUser.text = "Engellendi"

        blockedUserViewLine.visibility = View.VISIBLE
        blockedFligram.visibility = View.VISIBLE
        blockUseInfoName.visibility = View.VISIBLE
        blockedUserInfo.visibility = View.VISIBLE*/

    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Uyarı..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                applicationContext!!,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext!!, R.color.constWhite),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
        )
    }

    private fun showEzDialogMessage(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        val ezDialogMessage =
            OnedioEzDialogMessageModel4Activity(
                this,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) {}
    }

    private fun showEzDialogMessage1(
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        val ezDialogMessage =
            OnedioEzDialogMessageModel4Activity(
                this,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) {
            onBackPressed()
        }
    }

    public override fun onResume() {
        sharedPref = getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        articles.performClick()
        articles.isPressed = true
        articles.invalidate()
        getProfileData()

        prepareToolbar()

        super.onResume()
    }

    private fun sendFirebaseAnalyticsLogEvent(response4UserProfile: Response4UsersProfile) {

        var userName: String? = null
        response4UserProfile.data?.let {
            it.username?.let {
                userName = it
            }
        }

        val params = Bundle()
        params.putString("name", "$userName -  Onedio")
        params.putString("type", "Kullanıcı Profili")

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = "$userName -  Onedio"
        mapOfFeedAppMetrica["type"] = "Kullanıcı Profili"

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }

    override fun forNow() {
        /*if(arrOfUserArticleData?.size != 0){
            userFavoriteRecyclerView.visibility = View.VISIBLE
            noContentText.visibility = View.INVISIBLE
            noContentImage.visibility = View.INVISIBLE
        }else{
            userFavoriteRecyclerView.visibility = View.INVISIBLE
            noContentText.visibility = View.VISIBLE
            noContentImage.visibility = View.VISIBLE
        }*/
    }

}