package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.video.CategoryFeedVideoActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed.video.CategoryFeedVideoActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.CategoryFeedVideoAdapter
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.adapter.model.VideoAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_category_feed_video.*


class VideoActivityViewImpl : AppCompatActivity(),
    IVideoActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var categoryIdWithName: CategoryModel

    private lateinit var categoryFeedVideoActivityPresenterImpl: CategoryFeedVideoActivityPresenterImpl

    private var videoCategoryList: MutableList<HugeCardModel> = mutableListOf()
    private var arrOfVideoData: MutableList<VideoAdapterModel> = mutableListOf()

    private lateinit var filterParamDate: String
    private lateinit var filterParamSort: String

    var mainPage = 1

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var isScreenLoadMultiple: Boolean = false

    private var theme: String = ""

    private lateinit var mLayoutManager: PreCachingLayoutManager
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_feed_video)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        initVideoActivityComponent()

    }

    override fun initVideoActivityComponent() {
        intent.getStringExtra("ARTICLE_VIDEO_DATA")?.let {
            categoryIdWithName = Gson().fromJson(
                intent.getStringExtra("ARTICLE_VIDEO_DATA"),
                CategoryModel::class.java
            )

            categoryFeedVideoActivityPresenterImpl =
                CategoryFeedVideoActivityPresenterImpl(
                    CategoryFeedVideoActivityModelImpl(),
                    this
                )

            prepareToolbar()

            filterParamDate = ""
            filterParamSort = ""

            getVideoArticleData(
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort
            )

            clickDateSort()

            clickPopularitySort()

            showPopularitySortMenu()

            setRecyclerViewConfig()


        } ?: run {
            onBackPressed()
        }
    }

    private fun prepareToolbar() {
        toolBar = toolBar4CategoryVideo as Toolbar

        toolBar.title = "Video"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected_dark_mode
            )
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected_dark_mode
            )
            popularMenu.setImageResource(R.drawable.ic_popular_menu_2)

        } else {
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected
            )
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected
            )
            popularMenu.setImageResource(R.drawable.ic_popular_menu)
        }

    }

    override fun getVideoArticleData(
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String
    ) {
        categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
            false,
            categoryId,
            page,
            perPage,
            dateFilterType,
            sortType,
            true
        )
    }

    override fun handleVideoDataNew(response4ArticlesFeed: Response4ArticlesFeed) {
        paginationRecyclerView4CategoryFeedVideo.visibility = View.VISIBLE

        val videoData = response4ArticlesFeed.data?.feed!!

        if (arrOfVideoData.size == 0) {
            prepareVideoData(videoData)

            paginationRecyclerView4CategoryFeedVideo.adapter = CategoryFeedVideoAdapter(
                applicationContext,
                arrOfVideoData
            ) { itemOfVideoData, type ->

                JzvdStd.releaseAllVideos()

                handleClickViews(itemOfVideoData, type)
            }
        } else {
            arrOfVideoData.removeAt(arrOfVideoData.size - 1)
            paginationRecyclerView4CategoryFeedVideo.adapter?.notifyItemRemoved(arrOfVideoData.size)
            isLoading = false

            prepareVideoData(videoData)

            paginationRecyclerView4CategoryFeedVideo.adapter?.notifyDataSetChanged()
        }

        paginationRecyclerView4CategoryFeedVideo.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition = mLayoutManager.findLastVisibleItemPosition()
                val listSize = arrOfVideoData.size

                if (!isLoading && listSize == (lastPosition + 1)) {
                    mainPage++
                    infiniteScroll4VideoData(mLayoutManager)
                    isLoading = true
                }

            }
        })
    }

    private fun infiniteScroll4VideoData(layoutManager: PreCachingLayoutManager) {
        arrOfVideoData.add(
            VideoAdapterModel(
                "loading",
                "",
                0,
                "",
                "",
                "",
                "",
                "",
                " ",
                "",
                "",
                false,
                "",
                false,
                false
            )
        )

        paginationRecyclerView4CategoryFeedVideo.adapter?.notifyItemInserted(arrOfVideoData.size - 1)
        layoutManager.scrollToPosition(arrOfVideoData.size)
        categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
            false,
            categoryIdWithName.id,
            mainPage,
            15,
            filterParamDate,
            filterParamSort,
            false
        )
    }

    private fun prepareVideoData(videoData: MutableList<FeedArticleModel>): MutableList<VideoAdapterModel> {

        sortInfo4Comments.visibility = View.VISIBLE
        dateSort.visibility = View.VISIBLE
        popularitySort.visibility = View.VISIBLE

        if (arrOfVideoData.size == 0)
            arrOfVideoData.add(
                VideoAdapterModel(
                    "googleAdsTop",
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    " ",
                    "",
                    "",
                    false,
                    "",
                    false,
                    false
                )
            )


        for (i in 0 until videoData.size) {
            var categoryId: String? = null
            var categoryName: String? = null

            videoData[i].categories?.let {
                it[0].id?.let {
                    categoryId = it
                } ?: run {
                    categoryId = ""
                }

                it[0].name?.let {
                    categoryName = it
                } ?: run {
                    categoryName = ""
                }
            } ?: run {
                categoryId = ""
                categoryName = ""
            }

            var videoDataLink = ""
            var videoType = ""
            videoData[i].videoData?.let {
                it.video?.let {
                    videoDataLink = it
                }?: run{
                    videoDataLink = "youtube"
                }
                videoType = if (videoDataLink.contains("onedio", ignoreCase = true)) {
                    "onedio"
                } else
                    "other"
            } ?: run {
                videoDataLink = "youtube"
                videoType = "other"
            }

            var redirectUrl: String? = null
            videoData[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var viewTotal = ""
            videoData[i].stats?.let {
                it.viewsTotal?.let {
                    viewTotal = it.toString()
                } ?: run {
                    viewTotal = "0"
                }
            } ?: run {
                viewTotal = "0"
            }

            var articleId: String? = null
            videoData[i].id?.let {
                articleId = it
            } ?: run {
                articleId = ""
            }

            var legacyId: Long? = null
            videoData[i].legacyId?.let {
                legacyId = it
            } ?: run {
                legacyId = 0
            }

            var image: String? = null
            videoData[i].image?.let {
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

            var title: String? = null
            videoData[i].title?.let {
                title = it
            } ?: run {
                title = ""
            }

            var createDate: String? = null
            videoData[i].createDate?.let {
                createDate = it
            } ?: run {
                createDate = ""
            }

            var showInWebview: Boolean? = null
            videoData[i].showInWebview?.let {
                showInWebview = it
            } ?: run {
                showInWebview = false
            }

            var isHideCountLayout: Boolean = false
            videoData[i].tags?.let {
                if (it.size != 0) {
                    it.find { it == "~no-visitcount" }?.let {
                        isHideCountLayout = true
                    } ?: run {
                        isHideCountLayout = false
                    }
                } else {
                    isHideCountLayout = false
                }
            } ?: run {
                isHideCountLayout = false
            }

            arrOfVideoData.add(
                VideoAdapterModel(
                    videoType,
                    articleId!!,
                    legacyId!!,
                    image!!,
                    videoDataLink,
                    title!!,
                    " ",
                    viewTotal,
                    OnedioCommon.convertFeedDate(createDate!!),
                    categoryId!!,
                    categoryName!!,
                    showInWebview!!,
                    redirectUrl!!,
                    isHideCountLayout
                )
            )

            videoCategoryList.add(
                HugeCardModel(
                    articleId!!,
                    legacyId!!,
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
                    categoryName!!,
                    categoryId!!,
                    showInWebview!!,
                    redirectUrl!!
                )
            )

        }

        return arrOfVideoData

    }

    private fun handleClickViews(itemOfVideoData: VideoAdapterModel, type: String) {
        when {
            itemOfVideoData.redirectUrl != "" -> {
                val browserIntent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(itemOfVideoData.redirectUrl)
                    )
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)

                startAnim()
            }
            else -> {
                val sHugeCardObj =
                    SwipableArticleDetailObj(
                        videoCategoryList,
                        0
                    )
                val intent = Intent(
                    applicationContext,
                    SwipableArticleDetailActivityViewImpl::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra(
                    "SWIPE_ARTICLE_DATA",
                    Gson().toJson(sHugeCardObj)
                )
                intent.putExtra("LEGACY_ID", itemOfVideoData.legacyId)
                startActivity(intent)

                startAnim()
            }
        }
    }

    private fun clickDateSort() {
        dateSort.setOnClickListener {
            changeSortingButton2Date()

            videoCategoryList = mutableListOf()

            filterParamDate = ""
            filterParamSort = ""
            mainPage = 1

            paginationRecyclerView4CategoryFeedVideo.visibility = View.INVISIBLE

            arrOfVideoData = mutableListOf()
            videoCategoryList = mutableListOf()

            categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                20,
                filterParamDate,
                filterParamSort,
                true
            )
        }
    }

    private fun clickPopularitySort() {
        popularitySort.setOnClickListener {

            videoCategoryList = mutableListOf()

            if (dotLast24Hour.visibility == View.VISIBLE) {
                dotLast24Hour.visibility = View.VISIBLE
                dotLastWeek.visibility = View.INVISIBLE
                dotLastMonth.visibility = View.INVISIBLE
                dotAllTime.visibility = View.INVISIBLE

                popularitySortMenu.visibility = View.INVISIBLE

                paginationRecyclerView4CategoryFeedVideo.visibility = View.INVISIBLE

                filterParamDate = "popular"
                filterParamSort = "1day"
                mainPage = 1

                arrOfVideoData = mutableListOf()
                videoCategoryList = mutableListOf()

                categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
                    true,
                    categoryIdWithName.id,
                    mainPage,
                    20,
                    filterParamDate,
                    filterParamSort,
                    true
                )
            }


            changeSortingButton2Popularity()

        }
    }

    private fun changeSortingButton2Date() {
        infoPopularite.setTextColor(Color.parseColor("#777777"))

        if (theme == "dark") {
            popularMenu.setImageResource(R.drawable.ic_popular_menu_2)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected_dark_mode
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected_dark_mode
            )

        } else {
            popularMenu.setImageResource(R.drawable.ic_popular_menu)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected
            )
        }

        infoDate.setTextColor(Color.parseColor("#FFFFFF"))
        popularitySortMenu.visibility = View.INVISIBLE

    }

    private fun changeSortingButton2Popularity() {
        infoPopularite.setTextColor(Color.parseColor("#FFFFFF"))

        if (theme == "dark") {
            popularMenu.setImageResource(R.drawable.ic_popular_menu_2)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected_dark_mode
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected_dark_mode
            )

            popularitySortMenu.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.layout_border_dark_mode)
        } else {
            popularMenu.setImageResource(R.drawable.ic_popular_menu)
            popularitySort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_selected
            )

            dateSort.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.custom_button_sort_unselected
            )

            popularitySortMenu.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.layout_border)
        }

        infoDate.setTextColor(Color.parseColor("#777777"))
        popularitySortMenu.visibility = View.VISIBLE

    }

    private fun showPopularitySortMenu() {
        last24Hour.setOnClickListener {
            dotLast24Hour.visibility = View.VISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            videoCategoryList = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1day"
            mainPage = 1

            paginationRecyclerView4CategoryFeedVideo.visibility = View.INVISIBLE

            arrOfVideoData = mutableListOf()
            videoCategoryList = mutableListOf()

            categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                20,
                filterParamDate,
                filterParamSort,
                true
            )

        }

        lastWeek.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.VISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            videoCategoryList = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1week"
            mainPage = 1

            paginationRecyclerView4CategoryFeedVideo.visibility = View.INVISIBLE

            arrOfVideoData = mutableListOf()
            videoCategoryList = mutableListOf()

            categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                20,
                filterParamDate,
                filterParamSort,
                true
            )
        }

        lastMonth.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.VISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            videoCategoryList = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1month"
            mainPage = 1

            paginationRecyclerView4CategoryFeedVideo.visibility = View.INVISIBLE

            arrOfVideoData = mutableListOf()
            videoCategoryList = mutableListOf()

            categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                20,
                filterParamDate,
                filterParamSort,
                true
            )
        }

        allTime.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.VISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            videoCategoryList = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = ""
            mainPage = 1

            paginationRecyclerView4CategoryFeedVideo.visibility = View.INVISIBLE

            arrOfVideoData = mutableListOf()
            videoCategoryList = mutableListOf()

            categoryFeedVideoActivityPresenterImpl.getVideoArticleDataNew(
                true,
                categoryIdWithName.id,
                mainPage,
                20,
                filterParamDate,
                filterParamSort,
                true
            )
        }
    }

    private fun setRecyclerViewConfig() {
        mLayoutManager =
            PreCachingLayoutManager(
                applicationContext,
                RecyclerView.VERTICAL,
                false
            )

        mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(applicationContext))
        paginationRecyclerView4CategoryFeedVideo.layoutManager = mLayoutManager
        paginationRecyclerView4CategoryFeedVideo.setItemViewCacheSize(5)
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        categoryFeedVideoAvlIndicatorProgress.playAnimation()
        categoryFeedVideoAvlIndicatorProgress.visibility = View.VISIBLE
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading4List() {
        categoryFeedVideoAvlIndicatorProgress4List.playAnimation()
        categoryFeedVideoAvlIndicatorProgress4List.visibility = View.VISIBLE
    }

    //==================================================================================================================
    /**
     * Hide Loading
     */
    //==================================================================================================================
    override fun hideLoading() {
        categoryFeedVideoAvlIndicatorProgress.cancelAnimation()
        categoryFeedVideoAvlIndicatorProgress.visibility = View.GONE

    }

    //==================================================================================================================
    /**
     * Hide Loading
     */
    //==================================================================================================================
    override fun hideLoading4List() {
        categoryFeedVideoAvlIndicatorProgress4List.cancelAnimation()
        categoryFeedVideoAvlIndicatorProgress4List.visibility = View.GONE

    }

    //==================================================================================================================
    /**
     * Show Error...
     */
    //==================================================================================================================
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

    override fun showEzDialogMessage(
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

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) { onBackPressed() }
    }

    override fun onSupportNavigateUp(): Boolean {
        JzvdStd.releaseAllVideos()
        onBackPressed()
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            JzvdStd.releaseAllVideos()
            onBackPressed()
            false
        } else super.onKeyDown(keyCode, event)
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }

    override fun onPause() {
        JzvdStd.releaseAllVideos()
        super.onPause()
    }

    override fun onResume() {

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics.setCurrentScreen(this, "Video", "VideoActivityViewImpl")

        if (!isScreenLoadMultiple)
            sendFirebaseAnalyticsLogEvent()

        super.onResume()

    }

    private fun sendFirebaseAnalyticsLogEvent() {
        val params = Bundle()
        params.putString("name", "Video")
        params.putString("type", "Kategori Liste")
        params.putString("category1", "Video")

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = "Video"
        mapOfFeedAppMetrica["type"] = "Kategori Liste"
        mapOfFeedAppMetrica["category1"] = "Video"

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }
}