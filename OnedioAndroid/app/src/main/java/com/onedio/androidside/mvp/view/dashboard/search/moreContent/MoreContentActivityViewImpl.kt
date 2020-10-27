package com.onedio.androidside.mvp.view.dashboard.search.moreContent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.onedio.androidside.R
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioCustomIntent
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.FeaturedNewsDataModel
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Articles
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article
import com.onedio.androidside.mvp.model.welcomeDashboard.search.moreContent.MoreContentActivityModelImpl
import com.onedio.androidside.mvp.presenter.welcomeDashboard.search.moreContent.MoreContentActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.ArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.articleDetail.TestArticleDetailActivityViewImpl
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.hugeCardRecyclerViewAdapter.model.HugeCardModel
import com.onedio.androidside.mvp.view.dashboard.search.moreContent.adapter.SearchAllContentDataGridAdapter
import com.onedio.androidside.singleton.OnedioSingletonPattern
import kotlinx.android.synthetic.main.activity_more_content.*

class MoreContentActivityViewImpl : AppCompatActivity(),
    IMoreContentActivityView {

    private lateinit var toolBar: Toolbar

    private var mContentResponseList: MutableList<Articles> = mutableListOf()
    private var mContentListOfFeaturedNewsDataModel: MutableList<FeaturedNewsDataModel> =
        mutableListOf()

    private lateinit var moreContentActivityPresenterImpl: MoreContentActivityPresenterImpl
    private lateinit var mAdapter: SearchAllContentDataGridAdapter

    private var page: Int = 1

    private lateinit var filterParamDate: String
    private lateinit var filterParamSort: String
    private lateinit var searchedWord: String
    private lateinit var searchedWordCount: String

    private var isFirstLoading: Boolean = false

    private var isContinueInfinite = true

    private var theme: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_content)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!


        //searchedWord = OnedioSingletonPattern.instance.getSearchedWord()


        intent.getStringExtra("MORE_CONTENT_SEARCH")?.let {
            searchedWord = intent.getStringExtra("MORE_CONTENT_SEARCH")!!
            intent.getStringExtra("MORE_CONTENT_SEARCH_COUNT")?.let {
                searchedWordCount = it
            } ?: run {
                searchedWordCount = "0"
            }


            initMoreContentComponent()

            clickDateSort()

            clickPopularitySort()

            showPopularitySortMenu()

            prepareToolbar()

            setSearchedWordAndCount(
                searchedWord,
                searchedWordCount
            )
            /*setSearchedWordAndCount(
                searchedWord,
                OnedioSingletonPattern.instance.getSearchArticleResponseData()!!
            )*/

            changeComponentTypeFace()

            clickSearchArea()


        } ?: run {
            onBackPressed()
        }

    }

    override fun initMoreContentComponent() {
        supportActionBar?.hide()

        moreContentActivityPresenterImpl =
            MoreContentActivityPresenterImpl(
                MoreContentActivityModelImpl(),
                this
            )

        filterParamDate = "all"
        filterParamSort = "updated desc"

        moreContentActivityPresenterImpl.filterSearchedContentByParams(
            searchedWord,
            page,
            25,
            filterParamDate,
            filterParamSort
        )

        searchAllContentsGrid.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {

            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    if (view?.lastVisiblePosition!! >= view.count - 1) {
                        if (isContinueInfinite) {
                            page++
                            moreContentActivityPresenterImpl.filterSearchedContentByParams(
                                searchedWord,
                                page,
                                25,
                                filterParamDate,
                                filterParamSort
                            )
                        }
                    }
                }
            }
        })
    }

    private fun prepareToolbar() {
        toolBar = toolBar4MoreContent as Toolbar

        toolBar.title = "İçerikler"
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

            searchArea4Content.background =
                ContextCompat.getDrawable(applicationContext, R.drawable.custom_edittext_dark_mode)
            searchArea4Content.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_search_dark_mode,
                0,
                0,
                0
            )
            searchArea4Content.setHintTextColor(Color.parseColor("#FFFFFF"))
            searchArea4Content.setTextColor(Color.parseColor("#FFFFFF"))

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

            searchArea4Content.background =
                ContextCompat.getDrawable(applicationContext!!, R.drawable.custom_edittext)
            searchArea4Content.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_search,
                0,
                0,
                0
            )
            searchArea4Content.setHintTextColor(Color.parseColor("#191919"))
            searchArea4Content.setTextColor(Color.parseColor("#191919"))
        }

    }


    private fun clickSearchArea() {
        searchArea4Content.setOnEditorActionListener { _, actionId, _ ->
            if (OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility == View.VISIBLE) {
                OnedioSingletonPattern.instance.getProfileSettingPopup()?.visibility = View.GONE
                return@setOnEditorActionListener false
            } else {
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (searchArea4Content.text.toString().length >= 3) {
                        searchedWord = searchArea4Content.text.toString()
                        moreContentActivityPresenterImpl.filterSearchedContentByParams(
                            searchedWord,
                            page,
                            25,
                            filterParamDate,
                            filterParamSort
                        )

                    } else if (searchArea4Content.text.toString().isEmpty()) {

                    }
                    handled = true
                }
                handled
            }
        }
    }


    override fun fillSearchAllContentData() {
        if (!isFirstLoading) {
            mAdapter =
                SearchAllContentDataGridAdapter(
                    applicationContext,
                    prepareGridData()
                ) {
                    val articleItem =
                        HugeCardModel(
                            it.id,
                            it.legacyId,
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
                            it.showInWebView,
                            it.redirectUrl
                        )
                    /*OnedioSingletonPattern.instance.setArticleItem(
                        articleItem
                    )*/

                    OnedioSingletonPattern.instance.setActivity(MoreContentActivityViewImpl::class.java)

                    /*val intent =
                        Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                    intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)*/

                    when {
                        it.redirectUrl != "" -> {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(it.redirectUrl))
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(browserIntent)
                        }
                        it.showInWebView -> {
                            val intent =
                                Intent(
                                    applicationContext,
                                    TestArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                        else -> {
                            val intent =
                                Intent(
                                    applicationContext,
                                    ArticleDetailActivityViewImpl::class.java
                                )
                            intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }

                    startAnim()
                }
            searchAllContentsGrid.adapter = mAdapter
            isFirstLoading = true
        } else {
            mAdapter.addItems(prepareGridData())

            SearchAllContentDataGridAdapter(
                applicationContext,
                prepareGridData()
            ) {
                val articleItem =
                    HugeCardModel(
                        it.id,
                        it.legacyId,
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
                        it.redirectUrl
                    )
                /*OnedioSingletonPattern.instance.setArticleItem(
                    articleItem
                )*/

                OnedioSingletonPattern.instance.setActivity(MoreContentActivityViewImpl::class.java)
                val intent =
                    Intent(applicationContext, ArticleDetailActivityViewImpl::class.java)
                intent.putExtra("ARTICLE_DETAIL_DATA", Gson().toJson(articleItem))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                /*OnedioCommon.cStartActivity(
                    applicationContext,
                    ArticleDetailActivityViewImpl::class.java
                )*/
                startAnim()
            }
        }
    }

    private fun prepareGridData(): MutableList<FeaturedNewsDataModel> {
        mContentListOfFeaturedNewsDataModel = mutableListOf()

        for (itemOfContentResponseList in mContentResponseList) {
            var id: String? = null
            itemOfContentResponseList.id?.let {
                id = it
            } ?: run {
                id = ""
            }

            var legacyId: Long? = null
            itemOfContentResponseList.legacyId?.let {
                legacyId = it
            } ?: run {
                legacyId = 0
            }

            var image: String? = null
            itemOfContentResponseList.image?.let {
                it.x6?.let {
                    image = it
                } ?: run {
                    image = ""
                }
            } ?: run {
                image = ""
            }

            var title: String? = null
            itemOfContentResponseList.title?.let {
                title = it
            } ?: run {
                title = ""
            }

            var date: String? = null
            itemOfContentResponseList.date?.let {
                date = it
            } ?: run {
                date = ""
            }

            var showInWebView: Boolean? = null
            itemOfContentResponseList.showInWebview?.let {
                showInWebView = it
            } ?: run {
                showInWebView = true
            }

            mContentListOfFeaturedNewsDataModel.add(
                FeaturedNewsDataModel(
                    id!!,
                    legacyId!!,
                    image!!,
                    title!!,
                    date!!,
                    showInWebView!!,
                    ""
                )
            )
        }

        return mContentListOfFeaturedNewsDataModel
    }

    override fun clickPopularitySort() {
        popularitySort.setOnClickListener {
            changeSortingButton2Popularity()

        }
    }

    override fun clickDateSort() {
        dateSort.setOnClickListener {

            changeSortingButton2Date()

            filterParamDate = "all"
            filterParamSort = "updated desc"
            page = 1
            isFirstLoading = false

            moreContentActivityPresenterImpl.filterSearchedContentByParams(
                searchedWord,
                page,
                25,
                filterParamDate,
                filterParamSort
            )
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

            filterParamDate = "day"
            filterParamSort = "popularity desc"
            page = 1
            isFirstLoading = false

            moreContentActivityPresenterImpl.filterSearchedContentByParams(
                searchedWord,
                page,
                25,
                filterParamDate,
                filterParamSort
            )

        }

        lastWeek.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.VISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            filterParamDate = "week"
            filterParamSort = "popularity desc"
            page = 1
            isFirstLoading = false

            moreContentActivityPresenterImpl.filterSearchedContentByParams(
                searchedWord,
                page,
                25,
                filterParamDate,
                filterParamSort
            )
        }

        lastMonth.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.VISIBLE
            dotAllTime.visibility = View.INVISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            filterParamDate = "month"
            filterParamSort = "popularity desc"
            page = 1
            isFirstLoading = false

            moreContentActivityPresenterImpl.filterSearchedContentByParams(
                searchedWord,
                page,
                25,
                filterParamDate,
                filterParamSort
            )
        }

        allTime.setOnClickListener {
            dotLast24Hour.visibility = View.INVISIBLE
            dotLastWeek.visibility = View.INVISIBLE
            dotLastMonth.visibility = View.INVISIBLE
            dotAllTime.visibility = View.VISIBLE

            popularitySortMenu.visibility = View.INVISIBLE

            filterParamDate = "all"
            filterParamSort = "popularity desc"
            page = 1
            isFirstLoading = false

            moreContentActivityPresenterImpl.filterSearchedContentByParams(
                searchedWord,
                page,
                25,
                filterParamDate,
                filterParamSort
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setSearchedWordAndCount(searchedText: String, searchedCount: String) {
        searchedTagWord4Content.text =
            "\"$searchedText terimi ile ilgili toplam $searchedCount sonuç bulundu"
        searchArea4Content.setText(searchedText)
    }

    override fun handleFilteredSearchData(response4Article: Response4Article) {
        setSearchedWordAndCount(searchedWord, response4Article.total.toString())

        mContentResponseList = mutableListOf()
        mContentResponseList = response4Article.articles!!

        isContinueInfinite = response4Article.articles?.size != 0

        fillSearchAllContentData()

    }

    override fun changeComponentTypeFace() {
        //applicationToolbarTitle.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchArea4Content.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        contentTitleInfo.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        searchedTagWord4Content.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        sortInfo4Comments.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        infoDate.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        infoPopularite.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
    }

    override fun showLoading() {
        avlIndicatorProgress4MoreContentSearchFilter.playAnimation()
        avlIndicatorProgress4MoreContentSearchFilter.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        avlIndicatorProgress4MoreContentSearchFilter.cancelAnimation()
        avlIndicatorProgress4MoreContentSearchFilter.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun showError(response4ErrorObj: Response4ErrorObj) {
        /*EZDialog.Builder(this)
            .setTitle("Hata.!")
            .setMessage(response4ErrorObj.status?.message)
            .setPositiveBtnText("Tamam")
            .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
            .setTitleTextColor(resources.getColor(R.color.constWhite))
            .setMessageTextColor(resources.getColor(R.color.constTextColor))
            .setButtonTextColor(resources.getColor(R.color.constTextColor))
            .setCancelableOnTouchOutside(false)
            .OnPositiveClicked {

            }
            .build()*/
        Toast.makeText(applicationContext, response4ErrorObj.status?.message, Toast.LENGTH_LONG)
            .show()
    }

    private fun startAnim() {
        OnedioCustomIntent.startAnim(this, "r2l")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}