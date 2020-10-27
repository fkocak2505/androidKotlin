package com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle

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
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.FeedArticleModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreTag.tagsArticle.TagsArticleActivityModelImpl
import com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreTag.tagsArticle.TagsArticleActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.layutManager.PreCachingLayoutManager
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.optimizedArticleDetail.utils.DeviceUtils
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.SwipableArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.swipableArticleDetail.model.SwipableArticleDetailObj
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.TagsArticleRecyclerViewAdapter
import com.onedio.androidside.mvp.view.dashboard.search.moreTag.tagsArticle.adapter.model.TagsAdapterModel
import com.yandex.metrica.YandexMetrica
import kotlinx.android.synthetic.main.activity_tags_article.*


class TagsArticleActivityViewImpl : AppCompatActivity(),
    ITagsArticleActivityView {

    private lateinit var toolBar: Toolbar

    private lateinit var tagsArticleActivityPresenterImpl: TagsArticleActivityPresenterImpl

    private var tagsFeedList: MutableList<HugeCardModel> = mutableListOf()
    private var arrOfTagsData: MutableList<TagsAdapterModel> = mutableListOf()

    private var page = 1

    private var isFirstLoad = false

    private lateinit var filterParamDate: String
    private lateinit var filterParamSort: String

    private lateinit var tagsArticle: Tags

    private var isScreenLoadMultiple: Boolean = false

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var theme: String = ""

    private lateinit var mLayoutManager: PreCachingLayoutManager
    private var isLoading: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags_article)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        savedInstanceState?.let {
            isScreenLoadMultiple = true
        } ?: run {
            isScreenLoadMultiple = false
        }

        tagsArticle = Gson().fromJson(intent.getStringExtra("TAGS_ARTICLE"), Tags::class.java)

        filterParamDate = "recent"
        filterParamSort = "1month"

        initTagsActivityComponent()

        prepareToolbar()

        clickDateSort()

        clickPopularitySort()

        showPopularitySortMenu()

    }


    override fun initTagsActivityComponent() {
        supportActionBar?.hide()

        //tagsText.text = OnedioSingletonPattern.instance.getTagsItem()?.name

        tagsArticleActivityPresenterImpl =
            TagsArticleActivityPresenterImpl(
                TagsArticleActivityModelImpl(),
                this
            )

        getTagsArticleData()

        setRecyclerViewConfig()

    }

    private fun prepareToolbar() {
        toolBar = toolBar4TagsArticle as Toolbar

        toolBar.title = tagsArticle.name
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

    private fun clickDateSort() {
        dateSort.setOnClickListener {
            changeSortingButton2Date()

            tagsFeedList = mutableListOf()
            arrOfTagsData = mutableListOf()

            filterParamDate = "recent"
            filterParamSort = "1month"
            page = 1
            isFirstLoad = false

            tagsArticleActivityPresenterImpl.getTagArticle(
                true,
                tagsArticle.id!!,
                page,
                15,
                filterParamDate,
                filterParamSort,
                true
            )

        }
    }

    private fun clickPopularitySort() {
        popularitySort.setOnClickListener {

            tagsFeedList = mutableListOf()
            arrOfTagsData = mutableListOf()

            if (dotLast24Hour.visibility == View.VISIBLE) {
                dotLast24Hour.visibility = View.VISIBLE
                dotLastWeek.visibility = View.INVISIBLE
                dotLastMonth.visibility = View.INVISIBLE
                dotAllTime.visibility = View.INVISIBLE

                popularitySortMenu.visibility = View.INVISIBLE

                filterParamDate = "popular"
                filterParamSort = "1day"
                page = 1
                isFirstLoad = false


                tagsArticleActivityPresenterImpl.getTagArticle(
                    true,
                    tagsArticle.id!!,
                    page,
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

            tagsFeedList = mutableListOf()
            arrOfTagsData = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1day"
            page = 1
            isFirstLoad = false

            tagsArticleActivityPresenterImpl.getTagArticle(
                true,
                tagsArticle.id!!,
                page,
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

            tagsFeedList = mutableListOf()
            arrOfTagsData = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1week"
            page = 1
            isFirstLoad = false

            tagsArticleActivityPresenterImpl.getTagArticle(
                true,
                tagsArticle.id!!,
                page,
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

            tagsFeedList = mutableListOf()
            arrOfTagsData = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "1month"
            page = 1
            isFirstLoad = false

            tagsArticleActivityPresenterImpl.getTagArticle(
                true,
                tagsArticle.id!!,
                page,
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

            tagsFeedList = mutableListOf()
            arrOfTagsData = mutableListOf()

            filterParamDate = "popular"
            filterParamSort = "all"
            page = 1
            isFirstLoad = false

            tagsArticleActivityPresenterImpl.getTagArticle(
                true,
                tagsArticle.id!!,
                page,
                15,
                filterParamDate,
                filterParamSort,
                true
            )
        }
    }

    private fun getTagsArticleData() {
        tagsArticleActivityPresenterImpl.getTagArticle(
            false,
            tagsArticle.id!!,
            page,
            15,
            filterParamDate,
            filterParamSort,
            true
        )
    }

    override fun handleTagsArticle(response4TagArticle: Response4ArticlesFeed) {
        tagsRecyclerView.visibility = View.VISIBLE

        val tagsData = response4TagArticle.data?.feed!!

        if (arrOfTagsData.size == 0) {
            prepareTagsData(tagsData)

            tagsRecyclerView.adapter = TagsArticleRecyclerViewAdapter(
                applicationContext,
                arrOfTagsData
            ) { itemOfVideoData, type ->

                handleClickViews(itemOfVideoData, type)
            }
        } else {
            arrOfTagsData.removeAt(arrOfTagsData.size - 1)
            tagsRecyclerView.adapter?.notifyItemRemoved(arrOfTagsData.size)
            isLoading = false

            prepareTagsData(tagsData)

            tagsRecyclerView.adapter?.notifyDataSetChanged()
        }

        tagsRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition = mLayoutManager.findLastVisibleItemPosition()
                val listSize = arrOfTagsData.size

                if (!isLoading && listSize == (lastPosition + 1)) {
                    page++
                    infiniteScroll4VideoData(mLayoutManager)
                    isLoading = true
                }

            }
        })
    }

    private fun infiniteScroll4VideoData(layoutManager: PreCachingLayoutManager) {
        arrOfTagsData.add(
            TagsAdapterModel(
                "loading",
                "",
                0,
                "",
                null,
                null,
                "",
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
                "",
                false
            )
        )

        tagsRecyclerView.adapter?.notifyItemInserted(arrOfTagsData.size - 1)
        layoutManager.scrollToPosition(arrOfTagsData.size)
        tagsArticleActivityPresenterImpl.getTagArticle(
            false,
            tagsArticle.id!!,
            page,
            15,
            filterParamDate,
            filterParamSort,
            false
        )
    }

    private fun prepareTagsData(tagsData: MutableList<FeedArticleModel>): MutableList<TagsAdapterModel> {
        if (arrOfTagsData.size == 0)
            arrOfTagsData.add(
                TagsAdapterModel(
                    "googleAdsTop",
                    "",
                    0,
                    "",
                    null,
                    null,
                    "",
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
                    "",
                    false
                )
            )

        for (i in 0 until tagsData.size) {
            var redirectUrl: String? = null
            tagsData[i].redirectUrl?.let {
                redirectUrl = it
            } ?: run {
                redirectUrl = ""
            }

            var showInWebView: Boolean? = null
            tagsData[i].showInWebview?.let {
                showInWebView = it
            } ?: run {
                showInWebView = true
            }

            var image: String? = null
            tagsData[i].image?.let {
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

            arrOfTagsData.add(
                TagsAdapterModel(
                    "article",
                    tagsData[i].id!!,
                    tagsData[i].legacyId!!,
                    image!!,
                    null,
                    null,
                    tagsData[i].title!!,
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
            )

            tagsFeedList.add(
                HugeCardModel(
                    tagsData[i].id!!,
                    tagsData[i].legacyId!!,
                    image,
                    null,
                    null,
                    tagsData[i].title!!,
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
            )

        }

        return arrOfTagsData

    }

    private fun handleClickViews(itemOfTagsData: TagsAdapterModel, type: String) {
        when {
            itemOfTagsData.redirectUrl != "" -> {
                val browserIntent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(itemOfTagsData.redirectUrl)
                    )
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(browserIntent)

                startAnim()
            }
            else -> {
                val sHugeCardObj =
                    SwipableArticleDetailObj(
                        tagsFeedList,
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
                intent.putExtra("LEGACY_ID", itemOfTagsData.legacyId)
                startActivity(intent)

                startAnim()
            }
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
        tagsRecyclerView.layoutManager = mLayoutManager
        tagsRecyclerView.setItemViewCacheSize(5)
    }

    //==================================================================================================================
    /**
     * Show Loading..
     */
    //==================================================================================================================
    override fun showLoading() {
        tagsArticleAvlIndicatorProgress.playAnimation()
        tagsArticleAvlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun showLoading4List() {
        categoryFeedTagsAvlIndicatorProgress4List.playAnimation()
        categoryFeedTagsAvlIndicatorProgress4List.visibility = View.VISIBLE
        /*window?.setFlags(
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
        tagsArticleAvlIndicatorProgress.cancelAnimation()
        tagsArticleAvlIndicatorProgress.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun hideLoading4List() {
        categoryFeedTagsAvlIndicatorProgress4List.cancelAnimation()
        categoryFeedTagsAvlIndicatorProgress4List.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

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
                applicationContext,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext!!, R.color.constWhite),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor),
            ContextCompat.getColor(applicationContext!!, R.color.constTextColor)
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

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "l2r")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {

        if (!isScreenLoadMultiple)
            sendFirebaseAnalyticsEvent()

        super.onResume()

    }

    private fun sendFirebaseAnalyticsEvent() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val params = Bundle()
        params.putString("name", tagsArticle.name)
        params.putString("type", "Etiket Liste")
        params.putStringArray("tags", arrayOf(tagsArticle.name))

        firebaseAnalytics.logEvent("view_screen", params)

        val mapOfFeedAppMetrica = HashMap<String, Any>()
        mapOfFeedAppMetrica["name"] = tagsArticle.name.toString()
        mapOfFeedAppMetrica["type"] = "Etiket Liste"
        mapOfFeedAppMetrica["tags"] = arrayOf(tagsArticle.name)

        YandexMetrica.reportEvent("view_screen", mapOfFeedAppMetrica)
    }

}