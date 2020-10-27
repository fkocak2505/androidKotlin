package com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
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
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryFeed.CategoryFeedActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.categoryFragment.categoryModel.CategoryModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.presenter.dashboard.categotyFragment.categoryFeed.CategoryFeedActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.CategoryFeedRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.categoryFragment.categoryFeed.adapter.model.CategoryAdapterModel
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_category_feed.*


class CategoryFeedActivityViewImpl : AppCompatActivity(),
    ICategoryFeedActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var categoryIdWithName: CategoryModel

    private lateinit var categoryFeedActivityPresenterImpl: CategoryFeedActivityPresenterImpl

    private var categoryFeedList: MutableList<HugeCardModel> = mutableListOf()
    private var arrOfCategoryData: MutableList<CategoryAdapterModel> = mutableListOf()

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
        setContentView(R.layout.activity_category_feed)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        initCategoryFeedComponent()

    }

    override fun initCategoryFeedComponent() {

        //categoryIdWithName = OnedioSingletonPattern.instance.getCategoryId()
        intent.getStringExtra("ARTICLE_CATEGORY_DATA")?.let {
            categoryIdWithName = Gson().fromJson(
                intent.getStringExtra("ARTICLE_CATEGORY_DATA"),
                CategoryModel::class.java
            )

            prepareToolbar()

            //openVisibility4Toolbar()

            categoryFeedActivityPresenterImpl =
                CategoryFeedActivityPresenterImpl(
                    CategoryFeedActivityModelImpl(),
                    this
                )

            filterParamDate = ""
            filterParamSort = ""


            getArticleWithCategoryId(
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
        toolBar = toolBar4CategoryFeed as Toolbar

        toolBar.title = categoryIdWithName.title
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

    override fun getArticleWithCategoryId(
        categoryId: String,
        page: Int,
        perPage: Int,
        dateFilterType: String,
        sortType: String
    ) {
        categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
            false,
            categoryId,
            page,
            perPage,
            dateFilterType,
            sortType,
            true
        )
    }

    override fun handleArticleFeedDataByCategoryIdNew(response4ArticlesFeed: Response4ArticlesFeed) {
        paginationRecyclerView4CategoryDetail.visibility = View.VISIBLE

        val categoryData = response4ArticlesFeed.data?.feed!!

        if (arrOfCategoryData.size == 0) {
            prepareCategoryData(categoryData)

            paginationRecyclerView4CategoryDetail.adapter = CategoryFeedRecyclerViewAdapter(
                applicationContext,
                arrOfCategoryData
            ) { itemOfVideoData, type ->

                JzvdStd.releaseAllVideos()

                handleClickViews(itemOfVideoData, type)
            }
        } else {
            arrOfCategoryData.removeAt(arrOfCategoryData.size - 1)
            paginationRecyclerView4CategoryDetail.adapter?.notifyItemRemoved(arrOfCategoryData.size)
            isLoading = false

            prepareCategoryData(categoryData)

            paginationRecyclerView4CategoryDetail.adapter?.notifyDataSetChanged()
        }

        paginationRecyclerView4CategoryDetail.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition = mLayoutManager.findLastVisibleItemPosition()
                val listSize = arrOfCategoryData.size

                if (!isLoading && listSize == (lastPosition + 1)) {
                    mainPage++
                    infiniteScroll4CategoryData(mLayoutManager)
                    isLoading = true
                }

            }
        })
    }

    private fun infiniteScroll4CategoryData(layoutManager: PreCachingLayoutManager) {
        arrOfCategoryData.add(
            CategoryAdapterModel(
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
                false,
                "",
                false,
                false
            )
        )

        paginationRecyclerView4CategoryDetail.adapter?.notifyItemInserted(arrOfCategoryData.size - 1)
        layoutManager.scrollToPosition(arrOfCategoryData.size)
        categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
            false,
            categoryIdWithName.id,
            mainPage,
            15,
            filterParamDate,
            filterParamSort,
            false
        )
    }

    private fun prepareCategoryData(categoryData: MutableList<FeedArticleModel>): MutableList<CategoryAdapterModel> {
        sortInfo4Comments.visibility = View.VISIBLE
        dateSort.visibility = View.VISIBLE
        popularitySort.visibility = View.VISIBLE

        if (arrOfCategoryData.size == 0)
            arrOfCategoryData.add(
                CategoryAdapterModel(
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
                    false,
                    "",
                    false,
                    false
                )
            )

        for (i in 0 until categoryData.size) {
            var articleId: String? = null
            categoryData[i].id?.let {
                articleId = it
            } ?: run {
                articleId = ""
            }

            var legacyId: Long? = null
            categoryData[i].legacyId?.let {
                legacyId = it
            } ?: run {
                legacyId = 0
            }

            var categoryId: String? = null
            var categoryName: String? = null

            categoryData[i].categories?.let {
                if (categoryData[i].categories?.size != 0) {

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
                    categoryId = ""
                    categoryName = ""
                }

            } ?: run {
                categoryId = ""
                categoryName = ""
            }

            var image = ""
            categoryData[i].image?.let {
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

            var title = ""
            categoryData[i].title?.let {
                title = it
            } ?: run {
                title = ""
            }

            var description = ""
            categoryData[i].description?.let {
                description = it
            } ?: run {
                description = ""
            }

            var viewTotal = ""
            categoryData[i].stats?.let {
                it.viewsTotal?.let {
                    viewTotal = it.toString()
                } ?: run {
                    viewTotal = "0"
                }
            } ?: run {
                viewTotal = "0"
            }

            var createDate: String? = null
            categoryData[i].createDate?.let {
                createDate = it
            } ?: run {
                createDate = "2020-05-05T16:08:18.349Z"
            }

            var showInWebView: Boolean? = null
            categoryData[i].showInWebview?.let {
                showInWebView = it
            } ?: run {
                showInWebView = false
            }

            var redirectUrl: String? = null
            categoryData[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var isHideCountLayout: Boolean = false
            categoryData[i].tags?.let {
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

            arrOfCategoryData.add(
                CategoryAdapterModel(
                    "article",
                    articleId!!,
                    legacyId!!,
                    image,
                    title,
                    description,
                    viewTotal,
                    OnedioCommon.convertFeedDate(createDate!!),
                    categoryId!!,
                    categoryName!!,
                    showInWebView!!,
                    redirectUrl!!,
                    isHideCountLayout
                )
            )

            categoryFeedList.add(
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
                    showInWebView!!,
                    redirectUrl!!
                )
            )
        }

        return arrOfCategoryData
    }

    private fun handleClickViews(itemOfVideoData: CategoryAdapterModel, type: String) {
        when {
            itemOfVideoData.redirectUrl != "" -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(itemOfVideoData.redirectUrl))
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)

                startAnim()
            }
            else -> {
                val sHugeCardObj =
                    SwipableArticleDetailObj(
                        categoryFeedList,
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

            categoryFeedList = mutableListOf()
            arrOfCategoryData = mutableListOf()

            filterParamDate = ""
            filterParamSort = ""
            mainPage = 1

            paginationRecyclerView4CategoryDetail.visibility = View.INVISIBLE

            categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
                filterParamDate,
                filterParamSort,
                true
            )
        }
    }

    private fun clickPopularitySort() {
        popularitySort.setOnClickListener {

            categoryFeedList = mutableListOf()
            arrOfCategoryData = mutableListOf()

            if (dotLast24Hour.visibility == View.VISIBLE) {
                dotLast24Hour.visibility = View.VISIBLE
                dotLastWeek.visibility = View.INVISIBLE
                dotLastMonth.visibility = View.INVISIBLE
                dotAllTime.visibility = View.INVISIBLE

                popularitySortMenu.visibility = View.INVISIBLE

                paginationRecyclerView4CategoryDetail.visibility = View.INVISIBLE

                filterParamDate = "popular"
                filterParamSort = "1day"
                mainPage = 1

                categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
                    true,
                    categoryIdWithName.id,
                    mainPage,
                    15,
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

            categoryFeedList = mutableListOf()
            arrOfCategoryData = mutableListOf()


            filterParamDate = "popular"
            filterParamSort = "1day"
            mainPage = 1

            paginationRecyclerView4CategoryDetail.visibility = View.INVISIBLE

            categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
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

            categoryFeedList = mutableListOf()
            arrOfCategoryData = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1week"
            mainPage = 1

            paginationRecyclerView4CategoryDetail.visibility = View.INVISIBLE

            categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
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

            arrOfCategoryData = mutableListOf()
            categoryFeedList = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1month"
            mainPage = 1

            paginationRecyclerView4CategoryDetail.visibility = View.INVISIBLE

            categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
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

            categoryFeedList = mutableListOf()
            arrOfCategoryData = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = ""
            mainPage = 1

            paginationRecyclerView4CategoryDetail.visibility = View.INVISIBLE

            categoryFeedActivityPresenterImpl.getArticleFeedByCategoryIdNew(
                true,
                categoryIdWithName.id,
                mainPage,
                15,
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
        paginationRecyclerView4CategoryDetail.layoutManager = mLayoutManager
        paginationRecyclerView4CategoryDetail.setItemViewCacheSize(5)

    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        categoryFeedAvlIndicatorProgress.playAnimation()
        categoryFeedAvlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading4List() {
        categoryFeedVideoAvlIndicatorProgress4List.playAnimation()
        categoryFeedVideoAvlIndicatorProgress4List.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Hide Loading
     */
    //==================================================================================================================
    override fun hideLoading() {
        categoryFeedAvlIndicatorProgress.cancelAnimation()
        categoryFeedAvlIndicatorProgress.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }

    //==================================================================================================================
    /**
     * Hide Loading
     */
    //==================================================================================================================
    override fun hideLoading4List() {
        categoryFeedVideoAvlIndicatorProgress4List.cancelAnimation()
        categoryFeedVideoAvlIndicatorProgress4List.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }

    override fun onResume() {

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics.setCurrentScreen(
            this,
            categoryIdWithName.title,
            "CategoryFeedActivityViewImpl"
        )

        if (!isScreenLoadMultiple)
            sendFirebaseAnalyticsLogEvent()

        super.onResume()
    }

    private fun sendFirebaseAnalyticsLogEvent() {
        val params = Bundle()
        params.putString("name", categoryIdWithName.title)
        params.putString("type", "Kategori Liste")
        params.putString("category1", categoryIdWithName.title)

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = categoryIdWithName.title
        mapOfFeedAppMetrica["type"] = "Kategori Liste"
        mapOfFeedAppMetrica["category1"] = categoryIdWithName.title

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)

    }

}