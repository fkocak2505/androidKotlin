package com.onedio.androidside.mvp.view.dashboard.trendingFragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Fragment
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.dashboard.trendingFragment.TrendingFragmentActivityModelImpl
import com.onedio.androidside.mvp.presenter.dashboard.trendingFragment.TrendingFragmentActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.FoodActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.VideoActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.TrendingRecyclerViewAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.fragment_trending.view.*


class TrendingFragmentActivityViewImpl : Fragment(),
    ITrendingFragmentActivityView {

    private var mActivity: Activity? = null

    private lateinit var viewP: View

    private lateinit var trendingFragmentActivityPresenterImpl: TrendingFragmentActivityPresenterImpl

    private var trendingArticleList: MutableList<HugeCardModel> = mutableListOf()

    private lateinit var mAdapter: TrendingRecyclerViewAdapter

    private var page = 1

    private var isFirstLoad = false

    private var isContinueInfinite = true

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var isScreenLoadMultiple: Boolean = false

    companion object {
        fun newInstance(): TrendingFragmentActivityViewImpl {
            val fragmentHome =
                TrendingFragmentActivityViewImpl()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewP = inflater.inflate(R.layout.fragment_trending, container, false)

        mActivity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )

        swiperRefresh()

        initTrendingFragmentComponent()

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        //changeDarkMode()

        return viewP
    }

    private fun swiperRefresh() {
        viewP.swipeRefreshTrending.setOnRefreshListener {

            isFirstLoad = false
            page = 1

            initTrendingFragmentComponent()

            loadMastheadAd()
        }
    }

    override fun initTrendingFragmentComponent() {

        /*firebaseAnalytics = FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)
        firebaseAnalytics.setCurrentScreen(
            mActivity!!,
            "Trending",
            "TrendingFragmentActivityViewImpl"
        )*/

        trendingFragmentActivityPresenterImpl =
            TrendingFragmentActivityPresenterImpl(
                TrendingFragmentActivityModelImpl(),
                this
            )


        getTrendData()

        setProfilePhoto()
    }

    private fun getTrendData() {
        trendingFragmentActivityPresenterImpl.getTrendDataNew(page, 20, true)
    }

    override fun handleTrendDataNew(response4ArticlesFeed: Response4ArticlesFeed) {
        val articles = response4ArticlesFeed.data?.feed!!
        viewP.swipeRefreshTrending.isRefreshing = false

        isContinueInfinite = articles.size != 0

        if (!isFirstLoad) {
            /*trendingArticleList = addAllTrendingData(articles)
            setMostPopularTrendData(articles[0], trendingArticleList)*/
            //setMostPopularTrendData(articles[0])
        }
        prepareMostPopularRecyclerViewData(articles)

    }

    /*override fun handleTrendData(response4FeedModel: Response4FeedModel) {
        val articles = response4FeedModel.dataOfFeedModel?.articles!!


        if (!isFirstLoad)
            setMostPopularTrendData(articles[0])
        prepareMostPopularRecyclerViewData(articles)

    }*/

    /*private fun setMostPopularTrendData(
        mostPopularTrendingItem: FeedArticleModel
    ) {
        loadMostTrendingCoverPhoto(mostPopularTrendingItem)

        viewP.articleAction.visibility = View.VISIBLE

        var icons: String? = null
        var categoryName: String? = null
        var categoryId: String? = null

        mostPopularTrendingItem.categories?.let {
            if (it.size != 0) {
                it[0].icons?.let {
                    it.png?.let {
                        icons = it

                        viewP.articleAction.visibility = View.VISIBLE
                        viewP.articleActionBackground.visibility = View.VISIBLE
                    } ?: run {
                        icons = ""

                        viewP.articleAction.visibility = View.GONE
                        viewP.articleActionBackground.visibility = View.GONE
                    }
                } ?: run {
                    icons = ""

                    viewP.articleAction.visibility = View.GONE
                    viewP.articleActionBackground.visibility = View.GONE
                }

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
            } else {
                icons = ""
                categoryId = ""
                categoryName = ""

                viewP.articleAction.visibility = View.GONE
                viewP.articleActionBackground.visibility = View.GONE
            }
        } ?: run {
            icons = ""
            categoryId = ""
            categoryName = ""

            viewP.articleAction.visibility = View.GONE
            viewP.articleActionBackground.visibility = View.GONE

        }

        if (viewP.articleAction.visibility == View.VISIBLE)
            loadImageWithoutProgress(
                icons!!,
                viewP.articleAction
            )

        viewP.trendingTitle.visibility = View.VISIBLE
        viewP.trendingTitle.text = mostPopularTrendingItem.title

        viewP.view5.visibility = View.VISIBLE
        viewP.view3.visibility = View.VISIBLE

        viewP.trendingTitle.setOnClickListener {

            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
            } else {

                var redirectUrl: String? = null
                mostPopularTrendingItem.redirectUrl?.let {
                    redirectUrl = it
                } ?: run {
                    redirectUrl = ""
                }

                var articleId: String? = null
                mostPopularTrendingItem.id?.let {
                    articleId = it
                } ?: run {
                    articleId = ""
                }

                var legacyId: Long? = null
                mostPopularTrendingItem.legacyId?.let {
                    legacyId = it
                } ?: run {
                    legacyId = 0
                }

                var showInWebView: Boolean? = null
                mostPopularTrendingItem.showInWebview?.let {
                    showInWebView = it
                } ?: run {
                    showInWebView = false
                }

                val articleItem =
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
                        null,
                        null,
                        showInWebView!!,
                        redirectUrl!!
                    )

                *//*trendingArticleList.add(0, articleItem)
                trendingArticleList = trendingArticleList.distinct().toMutableList()*//*

                OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)

                when {
                    redirectUrl != "" -> {
                        val browserIntent =
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(mostPopularTrendingItem.redirectUrl)
                            )
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(browserIntent)
                    }
                    else -> {
                        val sHugeCardObj =
                            SwipableArticleDetailObj(
                                trendingArticleList,
                                0
                            )
                        val intent = Intent(
                            mActivity,
                            SwipableArticleDetailActivityViewImpl::class.java
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra(
                            "SWIPE_ARTICLE_DATA",
                            Gson().toJson(sHugeCardObj)
                        )
                        intent.putExtra("LEGACY_ID", legacyId!!)
                        startActivity(intent)
                    }
                }
                startAnim()
            }
        }


        viewP.articleAction.setOnClickListener {
            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
            } else {
                val categoryModel =
                    CategoryModel(
                        categoryId!!,
                        null,
                        categoryName!!
                    )
                when (categoryId) {
                    OnedioConstant.CATEGORIES_YEMEK_ID -> {
                        *//*OnedioSingletonPattern.instance.setCategoryId(categoryModel)*//*
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )
                        *//*OnedioCommon.cStartActivity(
                            activity?.applicationContext!!,
                            FoodActivityViewImpl::class.java
                        )*//*

                        val intent =
                            Intent(mActivity, FoodActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_FOOD_DATA", Gson().toJson(categoryModel))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        startAnim()
                    }
                    OnedioConstant.CATEGORIES_VIDEO_ID -> {
                        //OnedioSingletonPattern.instance.setCategoryId(categoryModel)
                        *//*OnedioSingletonPattern.instance.setTabIndex(3)*//*
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )
                        *//*OnedioCommon.cStartActivity(
                            activity?.applicationContext!!,
                            VideoActivityViewImpl::class.java
                        )*//*

                        val intent =
                            Intent(mActivity, VideoActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_VIDEO_DATA", Gson().toJson(categoryModel))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        startAnim()
                    }
                    else -> {
                        //OnedioSingletonPattern.instance.setCategoryId(categoryModel)
                        OnedioSingletonPattern.instance.setActivity(
                            DashboardActivityViewImpl::class.java
                        )

                        val intent =
                            Intent(mActivity, CategoryFeedActivityViewImpl::class.java)
                        intent.putExtra("ARTICLE_CATEGORY_DATA", Gson().toJson(categoryModel))
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                        *//*OnedioCommon.cStartActivity(
                            activity?.applicationContext!!,
                            CategoryFeedActivityViewImpl::class.java
                        )*//*
                        startAnim()
                    }
                }
            }
        }
    }*/

    /*private fun loadMostTrendingCoverPhoto(
        mostPopularTrendingItem: FeedArticleModel
    ) {
        val coverPhoto = mostPopularTrendingItem.image?.alternates?.standardResolution?.url
        if (coverPhoto?.contains("gif", true)!!)
            loadGifWithGlide(
                mActivity?.applicationContext!!,
                coverPhoto,
                viewP.trendingCoverPhoto
            )
        else
            loadImageWithoutProgress(coverPhoto, viewP.trendingCoverPhoto)

        *//*var categoryId = ""
        var categoryName = ""

        mostPopularTrendingItem.categories?.let {
            categoryId = mostPopularTrendingItem.categories[0].id!!
            categoryName = mostPopularTrendingItem.categories[0].name!!
        } ?: run {
            categoryId = ""
            categoryName = ""
        }*//*

        viewP.trendingCoverPhoto.setOnClickListener {
            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
            } else {

                var redirectUrl: String? = null
                mostPopularTrendingItem.redirectUrl?.let {
                    redirectUrl = it
                } ?: run {
                    redirectUrl = ""
                }

                var articleId: String? = null
                mostPopularTrendingItem.id?.let {
                    articleId = it
                } ?: run {
                    articleId = ""
                }

                var legacyId: Long? = null
                mostPopularTrendingItem.legacyId?.let {
                    legacyId = it
                } ?: run {
                    legacyId = 0
                }

                var showInWebView: Boolean? = null
                mostPopularTrendingItem.showInWebview?.let {
                    showInWebView = it
                } ?: run {
                    showInWebView = false
                }


                val articleItem =
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
                        null,
                        null,
                        showInWebView!!,
                        redirectUrl!!
                    )

                *//*trendingArticleList.add(0, articleItem)
                trendingArticleList = trendingArticleList.distinct().toMutableList()*//*

                OnedioSingletonPattern.instance.setActivity(DashboardActivityViewImpl::class.java)

                when {
                    redirectUrl != "" -> {
                        val browserIntent =
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(mostPopularTrendingItem.redirectUrl)
                            )
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(browserIntent)
                    }
                    else -> {
                        val sHugeCardObj =
                            SwipableArticleDetailObj(
                                trendingArticleList,
                                0
                            )
                        val intent = Intent(
                            mActivity,
                            SwipableArticleDetailActivityViewImpl::class.java
                        )
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra(
                            "SWIPE_ARTICLE_DATA",
                            Gson().toJson(sHugeCardObj)
                        )
                        intent.putExtra("LEGACY_ID", legacyId!!)
                        startActivity(intent)
                    }
                }
                startAnim()
            }
        }
    }*/

    private fun loadImageWithoutProgress(
        imageUrl: String,
        imageView: ImageView
    ) {
        if (imageUrl.trim() != "") {
            Picasso.get().load(imageUrl)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        imageView.visibility = View.VISIBLE
                    }

                    override fun onError(e: Exception?) {
                        imageView.visibility = View.VISIBLE
                        imageView.setImageResource(R.drawable.image_error_dark_mode)
                    }
                })
        } else {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.image_error_dark_mode)
        }
    }

    private fun loadGifWithGlide(
        context: Context,
        gifUrl: String,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(gifUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    imageView.setImageResource(R.drawable.image_error_dark_mode)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    return false
                }
            })
            .into(imageView)
    }

    private fun prepareMostPopularRecyclerViewData(articles: MutableList<FeedArticleModel>) {
        viewP.trendingRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mActivity)
        viewP.trendingRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            viewP.trendingRecyclerView.context,
            layoutManager.orientation
        )
        viewP.trendingRecyclerView.addItemDecoration(dividerItemDecoration)

        //setTrendingDataAdapter(addAllTrendingData(articles))

    }

    /*private fun setTrendingDataAdapter(trendingData: MutableList<HugeCardModel>) {

        if (!isFirstLoad) {
            mAdapter =
                TrendingRecyclerViewAdapter(
                    mActivity?.applicationContext!!,
                    trendingData
                ) { itemOfTrendingData, index, type ->

                    if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                        OnedioSingletonPattern.instance.getProfileSettingPopup()
                            ?.visibility = View.GONE
                    } else {
                        when (type) {
                            "FEED" -> {
                                OnedioSingletonPattern.instance.setActivity(
                                    DashboardActivityViewImpl::class.java
                                )

                                when {
                                    itemOfTrendingData.redirectUrl != "" -> {
                                        val browserIntent =
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(itemOfTrendingData.redirectUrl)
                                            )
                                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(browserIntent)
                                    }
                                    else -> {
                                        val sHugeCardObj =
                                            SwipableArticleDetailObj(
                                                trendingArticleList,
                                                (index + 1)
                                            )
                                        val intent = Intent(
                                            mActivity,
                                            SwipableArticleDetailActivityViewImpl::class.java
                                        )
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        intent.putExtra(
                                            "SWIPE_ARTICLE_DATA",
                                            Gson().toJson(sHugeCardObj)
                                        )
                                        intent.putExtra("LEGACY_ID", itemOfTrendingData.legacyId)
                                        startActivity(intent)
                                    }
                                }
                                startAnim()
                            }
                            "CATEGORY" -> {
                                val categoryModel =
                                    CategoryModel(
                                        itemOfTrendingData.categoryId!!,
                                        null,
                                        itemOfTrendingData.categoryName!!
                                    )
                                when (itemOfTrendingData.categoryId) {
                                    OnedioConstant.CATEGORIES_YEMEK_ID -> {
                                        *//*OnedioSingletonPattern.instance.setCategoryId(
                                            categoryModel
                                        )*//*
                                        OnedioSingletonPattern.instance.setActivity(
                                            DashboardActivityViewImpl::class.java
                                        )

                                        val intent =
                                            Intent(mActivity, FoodActivityViewImpl::class.java)
                                        intent.putExtra(
                                            "ARTICLE_FOOD_DATA",
                                            Gson().toJson(categoryModel)
                                        )
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)

                                        *//*OnedioCommon.cStartActivity(
                                            activity?.applicationContext!!,
                                            FoodActivityViewImpl::class.java
                                        )*//*
                                        startAnim()
                                    }
                                    OnedioConstant.CATEGORIES_VIDEO_ID -> {
                                        *//*OnedioSingletonPattern.instance.setCategoryId(
                                            categoryModel
                                        )*//*
                                        *//*OnedioSingletonPattern.instance.setTabIndex(
                                            3
                                        )*//*
                                        OnedioSingletonPattern.instance.setActivity(
                                            DashboardActivityViewImpl::class.java
                                        )

                                        val intent =
                                            Intent(mActivity, VideoActivityViewImpl::class.java)
                                        intent.putExtra(
                                            "ARTICLE_VIDEO_DATA",
                                            Gson().toJson(categoryModel)
                                        )
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)

                                        *//*OnedioCommon.cStartActivity(
                                            activity?.applicationContext!!,
                                            VideoActivityViewImpl::class.java
                                        )*//*
                                        startAnim()
                                    }
                                    else -> {
                                        *//*OnedioSingletonPattern.instance.setCategoryId(
                                            categoryModel
                                        )*//*
                                        OnedioSingletonPattern.instance.setActivity(
                                            DashboardActivityViewImpl::class.java
                                        )

                                        val intent =
                                            Intent(
                                                mActivity,
                                                CategoryFeedActivityViewImpl::class.java
                                            )
                                        intent.putExtra(
                                            "ARTICLE_CATEGORY_DATA",
                                            Gson().toJson(categoryModel)
                                        )
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)

                                        *//*OnedioCommon.cStartActivity(
                                            activity?.applicationContext!!,
                                            CategoryFeedActivityViewImpl::class.java
                                        )*//*
                                        startAnim()
                                    }
                                }
                            }
                        }
                    }
                }
            isFirstLoad = true
            viewP.trendingRecyclerView.adapter = mAdapter
        } else
            mAdapter.addItems(trendingData)


        viewP.trendingNestedScrollView.setOnScrollChangeListener(object :
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
                        if (isContinueInfinite) {
                            trendingFragmentActivityPresenterImpl.getTrendDataNew(
                                page,
                                20
                            )
                        }
                    }
                } ?: run {

                }
            }
        })


    }*/

    private fun addAllTrendingData(articles: MutableList<FeedArticleModel>): MutableList<HugeCardModel> {
        val arrOfTrendingData: MutableList<HugeCardModel> = mutableListOf()
        var startCount = 1

        prepareData(trendingArticleList, 0, articles)

        if (isFirstLoad) {
            startCount = 0
        }

        prepareData(arrOfTrendingData, startCount, articles)

        /*for (i in startCount until articles.size) {

            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""

            if (articles[i].categories?.size != 0) {
                articles[i].categories?.let {
                    categoryUrl = articles[i].categories?.get(0)?.icons?.png!!
                    categoryId = articles[i].categories?.get(0)?.id!!
                    categoryName = articles[i].categories?.get(0)?.name!!
                } ?: run {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } else {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }

            var redirectUrl: String? = null
            articles[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var tArticleId: String? = null
            articles[i].id?.let {
                tArticleId = it
            } ?: run {
                tArticleId = ""
            }

            var tLegacyId: Long? = null
            articles[i].legacyId?.let {
                tLegacyId = it
            } ?: run {
                tLegacyId = 0
            }

            var tTitle: String? = null
            articles[i].title?.let {
                tTitle = it
            } ?: run {
                tTitle = ""
            }

            var tImage: String? = null
            articles[i].image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            tImage = it
                        } ?: run {
                            tImage = ""
                        }
                    } ?: run {
                        tImage = ""
                    }
                } ?: run {
                    tImage = ""
                }
            } ?: run {
                tImage = ""
            }

            var tShowInWebView: Boolean? = null
            articles[i].showInWebview?.let {
                tShowInWebView = it
            } ?: run {
                tShowInWebView = false
            }


            arrOfTrendingData.add(
                HugeCardModel(
                    tArticleId!!,
                    tLegacyId!!,
                    tImage,
                    categoryUrl,
                    null,
                    tTitle!!,
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
                    tShowInWebView!!,
                    redirectUrl!!
                )
            )

            trendingArticleList.add(
                HugeCardModel(
                    tArticleId!!,
                    tLegacyId!!,
                    tImage,
                    categoryUrl,
                    null,
                    tTitle!!,
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
                    tShowInWebView!!,
                    redirectUrl!!
                )
            )
        }*/

        page++

        trendingArticleList = trendingArticleList.distinct().toMutableList()

        return arrOfTrendingData

    }

    private fun prepareData(
        arrData: MutableList<HugeCardModel>,
        startCount: Int,
        articles: MutableList<FeedArticleModel>
    ) {
        for (i in startCount until articles.size) {

            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""

            if (articles[i].categories?.size != 0) {
                articles[i].categories?.let {
                    categoryUrl = articles[i].categories?.get(0)?.icons?.png!!
                    categoryId = articles[i].categories?.get(0)?.id!!
                    categoryName = articles[i].categories?.get(0)?.name!!
                } ?: run {
                    categoryUrl = ""
                    categoryId = ""
                    categoryName = ""
                }
            } else {
                categoryUrl = ""
                categoryId = ""
                categoryName = ""
            }

            var redirectUrl: String? = null
            articles[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var tArticleId: String? = null
            articles[i].id?.let {
                tArticleId = it
            } ?: run {
                tArticleId = ""
            }

            var tLegacyId: Long? = null
            articles[i].legacyId?.let {
                tLegacyId = it
            } ?: run {
                tLegacyId = 0
            }

            var tTitle: String? = null
            articles[i].title?.let {
                tTitle = it
            } ?: run {
                tTitle = ""
            }

            var tImage: String? = null
            articles[i].image?.let {
                it.alternates?.let {
                    it.standardResolution?.let {
                        it.url?.let {
                            tImage = it
                        } ?: run {
                            tImage = ""
                        }
                    } ?: run {
                        tImage = ""
                    }
                } ?: run {
                    tImage = ""
                }
            } ?: run {
                tImage = ""
            }

            var tShowInWebView: Boolean? = null
            articles[i].showInWebview?.let {
                tShowInWebView = it
            } ?: run {
                tShowInWebView = false
            }


            arrData.add(
                HugeCardModel(
                    tArticleId!!,
                    tLegacyId!!,
                    tImage,
                    categoryUrl,
                    null,
                    tTitle!!,
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
                    tShowInWebView!!,
                    redirectUrl!!
                )
            )
        }
    }

    private fun setProfilePhoto() {
        /*if (OnedioCommon.isUserLogin()) {
            if (Gson().fromJson(
                    OnedioSingletonPattern.instance.getUserProfileInfoData(),
                    Response4UserProfileInfo::class.java
                ).valOfUserProfileInfoData?.avatar!!.take(
                    1
                ) != "/"
            )
                Picasso.get().load(
                    Gson().fromJson(
                        OnedioSingletonPattern.instance.getUserProfileInfoData(),
                        Response4UserProfileInfo::class.java
                    ).valOfUserProfileInfoData?.avatar!!
                )
                    .into(viewP.profilePhoto, object : Callback {
                        override fun onSuccess() {
                            viewP.profilePhotoProgress.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {

                        }
                    })
        } else
            viewP.profilePhotoProgress.visibility = View.GONE*/
    }

    //==================================================================================================================
    /**
     * Show Loading..
     */
    //==================================================================================================================
    override fun showLoading() {
        viewP.trendingAvlIndicatorProgress.playAnimation()
        viewP.trendingAvlIndicatorProgress.visibility = View.VISIBLE
        /*mActivity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Hide Loading..
     */
    //==================================================================================================================
    override fun hideLoading() {
        viewP.trendingAvlIndicatorProgress.cancelAnimation()
        viewP.trendingAvlIndicatorProgress.visibility = View.GONE
        //mActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    //==================================================================================================================
    /**
     * Show Error..
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            "Uyarı..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                mActivity?.applicationContext!!,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(mActivity?.applicationContext!!, R.color.constWhite),
            ContextCompat.getColor(mActivity?.applicationContext!!, R.color.constTextColor),
            ContextCompat.getColor(mActivity?.applicationContext!!, R.color.constTextColor)
        )
    }

    //==================================================================================================================
    /**
     * Show EzDialog Message..
     */
    //==================================================================================================================
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
            OnedioEzDialogMessageModel4Fragment(
                mActivity!!,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Fragment(ezDialogMessage) {}
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "l2r")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            mActivity = context
        }
    }

    override fun onResume() {

        firebaseAnalytics = FirebaseAnalytics.getInstance(mActivity?.applicationContext!!)
        firebaseAnalytics.setCurrentScreen(
            mActivity!!,
            "Trending",
            "TrendingFragmentActivityViewImpl"
        )

        if (!isScreenLoadMultiple)
            loadMastheadAd()

        super.onResume()
    }

    /*private fun changeDarkMode(){

        val sharedPref = mActivity?.applicationContext!!.getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString("mode", "default")

        if (theme == "dark") {
            viewP.trendingTitle.setTextColor(Color.parseColor("#FFFFFF"))
            viewP.view3.setBackgroundColor(Color.parseColor("#0e1720"))
            viewP.view5.setBackgroundColor(Color.parseColor("#0e1720"))

            viewP.articleActionBackground.setImageResource(R.drawable.icon_circle_dark_mode)

        }else{
            viewP.trendingTitle.setTextColor(Color.parseColor("#231f20"))
            viewP.view3.setBackgroundColor(Color.parseColor("#e2e2e2"))
            viewP.view5.setBackgroundColor(Color.parseColor("#e2e2e2"))

            viewP.articleActionBackground.setImageResource(R.drawable.ic_circle)
        }
    }*/

    private fun loadMastheadAd() {

        sendFirebaseAnalyticsLogEvent()

        //OnedioCommon.loadMastheadAd(viewP.publisherAdViewTrendingTop, "TRND_TOP")

    }

    private fun sendFirebaseAnalyticsLogEvent() {
        val params = Bundle()
        params.putString("name", "Trending")
        params.putString("type", "Trending")

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = "Trending"
        mapOfFeedAppMetrica["type"] = "Trending"

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }

}