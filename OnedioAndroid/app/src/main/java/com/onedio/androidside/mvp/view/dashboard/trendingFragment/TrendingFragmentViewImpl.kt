package com.onedio.androidside.mvp.view.dashboard.trendingFragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
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
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.food.FoodActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.video.VideoActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.TrendingRecyclerAdapter
import com.onedio.androidside.mvp.view.dashboard.trendingFragment.adapter.model.TrendingAdapterModel
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.fragment_trending.view.*

class TrendingFragmentViewImpl : Fragment(),
    ITrendingFragmentActivityView {

    private var mActivity: Activity? = null
    private lateinit var viewP: View

    private var page = 1
    private var isFirstLoad = false

    private lateinit var trendingFragmentActivityPresenterImpl: TrendingFragmentActivityPresenterImpl

    private var arrOfTrendingData: MutableList<TrendingAdapterModel> = mutableListOf()
    private var arrOfTrendingDataWithHugeCard: MutableList<HugeCardModel> = mutableListOf()

    private var isLoading: Boolean = false

    private lateinit var mLayoutManager: PreCachingLayoutManager

    private lateinit var firebaseAnalytics: FirebaseAnalytics


    companion object {
        fun newInstance(): TrendingFragmentViewImpl {
            val fragmentHome =
                TrendingFragmentViewImpl()
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


        return viewP
    }

    override fun initTrendingFragmentComponent() {
        trendingFragmentActivityPresenterImpl =
            TrendingFragmentActivityPresenterImpl(
                TrendingFragmentActivityModelImpl(),
                this
            )

        getTrendData()

    }

    private fun getTrendData() {
        trendingFragmentActivityPresenterImpl.getTrendDataNew(page, 20, true)
    }

    override fun handleTrendDataNew(response4ArticlesFeed: Response4ArticlesFeed) {
        val trendingData = response4ArticlesFeed.data?.feed!!


        if (arrOfTrendingData.size == 0) {
            viewP.swipeRefreshTrending.isRefreshing = false
            mLayoutManager =
                PreCachingLayoutManager(
                    mActivity?.applicationContext!!,
                    RecyclerView.VERTICAL,
                    false
                )

            mLayoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(mActivity?.applicationContext!!))
            viewP.trendingRecyclerView.layoutManager = mLayoutManager
            viewP.trendingRecyclerView.setItemViewCacheSize(5)

            prepareTrendingData(trendingData)

            viewP.trendingRecyclerView.adapter = TrendingRecyclerAdapter(
                mActivity?.applicationContext!!,
                arrOfTrendingData
            ) { itemOfTrendingData, type ->
                handleClickViews(itemOfTrendingData, type)
            }
        } else {
            arrOfTrendingData.removeAt(arrOfTrendingData.size - 1)
            viewP.trendingRecyclerView.adapter?.notifyItemRemoved(arrOfTrendingData.size)
            isLoading = false

            prepareTrendingData(trendingData)

            viewP.trendingRecyclerView.adapter?.notifyDataSetChanged()
        }



        viewP.trendingRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition = mLayoutManager.findLastVisibleItemPosition()
                val listSize = arrOfTrendingData.size

                if (!isLoading && listSize == (lastPosition + 1)) {
                    page++
                    infiniteScroll4TrendingData(mLayoutManager)
                    isLoading = true
                }

            }
        })
    }

    private fun handleClickViews(itemOfTrendingData: TrendingAdapterModel, type: String) {
        when (type) {
            "COVER_PHOTO" -> {
                goArticle(itemOfTrendingData)
            }
            "CATEGORY" -> {
                goCategory(itemOfTrendingData)
            }
        }
    }

    private fun goArticle(itemOfTrendingData: TrendingAdapterModel) {
        val redirectUrl: String = itemOfTrendingData.redirectUrl
        when {
            redirectUrl != "" -> {
                val browserIntent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(redirectUrl)
                    )
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)
            }
            else -> {
                val sHugeCardObj =
                    SwipableArticleDetailObj(
                        arrOfTrendingDataWithHugeCard,
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
                intent.putExtra("LEGACY_ID", itemOfTrendingData.legacyId)
                startActivity(intent)
            }
        }

    }

    private fun goCategory(itemOfTrendingData: TrendingAdapterModel) {
        val categoryModel =
            CategoryModel(
                itemOfTrendingData.categoryId,
                null,
                itemOfTrendingData.categoryName
            )

        when (itemOfTrendingData.categoryId) {
            OnedioConstant.CATEGORIES_YEMEK_ID -> {
                val intent =
                    Intent(mActivity, FoodActivityViewImpl::class.java)
                intent.putExtra("ARTICLE_FOOD_DATA", Gson().toJson(categoryModel))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

                startAnim()
            }
            OnedioConstant.CATEGORIES_VIDEO_ID -> {
                val intent =
                    Intent(mActivity, VideoActivityViewImpl::class.java)
                intent.putExtra("ARTICLE_VIDEO_DATA", Gson().toJson(categoryModel))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

                startAnim()
            }
            else -> {
                val intent =
                    Intent(mActivity, CategoryFeedActivityViewImpl::class.java)
                intent.putExtra("ARTICLE_CATEGORY_DATA", Gson().toJson(categoryModel))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                startAnim()
            }
        }
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(mActivity!!, "l2r")
    }

    private fun infiniteScroll4TrendingData(layoutManager: PreCachingLayoutManager) {
        arrOfTrendingData.add(
            TrendingAdapterModel(
                "loading",
                "",
                0,
                "",
                "",
                "",
                "",
                "",
                false,
                "",
                false
            )
        )

        viewP.trendingRecyclerView.adapter?.notifyItemInserted(arrOfTrendingData.size - 1)
        layoutManager.scrollToPosition(arrOfTrendingData.size)
        trendingFragmentActivityPresenterImpl.getTrendDataNew(
            page,
            20,
            false
        )
    }

    private fun prepareTrendingData(
        trendingData: MutableList<FeedArticleModel>
    ): MutableList<TrendingAdapterModel> {

        if (arrOfTrendingData.size == 0)
            arrOfTrendingData.add(
                TrendingAdapterModel(
                    "googleAdsTop",
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    false,
                    "",
                    false
                )
            )

        for (i in 0 until trendingData.size) {
            var tArticleId: String? = null
            trendingData[i].id?.let {
                tArticleId = it
            } ?: run {
                tArticleId = ""
            }

            var tLegacyId: Long? = null
            trendingData[i].legacyId?.let {
                tLegacyId = it
            } ?: run {
                tLegacyId = 0
            }

            var tTitle: String? = null
            trendingData[i].title?.let {
                tTitle = it
            } ?: run {
                tTitle = ""
            }

            var tImage: String? = null
            trendingData[i].image?.let {
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
            trendingData[i].showInWebview?.let {
                tShowInWebView = it
            } ?: run {
                tShowInWebView = false
            }

            var redirectUrl: String? = null
            trendingData[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }


            var categoryUrl = ""
            var categoryId = ""
            var categoryName = ""

            if (trendingData[i].categories?.size != 0) {
                trendingData[i].categories?.let {
                    categoryUrl = trendingData[i].categories?.get(0)?.icons?.png!!
                    categoryId = trendingData[i].categories?.get(0)?.id!!
                    categoryName = trendingData[i].categories?.get(0)?.name!!
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

            var type = ""
            type = if (arrOfTrendingData.size == 1) {
                if (i == 0)
                    "header"
                else
                    "list"
            } else
                "list"


            arrOfTrendingData.add(
                TrendingAdapterModel(
                    type,
                    tArticleId!!,
                    tLegacyId!!,
                    tImage!!,
                    tTitle!!,
                    categoryId,
                    categoryName,
                    categoryUrl,
                    tShowInWebView!!,
                    redirectUrl!!

                )
            )

            arrOfTrendingDataWithHugeCard.add(
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


        return arrOfTrendingData


    }

    override fun showLoading() {
        viewP.trendingAvlIndicatorProgress.playAnimation()
        viewP.trendingAvlIndicatorProgress.visibility = View.VISIBLE

    }

    //==================================================================================================================
    /**
     * Hide Loading..
     */
    //==================================================================================================================
    override fun hideLoading() {
        viewP.trendingAvlIndicatorProgress.cancelAnimation()
        viewP.trendingAvlIndicatorProgress.visibility = View.GONE
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

    private fun swiperRefresh() {
        viewP.swipeRefreshTrending.setOnRefreshListener {

            isFirstLoad = false
            page = 1

            arrOfTrendingData = mutableListOf()

            initTrendingFragmentComponent()
        }
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

        sendFirebaseAnalyticsLogEvent()

        super.onResume()
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